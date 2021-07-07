<template>
  <div class="background">
    <el-container>
      <el-header class="el-header">
        <el-row>
          <el-col :span="6">
            <el-link type="primary">登录/注册</el-link>
          </el-col>
        </el-row>
      </el-header>
      <div class="card">
        <MainCard></MainCard>
      </div>
    </el-container>
  </div>
</template>

<script>
import MainCard from "./MainCard";
export default {
  components:{
    MainCard
  },
name: "MainPage"
}
</script>

<style scoped>
/*.background{*/
/*  position: absolute;*/
/*  top: 0;*/
/*  right: 0;*/
/*  bottom: 0;*/
/*  left: 0;*/
/*  overflow: hidden;*/
/*  background: rgba(38, 50, 56, 0.6) url(../../assets/login_bg.jpg) no-repeat;*/
/*  background-size: 100% 100%;*/
/*}*/
.background{
  width: 100%;
  background-size: 100%;
  min-height: 900px;
  padding-top: 30px;
  background: rgba(38, 50, 56, 0.6) url(../../assets/login_bg.jpg) no-repeat fixed top center;
}
.el-header{
  background-color: #e5e8f5;
  color: #333;
  text-align: center;
  line-height: 50px;
}
.card{
  margin-top: 3%;
}
</style>
