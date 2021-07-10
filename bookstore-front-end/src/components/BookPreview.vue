<template>
  <el-card :body-style="{ padding: '0px' }">
    <img :src="imgSrc" :alt="picture" class="image">
    <div style="padding: 14px;">
      <span>{{bookName}}</span>
      <div class="bottom clearfix">
        <el-button type="warning" round size="small">加入购物车</el-button>
        <el-button type="danger" round size="small">立即购买</el-button>
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "BookPreview",
  data(){
    return{
      picture:'图书预览图'
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
    }
  },
  methods: {
  },

}
</script>

<style scoped>
.image{
  width: 50%;
  height: 50%;
}
.bottom{
  margin-top: 2%;
}
</style>
