<template>
<div>
    <el-breadcrumb>
        <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>客户管理</el-breadcrumb-item>
        <el-breadcrumb-item>客户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
        <el-row :gutter="20">

            <el-col :span="6">
                <el-input placeholder="请输入手机号" v-model="phone" ></el-input>
            </el-col>
            <el-col :span="15" >
                <el-button type="success"  round>搜索</el-button>
                <el-button round >重置</el-button>
            </el-col>
            <el-button type="primary"  @click="remakeUser(),dialogForm=true" round>添加</el-button>
        </el-row>
        <el-row style="margin-top: 20px">
            <el-table :data="userList"  border>
                <el-table-column type="index" label="#"></el-table-column>
                <el-table-column prop="name"  label="昵称"></el-table-column>
                <el-table-column prop="phone"   label="手机号"></el-table-column>
                <el-table-column prop="sex"  label="性别"></el-table-column>
                <el-table-column prop="integral"  sortable label="积分"></el-table-column>
                <el-table-column prop="createTime" sortable label="创建时间"></el-table-column>
                <el-table-column label="操作" fixed="right" >
                    <template slot-scope="scope">
                      <el-button type="success"  size="mini" round   @click="editClick(scope.row),dialogForm=true">编辑</el-button>
                      <el-button  type="danger"  size="mini" round  @click="userDel(scope.row)">删除</el-button>
<!--                        <el-button round  @click="editClick(scope.row),dialogForm=true">编辑</el-button>-->
                    </template>
                </el-table-column>
            </el-table>
            <div class="block">

                <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page.sync="page"
                        :page-sizes="[2, 5,10,20]"
                        :page-size="pageSize"
                        layout="total,sizes, prev, pager, next"
                        :total="total">
                </el-pagination>
            </div>
        </el-row>

    </el-card>

    <el-dialog style="text-align: center" title="修改信息" :visible.sync="dialogForm" width="550px">
        <el-form :model="user">
            <el-form-item label="手机号" label-width="120px" prop="stuId">
                <el-input v-model="user.phone" ></el-input>
            </el-form-item>
            <el-form-item label="昵称" label-width="120px" prop="stuId">
                <el-input v-model="user.name" ></el-input>
            </el-form-item>
            <el-form-item label="性别" label-width="120px" prop="stuId">
                <el-input v-model="user.sex" ></el-input>
            </el-form-item>
            <el-form-item label="积分" label-width="120px" prop="stuId">
                <el-input v-model="user.integral" ></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer" style="text-align: center">
            <el-button @click="dialogForm = false">取 消</el-button>
            <el-button type="primary" @click="updateUser(user)">确 定</el-button>
        </div>
    </el-dialog>
</div>
</template>

<script>
    export default {
        name: "list",
        data(){

            return{
                dialogForm:false,
                phone:'',
                user:{},
                total:10,
                pageSize:5,
                page:1,
                userList:[]
            }
        },
        mounted(){
            this.getList(this.page-1,this.pageSize)
        },
        methods:{
          getList(page,size){
                const thas=this;
                this.$axios.get('/api/user/list', {
                        params:{page:page, size:size}
                    }
                ).then((res)=>{
                    console.log(res);
                    thas.total=res.data.content.total
                    thas.userList=res.data.content.list
                })
            },

          //删除
          async  userDel(e){
            console.log(e);
              // 弹框询问用户是否删除数据
              const confirmResult = await this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              })
              // 如果用户确认删除，则返回值为字符串 confirm
              // 如果用户取消删除，则返回值为字符串 cancel
              if (confirmResult !== 'confirm') {
                return this.$message.info('已取消删除')
              }
              // 发起删除用户信息的数据请求
              const {data: res} = await this.$axios.delete('/api/user/userDel/' + e.id)
              if (res.success!==true) {

                return this.$message.error('删除用户失败！')
              }
              // 提示删除成功
              this.$message.success('删除用户成功！');
              // 更新数据列表
              this.getList(this.page-1,this.pageSize)

            },


            editClick(e){
                this.user=e;
                console.log(e);
            },
            remakeUser(){
                this.user={}
            },
            updateUser(e){
              this.$axios.post('/api/user/save',this.user).then((res) => {
                console.log("<==============>");
                console.log(e)
                if (res.status != 200) return this.$message.error("网络错误");
                if (res.data.success == true) {
                  this.$notify({
                    title: '成功',
                    message: '修改成功!',
                    type: 'success'
                  });
                  this.getList(this.page-1,this.pageSize)
                } else {
                  this.$notify({
                    title: '失败',
                    message: '修改失败!',
                    type: 'error'
                  });
                }
              })
                // console.log('更新');
                // console.log(e);
              this.dialogForm=false;
              this.getList(this.page-1,this.pageSize);
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
