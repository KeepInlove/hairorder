<template>
    <div>
        <el-breadcrumb>
            <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>项目管理</el-breadcrumb-item>
            <el-breadcrumb-item>项目类型列表</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <el-row>
                <el-button style="margin-right: 30px;float: right" size="medium" type="primary" @click="remakeType(),dialogForm=true" round>添加</el-button>
            </el-row>
            <el-row style="margin-top: 5px">
                <el-table :data="typeList"  border>
                    <el-table-column type="index" label="#"></el-table-column>
                    <el-table-column prop="hairTypeName" width="300px"  label="理发师类型"></el-table-column>
                    <el-table-column prop="count"  width="200px"  label="项目数量"></el-table-column>
                    <el-table-column prop="createTime" width="250px" sortable label="创建时间"></el-table-column>
                    <el-table-column label="Action">
                        <template slot-scope="scope">
                            <el-button type="success"  size="mini" round   @click="editClick(scope.row),dialogForm=true">编辑</el-button>
                            <!--                            <el-button slot="reference">删除</el-button>-->
                            <el-button  type="danger"  size="mini" round  @click="del(scope.row)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-row>

        </el-card>

        <el-dialog style="text-align: center" title="新增/修改 信息" :visible.sync="dialogForm" width="500px">
            <el-form :model="hairType" :rules="rules" ref="hairType">
                <el-form-item label="理发类型昵称" label-width="120px" prop="hairTypeName">
                    <el-input v-model="hairType.hairTypeName"></el-input>
                </el-form-item>
                <el-form-item label="项目数量" label-width="120px">
                    <el-input v-model="hairType.count" disabled></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer" style="text-align: center">
                <el-button @click="dialogForm = false">取 消</el-button>
                <el-button type="primary" @click="updateUser(hairType)">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        name: "hairType",
        data(){
            return{
                typeList:[],
                dialogForm:false,
                hairType:{},
                visible: false,
                rules: {
                    hairTypeName: [
                        {required: true, message: '请输入内容', trigger: 'blur'},
                        {min: 2, max: 7, message: '长度在 2 到 7个字符', trigger: 'blur'}
                    ],
                },
            }
        },
        methods:{
            getHairType(){
                this.$axios.get('/api/hairType/list').then((res)=>{
                    console.log("<==============>");
                    console.log(res)
                    if (res.status!=200) return this.$message.error("网络错误");
                    this.typeList=res.data.content;

                })
            },
            editClick(e){
                // console.log(e);
                this.hairType=e;
            },
            updateUser(e){
                this.$refs.hairType.validate((valid) => {
                    if (valid) {
                        this.$axios.post('/api/hairType/save',e).then((res)=>{
                            // console.log(res)
                            if (res.data.success==true){
                                this.$notify({
                                    title: '成功',
                                    message: '添加成功!',
                                    type: 'success'
                                });
                                this.dialogForm=false;
                                this.getHairType();
                            }else {
                                this.$notify({
                                    title: '失败',
                                    message: '添加失败!',
                                    type: 'error'
                                });
                            }
                        });
                        // console.log(e)
                    } else {
                        // console.log('error submit!!');
                        return false;
                    }
                });

            },
            async  del(e){
                // console.log(e);
                if (e.count!=0){
                    this.$message.error("此分类有员工,无法删除!!")
                }else {
                    // 弹框询问用户是否删除数据
                    const confirmResult = await this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消删除'
                        })
                    });

                    // 如果用户确认删除，则返回值为字符串 confirm
                    // 如果用户取消删除，则返回值为字符串 cancel
                    if (confirmResult !== 'confirm') {
                        return this.$message.info('已取消删除')
                    }

                    // 发起删除用户信息的数据请求
                    const {data: res} = await this.$axios.delete('/api/hairType/delete/' + e.hairTypeId)
                    if (res.success!==true) {

                        return this.$message.error('删除用户失败！')
                    }
                    // 提示删除成功
                    this.$message.success('删除用户成功！')
                    // 更新数据列表
                    this.getHairType();
                }

            },
            remakeType(){
                this.hairType={};
            }
        },
        mounted(){
            this.getHairType()
        }
    }
</script>

<style scoped>

</style>
