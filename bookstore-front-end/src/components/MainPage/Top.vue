<template>
  <div>
    <el-row class="row">
      <el-col :span="3" :offset="2">
        销量榜
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="5" v-for="book in books" :key="book.bookId" :offset="books.length > 0 ? 2 : 0">
        <BookPreview :bookName="book.bookName" :imgSrc="book.imgSrc"></BookPreview>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import BookPreview from "../BookPreview";
export default {
  name: "Top",
  data(){
    return{
      books:[],
    }
  },
  components:{
    BookPreview,
  },
  methods:{
    getTopBooks(){
      let t={
        bookId:'1',
        bookName:'机试指南',
        imgSrc:'http://images.amazon.com/images/P/0001010565.01.MZZZZZZZ.jpg',
      };
      this.books.push(t);
      this.$API.g_getTops({})
          .then((data) => {
            for(let i=0;i<data.topBooks.length;i++){
              let book=data.topBooks[i];
              let temp={
                bookId:'',
                bookName:'',
                imgSrc:'',
              }
              temp.bookId=book.id;
              temp.bookName=book.name;
              temp.imgSrc=book.imgUrl;
              this.books.push(temp)
            }
          })
          .catch((err) => {})
    },
  },
  mounted() {
    this.getTopBooks();
  }
}
</script>

<style scoped>
.row{
  margin-bottom: 3%;
}
</style>
