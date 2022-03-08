<template>
    <div>
        <el-breadcrumb>
            <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>理发师管理</el-breadcrumb-item>
            <el-breadcrumb-item>理发师列表</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <el-row :gutter="20">

                <el-col :span="6">
                    <el-input placeholder="请输入昵称" v-model="barberName" ></el-input>
                </el-col>
                <el-col :span="15" >
                    <el-button type="success"  round>搜索</el-button>
                    <el-button round >重置</el-button>
                </el-col>
            </el-row>
            <el-row style="margin-top: 20px">
                <el-table :data="barberList"  border>
                    <el-table-column type="index" label="#"></el-table-column>
                    <el-table-column   label="头像" width="150">
                        <template slot-scope="scope">

                            <div class="block" >
                                <!--                            <span class="title">{{ fit }}</span>-->
                                <el-avatar shape="square" :size="120"  :src="scope.row.barberImg"></el-avatar>
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column prop="barberName" width="120"  label="昵称"></el-table-column>

                    <el-table-column prop="barberDes"   width="200" label="描述"></el-table-column>
                    <el-table-column prop="barberTypeName" width="150" label="类型">
                    </el-table-column>
                    <el-table-column prop="orderCount" width="100" label="订单量">
                    </el-table-column>
                    <el-table-column prop="barberPrice"  width="100" sortable label="价格">
                        <template slot-scope="scope">
                            {{scope.row.barberPrice }}￥
                        </template>
                    </el-table-column>
                    <el-table-column prop="barberStatus" width="150" sortable label="休息/工作">
                        <template slot-scope="scope">
                            <el-switch active-color="#13ce66"
                                       inactive-color="#ff4949" @click.native.prevent="changeListType(scope.row.barberId)"  v-model="scope.row.barberStatus"></el-switch>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" width="150" fixed="right">
                        <template slot-scope="scope">
                            <el-button type="success"  size="mini" round   @click="updateBarber(scope.row)">编辑</el-button>
                            <el-button  type="danger"  size="mini" round  @click="barberDel(scope.row)">删除</el-button>
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
        name: "list",
        data(){

            return{

                url: '',
                dialogForm:false,
                barberName:'',
                barber:{},
                page:1,
                pageSize:5,
                total:100,
                barberList:[],

            }
        },
        mounted(){
            this.getList(this.page-1,this.pageSize)
        },
        methods:{
            async  getList(page,size){
                const thas=this;
                this.$axios.get('/api/barber/list', {
                        params:{page:page, size:size}
                    }
                ).then((res)=>{
                    console.log(res);
                    thas.total=res.data.content.total;
                    thas.barberList=res.data.content.list
                },)
            },
            changeListType(e){
                console.log(e);
            },
            updateBarber(e){
                console.log(e);
                // let barberForm=JSON.stringify(e);
                // console.log(barberForm)
                this.$router.push({
                    // path: 'edit',
                    name:'barberEdit',
                    params:{id:e.barberId,barberForm: e},
                })
                this.user=e;
                console.log(e);
            },
            async  barberDel(e){
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
                    const {data: res} = await this.$axios.delete('/api/barber/delete/' + e.barberId)
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
