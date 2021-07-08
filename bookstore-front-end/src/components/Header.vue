<template>
  <el-header class="el-header">
    <el-row>
      <el-col :span="6">
        <el-link type="primary">登录/注册</el-link>
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
}
</style>
