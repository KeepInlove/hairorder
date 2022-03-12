<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>项目管理</el-breadcrumb-item>
      <el-breadcrumb-item>新增项目</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card style="padding: 10px;">
      <el-form  ref="form" :model="hairForm"  label-width="100px" style="width: 700px;" >
        <el-form-item label="理发师照片">
          <el-upload
              class="avatar-uploader"
              :action="action"
              :show-file-list="false"
              :headers="headerObj"
              :on-success="handleSuccess">
            <img v-if="url" :src="url" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="项目名称">
          <el-input v-model="hairForm.hairName"></el-input>
        </el-form-item>
        <el-form-item label="项目描述" prop="checkPass">
          <el-input v-model="hairForm.hairDes" ></el-input>
        </el-form-item>
        <el-form-item label="项目类型">
          <el-select v-model="hairForm.hairTypeId" placeholder="请选择" style="width: 600px">
            <el-option
                v-for="item in hairTypeList"
                :key="item.hairTypeId"
                :label="item.hairTypeName"
                :value="item.hairTypeId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="理发师类型">
          <el-select v-model="hairForm.barberTypeId" placeholder="请选择" style="width: 600px">
            <el-option
                v-for="item in barberTypeList"
                :key="item.barberTypeId"
                :label="item.barberTypeName"
                :value="item.barberTypeId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="价格" prop="checkPass">
          <el-input-number  style="width: 600px" :precision="2" :step="0.1" v-model="hairForm.hairPrice"></el-input-number>
        </el-form-item>
        <el-form-item size="large" style="text-align: center">
          <el-button type="primary" @click="onSubmit">立即创建</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "add",
  data() {
    return {
      hairTypeList:[],
      barberTypeList:[],
      value: '',
      // 图片上传地址
      action: "http://localhost:8880/api/upload",
      // 图片上传组件的请求对象
      headerObj: {
        token: this.$store.getters.get_token,
      },
      url:null,
      hairForm: {
        hairName: '',
        hairDes: '',
        hairTypeId: '',
        hairPrice: null,
        hairStatus: 1,
        hairImg: '',
      }
    }
  },
  mounted(){
    this.getHairType();
    this.getBarberType()
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
      this.url=response.content.fileName;

      console.log(response.content.fileName);
      this.hairForm.hairImg=response.content.fileName
      //得到一个图片信息对象 临时路径
      // const picInfo = { pic: response.content.fileName };

      // 将图片信息对象，push到pictureList数组中
      // this.pictureList.push(picInfo);
    },

    getHairType(){
      this.$axios.get('/api/hairType/list').then((res)=>{
        console.log("<==============>");
        console.log(res)
        if (res.status!=200) return this.$message.error("网络错误");
        this.hairTypeList=res.data.content;

      })
    },
    getBarberType(){
      this.$axios.get('/api/barberType/list').then((res)=>{
        console.log("<==============>");
        console.log(res)
        if (res.status!=200) return this.$message.error("网络错误");
        this.barberTypeList=res.data.content;

      })
    },
    onSubmit() {
      console.log('submit!');
      console.log(this.hairForm);
      this.$axios.post("/api/hair/save",this.hairForm).then((res)=>{
        console.log("<==============>");
        console.log(res)
        if (res.status!=200) return this.$message.error("网络错误");
        if (res.data.success==true){
          this.$notify({
            title: '成功',
            message: '添加成功!',
            type: 'success'
          });
        }else {
          this.$notify({
            title: '失败',
            message: '添加失败!',
            type: 'error'
          });
        }
      })
    }
  }
}
</script>

<style scoped>

</style>