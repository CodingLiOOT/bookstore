<template>
  <el-container class="page">
    <el-main>
      <el-card class="card5">
        <span>{{this.lunboName}}</span>
        <el-row>
          <el-col :span="16" :offset="4">
            <img :src="this.imgUrl" class="image" />
          </el-col>
        </el-row>
        <el-divider class="d1"></el-divider>
        <Top></Top>
      </el-card>
    </el-main>
  </el-container>
</template>

<script>
import Top from './MainPage/Top'
export default {
  name: 'activityPage',
  components: {
    Top,
  },
  data() {
    return {
      src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
      currentDate: new Date(),
      tabPosition: 'right',
      imgUrl:'',
      lunboId:'',
      lunboName:'',
    }
  },
  methods:{
    getOneLunbo(){
      this.$API
          .p_getOneLunbo({
            lunboId: this.lunboId,
          })
          .then((data) => {
            this.lunboName=data.lunbo.lunboName
            this.imgUrl=data.lunbo.imgUrl
          })
          .catch((err) => {})
    }
  },
  mounted() {
    this.lunboId=this.$route.query.lunboId
    this.getOneLunbo()
  }
}
</script>

<style scoped>
.page {
  margin-top: 65px;
  background-color: #ff8000;
}
.card5 {
  border-radius: 90px;
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

  margin-top: 20px;
  border-radius: 4%;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: '';
}

.clearfix:after {
  clear: both;
}
.image{
  width: 500px;
  height:400px;
}
</style>