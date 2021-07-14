<template>

  <el-container class="page">
    <el-main>
      <el-card class="card5">
        <span>活动详情</span>
        <el-divider class="d1"></el-divider>
        <Top></Top>
<!--        <el-col :span="4" v-for="(o, index) in 10" :key="o" :offset="index === 0 ? 0 : (index%5 === 0 ? '0' : '1')">-->
<!--          <el-card class="book" :body-style="{ padding: '0px' }">-->
<!--            <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">-->
<!--            <div style="padding: 14px;">-->
<!--              <span>活动价</span>-->
<!--              <div class="bottom clearfix">-->
<!--                <time class="time">{{ currentDate }}</time>-->
<!--                <el-button type="text" class="button">购买</el-button>-->
<!--              </div>-->
<!--            </div>-->
<!--          </el-card>-->
<!--        </el-col>-->
      </el-card>
    </el-main>

  </el-container>


</template>

<script>
import Top from "./MainPage/Top";
export default {
  name: "activityPage",
  components:{
    Top
  },
  data() {
    return {
      src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
      currentDate: new Date(),
      tabPosition: 'right'
    }
  }
}
</script>

<style scoped>
.page{
  margin-top: 65px;
  background-color: #ff8000;
}
.card5{
  border-radius: 90px;
//background-color:  #FFEAA3;
}

.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
.book{
  border-radius: 10px;
}
.d1{

}
</style>