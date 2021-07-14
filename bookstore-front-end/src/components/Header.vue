<template>
  <el-header class="el-header">
    <el-row>
      <el-col :span="6">
        <!--        <el-link type="primary">登录/注册</el-link>-->
        <!--        <el-link type="primary" :href="/Cart">购物车</el-link>-->
        <router-link :to="{ path: '/user/login', params: {} }" class="r"
          >登录/注册</router-link
        >
        <router-link :to="{ path: '/mainPage', params: {} }" class="r"
          >主页</router-link
        >
        <router-link :to="{ path: '/Cart', params: {} }" class="r"
          >购物车</router-link
        >
        <router-link :to="{ path: '/CenterPage', params: {} }" class="r"
          >个人中心</router-link
        >
        <!--        <router-link v-if="flag=false" :to="{path: '/user/login', params: {}}"class="r" @click.native="quit">注销</router-link>-->
      </el-col>
    </el-row>
  </el-header>
</template>

<script>
import App from '../App'
export default {
  name: 'Header',
  data() {
    return {
      flag: true,
    }
  },
  methods: {
    quit() {
      // this.reload();
      this.$store.commit('logout')
    },
  },
  mounted() {
    this.flag = this.$store.state.userID === undefined
  },
}
</script>

<style scoped>
.router-link-active {
  text-decoration: none;
  color: #3f9dfe;
}
.el-header {
  background-color: #f3f3f4;
  color: #c1c9fc;
  text-align: center;
  line-height: 50px;
  position: fixed;
  width: 100%;
  border-radius: 30px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);

  left: 0;
  top: 0;
  z-index: 1000;
}
.r {
  text-decoration: none;
  color: #112234;
}
.r:hover {
  color: #3f9dfe;
}
</style>
