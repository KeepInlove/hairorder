<template>
    <div class="login_container">
        <div class="login_box">
            <el-card style="margin: auto;text-align:center;font-size: 36px;font-family:SimSun;opacity:90%">
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
        name: 'login',
        data() {
            return {
                loginUser: {
                    username: '',
                    password: '',
                },
                //这是登录表单的数据绑定对象
                loginForm: {
                    user: 'test',
                    password: 'test',
                    seccode: '',
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
                // console.log(this.loginForm);
                // console.log(this.checkCode);
                if (this.loginForm.seccode != this.checkCode) {
                    this.$message({
                        message: "验证码错误，注意大写字母",
                        type: "warning"
                    });
                    this.createCode();
                    return false;
                }
                this.loginUser.username=this.loginForm.user;
                this.loginUser.password=this.loginForm.password;
                console.log(this.loginUser.password)
                // const thas=this;
                this.$refs.loginRef.validate(async valid =>{
                    if (!valid)return;
                    this.loginUser.password= this.hexMd5(this.loginUser.password+this.KEY);
                   console.log(this.loginUser)
                    const {data: res}=await this.$axios.post('/api/login/sys',this.loginUser);
                    console.log(res);
                    if (!res.success) return this.$message.error("用户名或密码错误");
                    this.$message.success("登录成功");
                   // this.$store.user("setUser", res.data.content);
                    this.$store.commit("set_token", res.content.token);
                    this.$store.commit("setUserInfo", res.content);
                    setTimeout(() => {
                        // 此时要判断/login后面的参数，若无参数，进入主页；
                        this.$router.push("/home");
                        // 若有参数则参数为未有权限的那个路由，跳转到那个路由
                        // this.$router.push(***); -- 具体要自己在这实现
                    }, 100);
                    // console.log(window.sessionStorage.getItem('token'));
                    // this.$router.push('/sys/home');
                });

                // this.$axios.post('/api/login/sys',).then((res)=>{
                //     console.log(res);
                //
                // });
                console.log('登录')
            }

        }

    }
</script>

<style scoped lang="less">
    .el-form-item__content {
        line-height: 50px;
    }
    .login_container {
        /*background-image: url('../../assets/img/bj.png');*/
        background-repeat: no-repeat;
        background-size: cover;
        background-attachment: fixed;
        background-color: #283443;
        width: 100%;
        height: 100%;
    }

    .login_box {
        margin: 0 auto;
        width: 500px;
        height: 400px;
        background-color: #ffffff;
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
