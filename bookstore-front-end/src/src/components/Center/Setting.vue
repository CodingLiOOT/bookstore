<template>
  <el-row>
    <el-tabs
      :tab-position="tabPosition"
      v-model="activeName"
      @tab-click="handleClick"
    >
      <el-tab-pane label="基本信息" name="1">
        <el-row>
          <el-col :span="16" :offset="4">
            <el-form ref="form" :model="form">
              <el-form-item label="用户名">
                {{ form.name }}
                <!--                  <el-input v-model="form.name" class="inputSize"></el-input>-->
              </el-form-item>
              <el-form-item label="邮箱">
                {{ form.mail }}
                <!--                  <el-input v-model="form.mail" class="inputSize"></el-input>-->
              </el-form-item>
              <el-form-item label="手机号">
                <el-input v-model="form.phone" class="inputSize"></el-input>
              </el-form-item>
              <!--                <el-form-item label="生日">-->
              <!--                  <el-input v-model="form.birthday" class="inputSize" placeholder="yyyy/mm/dd"></el-input>-->
              <!--                </el-form-item>-->
              <el-form-item label="性别">
                <el-radio v-model="form.gender" :label="1">男</el-radio>
                <el-radio v-model="form.gender" :label="0">女</el-radio>
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
    </el-tabs>
  </el-row>
</template>

<script>
import Address from './Address'
export default {
  name: 'Setting',
  components: {
    Address,
  },
  data() {
    return {
      activeName: '1',
      tabPosition: 'left',
      birthday: '',
      form: {
        name: '',
        phone: '',
        mail: '',
        gender: 1,
        birthday: '',
      },
    }
  },
  methods: {
    handleClick(tab, event) {
      switch (tab.name) {
        case '1':
          this.getUserDetail()
          break
        case '2':
          Address.methods.getAllAddresses()
          break
        case '3':
          break
      }
    },
    onSubmit() {
      this.$API
        .p_modifyInformation({
          id: this.$store.state.userID,
          username: this.form.name,
          birthday: this.form.birthday,
          gender: this.form.gender,
          phone: this.form.phone,
        })
        .then((data) => {
          this.$notify({
            title: '成功',
            message: '修改个人信息成功',
            type: 'success',
          })
        })
        .catch((err) => {})
    },
    reset() {
      this.getUserDetail()
    },
    getUserDetail() {
      this.$API
        .p_getUserInfo({
          id: this.$store.state.userID,
        })
        .then((data) => {
          this.form.name = data.username
          this.form.gender = data.gender
          this.form.mail = data.mail
          this.form.phone = data.phone
        })
        .catch((err) => {})
    },
  },
  mounted() {
    this.getUserDetail()
  },
}
</script>

<style scoped>
.inputSize {
  width: 80%;
}
</style>
