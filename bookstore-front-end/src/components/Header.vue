<template>
  <el-header class="el-header">
    <el-row>
      <el-col :span="6">
<!--        <el-link type="primary">登录/注册</el-link>-->
<!--        <el-link type="primary" :href="/Cart">购物车</el-link>-->
        <router-link :to="{path: '/user/login', params: { }}">登录/注册</router-link>
        <router-link :to="{path: '/Cart', params: {}}">购物车</router-link>
        <router-link :to="{path: '/mainPage', params: {}}">主页</router-link>
        <router-link :to="{path: '/CenterPage', params: {}}">个人中心</router-link>
      </el-col>
    </el-row>
  </el-header>
</template>

<script>
export default {
name: "Header"
}
</script>

<style scoped>
.el-header{
  background-color: #e5e8f5;
  color: #333;
  text-align: center;
  line-height: 50px;
  position: fixed;
  width: 100%;
  left: 0;
  top: 0;
  z-index: 1000;
}
</style>
