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
          <el-input placeholder="请输入手机号" v-model="phone"></el-input>
        </el-col>
        <el-col :span="15">
          <el-button type="success" @click="findByPhone"  round>搜索</el-button>
          <el-button round>重置</el-button>
        </el-col>
        <el-button type="primary" @click="dialogForm=true" round>添加</el-button>
      </el-row>
      <el-row style="margin-top: 20px">
        <el-table :data="orderList" border>
          <el-table-column type="index" label="#"></el-table-column>
          <el-table-column prop="userName" label="昵称"></el-table-column>
          <el-table-column prop="phone" label="手机号"></el-table-column>
          <el-table-column prop="price" label="价格"></el-table-column>
          <el-table-column prop="hairName" label="发型项目"></el-table-column>
          <el-table-column prop="barberName" label="发型师"></el-table-column>
          <el-table-column prop="orderDate" sortable label="预约时间"></el-table-column>
          <el-table-column prop="orderStatus" label="预约状态">
            <template slot-scope="scope">
              <el-tag type="primary" v-show="scope.row.orderStatus==0?true:false">待确定</el-tag>
              <el-tag type="success" v-show="scope.row.orderStatus==1?true:false">预约成功</el-tag>
              <el-tag type="danger" v-show="scope.row.orderStatus==2?true:false">预约失败</el-tag>
            </template>

          </el-table-column>
          <el-table-column label="操作" width="150px">
            <template slot-scope="scope">
              <el-button type="success" v-show="scope.row.orderStatus==0?true:false" size="mini" round
                         @click="okClick(scope.row)">接受
              </el-button>
              <el-button type="danger" v-show="scope.row.orderStatus==0?true:false" size="mini" round
                         @click="cancelClick(scope.row)">拒绝
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block">

          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page.sync="currentPage"
              :page-sizes="[10, 20, 50]"
              :page-size="pageSize"
              layout="total,sizes, prev, pager, next"
              :total="total">
          </el-pagination>
        </div>
      </el-row>

    </el-card>
    <el-dialog style="text-align: center" title="下单" :visible.sync="dialogForm" width="550px">
      <el-form :model="order" :rules="rules"  ref="order">
        <el-form-item label="手机号" label-width="120px" prop="phone">
          <el-input v-model="order.phone" ></el-input>
        </el-form-item>
        <el-form-item label="价格" label-width="120px" prop="price">
          <el-input v-model="order.price" ></el-input>
        </el-form-item>
        <el-form-item label="项目名" label-width="120px" prop="hairId">
          <el-select v-model="order.hairId"  @change="getHairType" placeholder="请选择" style="width: 100%">
            <el-option
                v-for="item in hairList"
                :key="item.hairId"
                :label="item.hairName"
                :value="item.hairId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="理发师" label-width="120px" prop="barberId">
          <el-select v-model="order.barberId"   placeholder="请选择" style="width: 100%">
            <el-option
                v-for="item in barberList"
                :key="item.barberId"
                :label="item.barberName"
                :value="item.barberId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="预约时间" label-width="120px" prop="stuId">
          <el-date-picker style="width: 100%"
              v-model="order.orderDate"
              type="date" value-format="yyyy-MM-dd"
              placeholder="选择日期">
          </el-date-picker>
<!--          <el-input v-model="order.orderDate" ></el-input>-->
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: center">
        <el-button @click="dialogForm = false">取 消</el-button>
        <el-button type="primary" @click="addOrder()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "order",
  data() {
    return {
      hairList:[],
      barberList:[],
      dialogForm:false,
      order:{},
      currentPage: 0,
      active: 0,
      phone: '',
      orderStatus: "primary",
      orderList: [],
      total: 10,
      pageSize: 10,
      page: 1,
      rules: {
        phone: [
          {required: true, message: '请输入内容', trigger: 'blur'},
          { message: '11位手机号', trigger: 'blur'}
        ],
        price:[
          {required: true, message: '请输入内容', trigger: 'blur'},
          {message: '请输入价格', trigger: 'blur'}
        ],
        hairId: [{required: true, message: '请输入内容', trigger: 'blur'}]

      }

    }
  },
  mounted(){
    this.getHairs();
    this.getList(this.page-1,this.pageSize)
  },
  methods: {
    async addOrder(){
      this.$refs.order.validate(async valid =>{
        if (!valid)return
        console.log(this.order)
        const res= await this.$axios.post('api/order/save',this.order)
        this.getList(this.page-1,this.pageSize)
        console.log(res)
      });

    },

    async getHairs() {
      const res= await this.$axios.get('api/hair/All')
      this.hairList=res.data.content
    },
    async getHairType(){
      const res= await this.$axios.get('api/barber/findHairId/'+this.order.hairId)
      // console.log()
      this.barberList=res.data.content
      // console.log(res)
    },
    async findByPhone() {
      if (this.phone=='')return
      console.log(this.phone)
      const res= await this.$axios.get('/api/order/findByPhone/'+this.phone);
      if (res.data.success){
        this.orderList=null;
        this.orderList=res.data.content
      }else {
        this.$message.warning(res.data.message)
      }
      console.log(res)
      // this.$message.success("接受订单")
      // console.log('接受:')
    },
    getList(page,size){
      const thas=this;
      this.$axios.get('/api/order/list', {
            params:{page:page, size:size}
          }
      ).then((res)=>{
        console.log(res);
        thas.total=res.data.content.total
        thas.orderList=res.data.content.list
      })
    },
    async okClick(e) {
      await this.$axios.get('/api/order/acceptOrder/'+e.orderId);
      this.getList(this.page-1,this.pageSize)
      this.$message.success("接受订单")
      // console.log(res)
      // console.log('接受:')
    },
    async cancelClick(e) {
      await this.$axios.get('/api/order/cancelOrder/'+e.orderId);
      this.$message.success("拒绝订单")
      this.getList(this.page-1,this.pageSize)
      // console.log('拒绝:')
      // console.log(e)
    },
    handleSizeChange(val) {
      this.getList(this.page-1,val)
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.getList(val-1,this.pageSize)
      console.log(`当前页: ${val}`);
    }
  }
}
</script>

<style scoped>

</style>
