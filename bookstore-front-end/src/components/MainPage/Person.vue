<template>
  <div>
    <el-row class="information">
      <img src="../../assets/head.jpg" class="userHead" alt="头像" />
    </el-row>
    <el-row class="information">
      欢迎登录！
      <el-row class="information">
        <el-col :span="8">
          <el-button type="primary" round size="small" @click="login()">登录</el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="primary" round size="small" @click="login()">注册</el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="primary" round size="small">开店</el-button>
        </el-col>
      </el-row>
    </el-row>

  </div>
</template>

<script>
export default {
name: "Person",
  methods:{
    login(){
      this.$router.push('/user/login')
    },
  },
}
</script>

<style scoped>
.information{
  margin-top: 5%;
}
.userHead{
  width: 15%;
  height: 15%;
}
</style>
