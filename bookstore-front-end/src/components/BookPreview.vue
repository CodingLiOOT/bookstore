<template>
  <el-card :body-style="{ padding: '0px' }" @click.native="openBook(bookId)" class="bookCard" shadow="hover">
<!--    <img :src="imgSrc" class="image">-->
    <el-image :src="imgSrc" :fit="fill" class="image"></el-image>
    <div style="padding: 14px;">
      <span v-if="bookName.length>20">{{bookName.slice(0,15)}}...</span>
      <span v-else>{{bookName}}</span>
      <div class="bottom clearfix">
        <el-row>
          <el-col :span="12">
            <el-button type="text" round class="btn">加入购物车</el-button>
          </el-col>
          <el-col :span="12">
            <el-button type="text" round class="btn">立即购买</el-button>
          </el-col>
        </el-row>


<!--        <el-button type="text" round class="btn" @click="modify(bookId)">修改</el-button>-->
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "BookPreview",
  data(){
    return{
      picture:'http://images.amazon.com/images/P/0001010565.01.MZZZZZZZ.jpg',
      name:'',
    }
  },
  props: {//参数传递需要在props里面声明
    'imgSrc': {
      type: String,
      default: 'http://images.amazon.com/images/P/0001010565.01.MZZZZZZZ.jpg'
    },
    'bookName':{
      type: String,
      default:'defaultName',
    },
    'bookId':{
      type: String,
      default:'defaultBookId',
    }
  },
  methods: {
    modify(val){
      let books=[{
        id:val
      }]
      this.$API
          .p_shaixuan({books})
          .then((data) => {
          })
          .catch((err) => {})
    },
    openBook(val){
      this.$router.push({
        path: '/item',
        query: {
          bookId: val,
        }
      });
    }
  },
  mounted() {
    this.name=this.props.bookName
  }
}
</script>

<style scoped>
.image{
  width: 200px;
  height: 250px;
}
.bottom{
  margin-top: 2%;
}
.bookCard{
  cursor: pointer;
}
.btn{
  /*width:60%;*/
  /*height:60%;*/
  font-size: 0.2rem;
}
</style>
