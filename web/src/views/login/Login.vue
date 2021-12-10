<template>
    <div class="login_container">
        <div class="login_box">
            <el-card style="margin: auto;text-align:center;font-size: 20px;font-family:SimSun;color: cornflowerblue;opacity:90%">
                客户管理系统
            </el-card>
            <el-form ref="loginRef" class="login_form" :model="loginForm" :rules="loginFormRules" label-width="0px">
                <!--用户名-->
                <el-form-item prop="user">
                    <el-input v-model="loginForm.user" type="text" prefix-icon="el-icon-user"
                              placeholder="请输入账号"></el-input>
                </el-form-item>
                <!--密码-->
                <el-form-item prop="password">
                    <el-input v-model="loginForm.password" type="password" prefix-icon="el-icon-lock"
                              placeholder="请输入密码" show-password></el-input>
                </el-form-item>
                <!--验证码-->
                <el-form-item prop="seccode" class="row">
                    <el-input v-model="loginForm.seccode" placeholder="验证码" prefix-icon="el-icon-edit">
                        <template slot="append"><span class="checkCode" @click="createCode">{{ checkCode}}</span>
                        </template>
                    </el-input>
                </el-form-item>
                <!--按钮-->
                <div class="btns">
                    <el-form-item class="btns_item">
                        <el-button type="success" @click="resetForm">重置</el-button>
                    </el-form-item>
                    <el-form-item class="btns_item">
                        <el-button type="primary" @click="login">登录</el-button>
                    </el-form-item>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default{
        name: 'Login',
        data() {
            return {
                //这是登录表单的数据绑定对象
                loginForm: {
                    user: '',
                    password: '',
                    seccode: ''
                },
                ip: '',
                checkCode: '',
                //表单验证规则
                loginFormRules: {
                    user: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 3, max: 10, message: '长度在 3 到 10个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 3, max: 12, message: '长度在 3 到 10个字符', trigger: 'blur'}
                    ]
                },
            }
        },
        mounted() {
            this.createCode()
        },
        methods: {
            //验证码生成返回
            createCode() {
                let code = '';
                const codeLength = 4 //验证码的长度
                const random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                    'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //随机数
                for (let i = 0; i < codeLength; i++) { //循环操作
                    let index = Math.floor(Math.random() * 36) //取得随机数的索引（0~35）
                    code += random[index] //根据索引取得随机数加到code上
                }
                this.checkCode = code //把code值赋给验证码
            },
            //重置方法
            resetForm() {
                // console.log(this);
                this.$refs.loginRef.resetFields()
            },
            //    登录
            login() {
                console.log('登录')
            }

        }

    }
</script>

<style scoped lang="less">
    .login_container {
        background-image: url('../../assets/img/bj.png');
        background-repeat: no-repeat;
        background-size: cover;
        background-attachment: fixed;
        background-color: cadetblue;
        width: 100%;
        height: 100%;
    }

    .login_box {
        margin: 0 auto;
        width: 400px;
        height: 380px;
        background-color: #fff;
        border-radius: 3px;
        position: absolute; /*绝对定位*/
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        opacity:90%
    }

    .login_form {
        position: absolute;
        bottom: 0;
        width: 100%;
        padding: 0 20px;
        box-sizing: border-box;
    }

    .btns_item {
        height: 100%;
        padding: 5px;
        width: 200px;
        .el-button {
            height: 100%;
            width: 100%;
        }
    }
    .btns {
        display: flex;
        justify-content: space-between;
    }
</style>
