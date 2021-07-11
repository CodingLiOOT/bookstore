<template>
  <el-row>

      <el-tabs :tab-position="tabPosition"  v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="基本信息" name="1">
          <el-row>
            <el-col :span="16" :offset="4">
              <el-form ref="form" :model="form">
                <el-form-item label="用户名">
                  <el-input v-model="form.name" class="inputSize"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                  <el-input v-model="form.mail" class="inputSize"></el-input>
                </el-form-item>
                <el-form-item label="手机号">
                  <el-input v-model="form.phone" class="inputSize"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                  <el-radio v-model="form.gender" label=1>男</el-radio>
                  <el-radio v-model="form.gender" label=0>女</el-radio>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="onSubmit">修改</el-button>
                  <el-button @click="reset">重置</el-button>
                </el-form-item>
              </el-form>
            </el-col>
          </el-row>

        </el-tab-pane>
        <el-tab-pane label="地址管理" name="2">
         <Address></Address>
        </el-tab-pane>
        <el-tab-pane label="安全设置" name="3">

        </el-tab-pane>
      </el-tabs>
  </el-row>
</template>

<script>
import Address from "./Address";
export default {
  name: "Setting",
  components:{
      Address,
  },
  data() {
    return {
      activeName:'1',
      tabPosition:'left',
      birthday:'',
      form: {
        name: '',
        phone:'',
        mail:'',
        gender: 1,
      },
    }
  },
  methods: {
    handleClick(row) {
      console.log(row);
    },
    onSubmit() {
      this.$API
          .p_modifyInformation({
            ID:this.$store.state.userID,
            nickname:this.form.name,
            birthday:this.birthday,
            gender:this.gender,
            phone:this.phone
          })
          .then((data) => {

          })
          .catch((err) => {})
    },
    reset(){
      this.getUserDetail()
    },
    getUserDetail(){
      let d= {
        name: 'Nancy123',
        phone:'18518407537',
        mail:'nancy_wdi@163.com',
        gender: 1,
      }
      this.form.name=d.name
      this.form.gender=d.gender
      this.form.mail=d.mail
      this.form.phone=d.phone
      this.birthday=d.birthday
      this.$API
          .p_getUserInfo({
            ID:this.$store.state.userID
          })
          .then((data) => {
            this.form.name=data.userName
            this.form.gender=data.gender
            this.form.mail=data.mail
            this.form.phone=data.phone
          })
          .catch((err) => {})
    },
  },
  mounted() {
    this.getUserDetail()
  }
}
</script>

<style scoped>
.inputSize{
  width: 80%;
}
</style>
