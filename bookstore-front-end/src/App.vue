<template>
  <div id="app">
    <el-container>
      <Header></Header>
    </el-container>
    <router-view/>
  </div>
</template>

<script>
import Header from "./components/Header";
export default {
  name: 'App',
  components:{
    Header,
  }
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
  min-height: 900px;
  background: rgba(38, 50, 56, 0.6) url(./assets/bg4.jpg) no-repeat fixed top center;
  background-size: cover
}
html,body{
  margin:0;
  padding:0;
}
.el-header{
  background-color: #e6e6e7;
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
