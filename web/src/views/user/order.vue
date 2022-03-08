<template>
    <div>
        <el-breadcrumb>
            <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>客户管理</el-breadcrumb-item>
            <el-breadcrumb-item>预约订单</el-breadcrumb-item>
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
                <el-table :data="orderList"  border>
                    <el-table-column type="index" label="#"></el-table-column>
                    <el-table-column prop="name"  label="昵称"></el-table-column>
                    <el-table-column prop="phone"   label="手机号"></el-table-column>
                    <el-table-column prop="price"  label="价格"></el-table-column>
                    <el-table-column prop="hairName"  label="发型项目"></el-table-column>
                    <el-table-column prop="barberName"  label="发型师"></el-table-column>
                    <el-table-column prop="orderDate" sortable label="预约时间"></el-table-column>
                    <el-table-column prop="orderStatus"  label="预约状态">
                        <template slot-scope="scope">
                            <el-tag type="primary" v-show="scope.row.orderStatus==0?true:false">待确定</el-tag>
                            <el-tag type="success" v-show="scope.row.orderStatus==1?true:false">预约成功</el-tag>
                            <el-tag type="danger" v-show="scope.row.orderStatus==2?true:false">预约失败</el-tag>
                        </template>

                    </el-table-column>
                    <el-table-column label="操作"  width="150px" >
                        <template slot-scope="scope">
                            <el-button type="success" v-show="scope.row.orderStatus==0?true:false" size="mini" round  @click="okClick(scope.row)">接受</el-button>
                            <el-button type="danger" v-show="scope.row.orderStatus==0?true:false" size="mini" round  @click="cancelClick(scope.row)">拒绝</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div class="block">

                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page.sync="currentPage"
                            :page-sizes="[10, 20, 50]"
                            :page-size="10"
                            layout="total,sizes, prev, pager, next"
                            :total="100">
                    </el-pagination>
                </div>
            </el-row>

        </el-card>
    </div>
</template>

<script>
    export default {
        name: "order",
        data(){
            return{
                currentPage:0,
                active: 0,
                phone:'',
                orderStatus:"primary",
                orderList:[
                    {
                        userId: 7350204384166912,
                        name:'张一',
                        phone: "12345678900",
                        price: 88.00,
                        hairName: "锡纸烫",
                        barberName:"张托尼",
                        orderDate: "2021-12-08",
                        orderStatus:0
                    },
                    {
                        userId: 7350204384166912,
                        name:'张二',
                        phone: "12345678900",
                        price: 99.00,
                        hairName: "摩根烫",
                        barberName:"张托尼",
                        orderDate: "2021-12-08",
                        orderStatus:1
                    },
                    {
                        userId: 7350204384166912,
                        name:'张三',
                        phone: "12345678900",
                        price: 30.00,
                        hairName: "护发",
                        barberName:"张托尼",
                        orderDate: "2021-12-08",
                        orderStatus:1
                    },
                    {
                        userId: 7350204384166912,
                        name:'张四',
                        phone: "12345678900",
                        price: 15.00,
                        hairName: "吹剪",
                        barberName:"张托尼",
                        orderDate: "2021-12-08",
                        orderStatus:2
                    },
                ]
            }
        },
        methods:{
            okClick(e){
                console.log('接受:')
                console.log(e)
            },
            cancelClick(e){
                console.log('拒绝:')
                console.log(e)
            },
            updateUser(e){
                console.log('更新');
                console.log(e)
                this.dialogForm=false;
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
            }
        }
    }
</script>

<style scoped>

</style>
