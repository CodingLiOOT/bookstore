<template>
  <div id="app">
    <el-container>
      <div v-if="getUserState==='1'">
        <header1></header1>
      </div>
      <div v-else-if="getUserState==='2'">
        <Header2></Header2>
      </div>
      <div v-else>
        <Header></Header>
      </div>
    </el-container>
    <router-view/>
  </div>
</template>

<script>
import Header from "./components/Header";
import Header1 from "./components/Header1";
import Header2 from "./components/Header2";
export default {
  name: 'App',
  components:{
    Header,
    Header1,
    Header2
  },
  provide () {    //父组件中通过provide来提供变量，在子组件中通过inject来注入变量。
    return {
      reload: this.reload
    }
  },
  data() {
    return{
      flag: true,                //控制视图是否显示的变量
      type:1,
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
    },
    getUserState(){
      return this.$store.state.state;
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
  background-image: linear-gradient( #D1E8E2,#FFFFCC);
}
html,body{
  margin:0;
  padding:0;
}
</style>
