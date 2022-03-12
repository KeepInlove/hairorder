<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>项目管理</el-breadcrumb-item>
      <el-breadcrumb-item>项目列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row style="margin-top: 20px">
        <el-table :data="hairList"  border>
          <el-table-column type="index" label="#"></el-table-column>
          <el-table-column   label="头像" width="150">
            <template slot-scope="scope">

              <div class="block">
                <el-avatar shape="square" :size="120"  :src="scope.row.hairImg"></el-avatar>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="hairName" width="120"  label="项目名称"></el-table-column>

          <el-table-column prop="hairDes"   width="200" label="描述"></el-table-column>
          <el-table-column prop="hairTypeName" width="150" label="项目类型">
          </el-table-column>
          <el-table-column prop="barberTypeName" width="150" label="理发师类型">
          </el-table-column>

          <el-table-column prop="orderCount" width="100" label="订单量">
          </el-table-column>
          <el-table-column prop="hairPrice"  width="100" sortable label="价格">
            <template slot-scope="scope">
              {{scope.row.hairPrice }}￥
            </template>
          </el-table-column>
          <el-table-column prop="hairStatus" width="150" sortable label="上架/下架">
            <template slot-scope="scope">
              <el-switch active-color="#13ce66"
                         inactive-color="#ff4949" @click.native.prevent="changeListType(scope.row.hairId)"  v-model="scope.row.hairStatus"></el-switch>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="150" fixed="right">
            <template slot-scope="scope">
              <el-button type="success"  size="mini" round   @click="updateHair(scope.row)">编辑</el-button>
              <el-button  type="danger"  size="mini" round  @click="hairDel(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page.sync="page"
              :page-sizes="[5, 10, 50]"
              :page-size="pageSize"
              layout="total,sizes, prev, pager, next"
              :total="total">
          </el-pagination>
        </div>
      </el-row>
    </el-card>
  </div>
</template>

<script>
    export default {
        name: "hairList",
      data(){
          return{

            url: '',
            dialogForm:false,
            hairName:'',
            hair:{},
            page:1,
            pageSize:5,
            total:100,
            hairList:[]
          }
      },
      mounted(){
        this.getList(this.page-1,this.pageSize)
      },
      methods:{
        async  getList(page,size){
          const thas=this;
          this.$axios.get('/api/hair/list', {
                params:{page:page, size:size}
              }
          ).then((res)=>{
            console.log(res);
            thas.total=res.data.content.total;
            thas.hairList=res.data.content.list
          },)
        },
        changeListType(e){
          console.log(e);
        },
        updateHair(e){
          console.log(e);
          // let hairForm=JSON.stringify(e);
          // console.log(hairForm)
          this.$router.push({
            // path: 'edit',
            name:'hairEdit',
            params:{id:e.hairId,hairForm: e},
          })
          this.user=e;
          console.log(e);
        },
        async  hairDel(e){
          // console.log(e);
          if (e.orderCount!=0){
            this.$message.error("此员工有订单,无法删除!!")
          }else {
            // 弹框询问用户是否删除数据
            const confirmResult = await this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).catch(() => {
              // this.$message({
              //     type: 'info',
              //     message: '已取消删除'
              // })
            });

            // 如果用户确认删除，则返回值为字符串 confirm
            // 如果用户取消删除，则返回值为字符串 cancel
            if (confirmResult !== 'confirm') {
              return this.$message.info('已取消删除')
            }

            // 发起删除用户信息的数据请求
            const {data: res} = await this.$axios.delete('/api/hair/delete/' + e.hairId)
            if (res.success!==true) {

              return this.$message.error('删除用户失败！')
            }
            // 提示删除成功
            this.$message.success('删除用户成功！');
            // 更新数据列表
            this.getList(this.page-1,this.pageSize)
          }

        },

        handleSizeChange(val) {
          this.pageSize=val;
          this.getList(this.page-1,val)
          console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
          this.getList(val-1,this.pageSize)
          console.log(`当前页: ${val}`);
        }
      },
    }
</script>

<style scoped>

</style>
