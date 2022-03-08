<template>
    <el-container>
        <el-container>
            <el-aside width="240px">
                <el-menu background-color="#304156" text-color="#fff"
                         default-active="welcome"
                         :collapse-transition="false"
                         active-text-color="#409BFF"
                         :router="true">
                    <el-menu-item  index="welcome">
                        <i class="el-icon-s-home" />
                        <span slot="title">首页</span>
                    </el-menu-item >
                    <!--一级菜的模板区域-->
                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-s-custom"/>
                            <span>客户管理</span>
                        </template>
                        <el-menu-item index="list">
                            <template slot="title">
                                <i class="el-icon-user-solid"></i>
                                <span>客户列表</span>
                            </template>
                        </el-menu-item>
                        <el-menu-item index="order">
                            <template slot="title">
                                <i class="el-icon-time"></i>
                                <span>预约订单</span>
                            </template>
                        </el-menu-item>
                    </el-submenu>
                    <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-s-check"></i>
                            <span>项目管理</span>
                        </template>
                        <el-menu-item index="hairList">
                            <template slot="title">
                                <i class="el-icon-s-check"></i>
                                <span>项目列表</span>
                            </template>
                        </el-menu-item>
                        <el-menu-item index="hairType">
                            <template slot="title">
                                <i class="el-icon-s-check"></i>
                                <span>项目列表</span>
                            </template>
                        </el-menu-item>
                    </el-submenu>
                    <el-submenu index="3">
                        <template slot="title">
                            <i class="el-icon-s-check"></i>
                            <span>理发师管理</span>
                        </template>
                        <el-menu-item index="barberList">
                            <template slot="title">
                                <i class="el-icon-s-check"></i>
                                <span>理发师列表</span>
                            </template>
                        </el-menu-item>
                        <el-menu-item index="barberAdd">
                            <template slot="title">
                                <i class="el-icon-edit"></i>
                                <span>新增理发师</span>
                            </template>
                        </el-menu-item>
                        <el-menu-item index="barberTypeList">
                            <template slot="title">
                              <i class="el-icon-coordinate"></i>
                                <span>理发师类型列表</span>
                            </template>
                        </el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <el-main>
                <el-header height="54px">
                    <div>
                        <div class="user-logout">
                            <span  class="user">欢迎: {{name }}</span><el-button type="info" plain style="font-size: 15px">退出</el-button>
                        </div>
                    </div>
                </el-header>
               <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    export default {
        name: "home",
        data(){
            return{
                name:''
            }
        },
        mounted() {
            const user=this.$store.getters.get_user;
           this.name=user.name;

        },
    }
</script>

<style scoped lang="less">
    .el-container{
        height: 100%;
        width: 100%;
    }
    .el-header, .el-footer {
        background-color: #ffffff;
        color: #333;
        /*text-align: center;*/
        /*display: flex;*/
        /*flex-flow: row;*/
        /*line-height: 80px;*/
        width: 100%;
        .user-logout{
            float: right;
            margin-top: 5px;
            .user{
                margin-right: 20px;
                font-size: 15px;
                color: #4A5064;
            }
        }
    }

    .el-aside {
        background-color: #304156;
        color: #333;
        /*text-align: center;*/
        line-height: 100%;
        /*height: 100%;*/
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        /*text-align: center;*/
        /*line-height: 160px;*/
        padding: 1px;
        width: 100%;
    }
</style>
