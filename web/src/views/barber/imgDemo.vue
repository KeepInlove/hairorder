<template>
    <div>
        <!-- list-type 图片的显示类型 -->
        <!-- action 图片要上次服务器的地址 -->
        <!-- on-preview 点击文件列表中已上传的文件时的触发的钩子函数（点击显示对应的预览图片） -->
        <!-- on-remove 点击删除图片的时候触发的钩子函数 -->
        <!-- headers 上传可能需要携带的请求头部参数（不需要可以不加） -->
        <el-upload
                class="avatar-uploader"
                action="http://localhost:8880/api/upload"
                :show-file-list="false"
                :headers="headerObj"
                :on-success="handleSuccess"
                >
            <img v-if="url" :src="url" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>

        <el-upload
                :action="uploadURL"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :headers="headerObj"
                :on-success="handleSuccess"
                list-type="picture"
        >
            <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>

        <!-- 图片预览弹框 -->
        <el-dialog title="图片预览" :visible.sync="previewDialogVisible" width="40%"  class="dialog-picture" >
            <img :src="picPreviewPath" alt="" class="previewImg" />
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "imgDemo",
        data() {
            return {
                // 图片上传地址
                uploadURL: "http://localhost:8880/api/upload",

                // 图片上传组件的请求对象
                headerObj: {
                    token: this.$store.getters.get_token,
                },
                url:null,
                //定义一个数组来存放路径
                pictureList: [],

                //预览的图片路径
                picPreviewPath: null,

                //预览图片弹框的状态(默认关闭)
                previewDialogVisible: false,
            };
        },
        mounted() {

            // this.headerObj.token=this.$store.getters.get_token;
            const user=this.$store.getters.get_user;
            // console.log('token:',token);
            // this.name=user.name;
            console.log('user:',user);
        },
        methods: {

            // 监听图片上传成功事件
            handleSuccess(response) {
                //返回的对象
                console.log(response);
                this.$notify({
                    title: '成功',
                    message: '图片上传成功',
                    type: 'success'
                });

                //这是本地的图片路径
                this.url=response.content.fileName
                console.log(response.content.fileName );

                //得到一个图片信息对象 临时路径
                const picInfo = { pic: response.content.fileName };

                // 将图片信息对象，push到pictureList数组中
                this.pictureList.push(picInfo);
            },
            //点击图片，利用弹框显示出预览图
            handlePreview(file) {
                console.log("你点击了图片");
                //console.log(file)
                //获取到本地图片路径
                this.picPreviewPath = file.response.data.content.fileName ;

                //显示出对应的预览图片
                this.previewDialogVisible = true;
            },
            handleRemove(file) {
                console.log("你删除了图片");

                // 1.获取将要删除图片的临时路径
                const filePath = file.response.content.fileName;

                // 2.从pictureList数组中，找到图片对应的索引值
                const i = this.pictureList.findIndex((x) => x.pic === filePath);

                // 3.调用splice方法，移除图片信息
                this.pictureList.splice(i, 1);

                console.log("删除后的" + this.pictureList);
            },
        },
    };
</script>

<style lang="less" scoped>
    .dialog-picture {
        text-align: center;
    }
    .previewImg {
        width: 56%;
    }
</style>
