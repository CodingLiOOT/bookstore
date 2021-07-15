<template>
  <div id="app">
    <el-container>
      <el-header class="el-header">
        <el-row>
          <el-col :span="6">
            <el-link type="primary">登录/注册</el-link>
          </el-col>
        </el-row>
      </el-header>
    </el-container>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  width: 100%;
  background-size: 100%;
  min-height: 1000px;
  background: rgba(38, 50, 56, 0.6) url(./assets/login_bg.jpg) no-repeat fixed top center;
}
html,body{
  margin:0;
  padding:0;
}
.el-header{
  background-color: #e5e8f5;
  color: #333;
  text-align: center;
  line-height: 50px;
}

</style>
