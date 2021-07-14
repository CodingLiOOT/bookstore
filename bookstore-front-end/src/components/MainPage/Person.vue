<template>
  <div>
    <el-row class="information">
      <div class="block">
        <el-avatar :size="60" icon="el-icon-user-solid"> </el-avatar>
      </div>
    </el-row>
    <el-row class="information">
      欢迎登录！
      <el-row class="information">
        <el-col>
          <el-button type="primary" round size="small" @click="login()"
            >登录</el-button
          >
        </el-col>
        <el-col>
          <el-button type="primary" round size="small" @click="login()"
            >注册</el-button
          >
        </el-col>
      </el-row>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Person',
  methods: {
    login() {
      this.$router.push('/user/login')
    },
  },
}
</script>

<style scoped>
.information {
  margin-top: 5%;
}
.userHead {
  width: 77px;
  height: 77px;
}
</style>
