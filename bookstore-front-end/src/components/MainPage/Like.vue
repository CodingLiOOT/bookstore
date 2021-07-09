<template>
  <div>
    <el-row class="row">
      <el-col :span="3" :offset="2">
        猜你喜欢
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="5" v-for="(o, index) in 2" :key="o" :offset="index > 0 ? 2 : 0">
        <BookPreview bookName="矿金管理" imgSrc="http://images.amazon.com/images/P/0001010565.01.MZZZZZZZ.jpg"></BookPreview>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import BookPreview from "../../components/BookPreview";
export default {
  name: "Like",
  components:{
    BookPreview,
  }
}
</script>

<style scoped>
.row{
  margin-bottom: 3%;
}
</style>
