<template>
    <div>
        <el-breadcrumb>
            <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>理发师管理</el-breadcrumb-item>
            <el-breadcrumb-item>新增理发师</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card style="padding: 10px;">
            <el-form  ref="form" :model="barberForm"  label-width="100px" style="width: 700px;" >
                <el-form-item label="理发师照片">
                    <el-upload
                            class="avatar-uploader"
                            :action="action"
                            :show-file-list="false"
                            :headers="headerObj"
                            :on-success="handleSuccess">
                        <img v-if="url" :src="url" class="avatar">
                        <img v-else :src="barberForm.barberImg" class="avatar">
                    </el-upload>
                </el-form-item>
                <el-form-item label="理发师昵称">
                    <el-input v-model="barberForm.barberName"></el-input>
                </el-form-item>
                <el-form-item label="理发师描述" prop="checkPass">
                    <el-input v-model="barberForm.barberDes" ></el-input>
                </el-form-item>
                <el-form-item label="理发师类型">
                    <el-select v-model="barberForm.barberTypeId" placeholder="请选择" style="width: 600px">
                        <el-option
                                v-for="item in typeList"
                                :key="item.barberTypeId"
                                :label="item.barberTypeName"
                                :value="item.barberTypeId">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="价格" prop="checkPass">
                    <el-input-number  style="width: 600px" :precision="2"  v-model="barberForm.barberPrice"></el-input-number>
                </el-form-item>
                <el-form-item size="large" style="text-align: center">
                    <el-button type="primary" @click="onSubmit">确定修改</el-button>
                    <el-button>取消</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>

</template>

<script>
    export default {
        name: "edit",
        data() {
            return {
                typeList: [],

                value: '',
                // 图片上传地址
                action: "http://localhost:8880/api/upload",
                // 图片上传组件的请求对象
                headerObj: {
                    token: this.$store.getters.get_token,
                },
                url: null,
                id: this.$route.params.id,
                barberForm: this.$route.params.barberForm

            }
        },

        mounted() {
            this.getBarberType();
            this.getBarber();
        },
        methods: {
            // 监听图片上传成功事件
            handleSuccess(response) {
                //返回的对象
                // console.log(response);
                this.$notify({
                    title: '成功',
                    message: '图片上传成功',
                    type: 'success'
                });

                //这是本地的图片路径
                this.url = response.content.fileName;

                console.log(response.content.fileName);
                this.barberForm.barberImg = response.content.fileName
                //得到一个图片信息对象 临时路径
                // const picInfo = { pic: response.content.fileName };

                // 将图片信息对象，push到pictureList数组中
                // this.pictureList.push(picInfo);
            },
             getBarber() {
                console.log(this.id);
                if (this.barberForm == null) {
                    console.log("<==============>");
                    this.$axios.get('/api/barber/find/' + this.id).then((res) => {
                        // console.log("<==============>");
                        // console.log(res);
                        this.barberForm=res.data.content
                    })
                }else {
                    this.barberForm=this.$route.params.barberForm
                }
            },
            getBarberType() {
                this.$axios.get('/api/barberType/list').then((res) => {
                    // console.log("<==============>");
                    console.log(res)
                    if (res.status != 200) return this.$message.error("网络错误");
                    this.typeList = res.data.content;

                })
            },
            onSubmit() {
                console.log('submit!');
                console.log(this.barberForm);
                this.$axios.post("/api/barber/save", this.barberForm).then((res) => {
                    // console.log("<==============>");
                    console.log(res)
                    if (res.status != 200) return this.$message.error("网络错误");
                    if (res.data.success == true) {
                        this.$notify({
                            title: '成功',
                            message: '修改成功!',
                            type: 'success'
                        });
                    } else {
                        this.$notify({
                            title: '失败',
                            message: '修改失败!',
                            type: 'error'
                        });
                    }
                })
            }
        }
    }
</script>

<style>
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>

