<template>
  <div id="app">
    <el-container>
      <div v-if="flag=getUserId">
        <Header></Header>
      </div>
      <div v-else>
        <Header1></Header1>
      </div>
    </el-container>
    <router-view/>
  </div>
</template>

<script>
import Header from "./components/Header";
import Header1 from "./components/Header1";

export default {
  name: 'App',
  components:{
    Header,
    Header1
  },
  provide () {    //父组件中通过provide来提供变量，在子组件中通过inject来注入变量。
    return {
      reload: this.reload
    }
  },
  data() {
    return{
      flag: true                    //控制视图是否显示的变量
    }
  },
  mounted() {
  },
  methods: {
    reload () {
      this.flag = true;            //先关闭，
      this.$nextTick(function () {
        this.flag = false;         //再打开
      })
    }
  },
  computed:{
    getUserId(){
      return this.$store.state.userID === undefined;
    }
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
  /*background: rgba(38, 50, 56, 0.6) url(./assets/bg4.jpg) no-repeat fixed top center;*/
  /*background-size: cover*/
  /*background-color: #f3f4f6;*/
  background-image: linear-gradient( #D1E8E2,#FFFFCC);
}
html,body{
  margin:0;
  padding:0;
}
.el-header{
  /*background-color: #e6e6e7;*/
  /*color: #333;*/
  /*text-align: center;*/
  /*line-height: 50px;*/
  /*position: fixed;*/
  /*width: 100%;*/
  /*left: 0;*/
  /*top: 0;*/
  /*z-index: 1000;*/
}

</style>
