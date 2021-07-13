<template>
  <el-container class="manage">
    <el-header>久柒图书电商后台管理系统</el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu :default-active="this.$router.path" router mode="vertical">
          <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
            {{ item.navItem }}
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
import UserManage from "./UserManage";
export default {
name: "Manage",
  data(){
    return{
      index:'',
      navList:[
        {name:'/userManage',navItem:'用户管理'},
        {name:'/bookManage',navItem:'图书管理'},
        {name:'/LunboManage',navItem:'轮播管理'},
      ]
    }
  },
  components:{
    UserManage
  }
}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.manage{
  margin-top: 65px;
}
</style>
