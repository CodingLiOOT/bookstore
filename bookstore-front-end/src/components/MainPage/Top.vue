<template>
  <div>
    <el-row class="row">
      <el-col :span="3" :offset="0"> 销量榜 </el-col>
    </el-row>
    <el-row>
      <el-col
        :span="4"
        v-for="book in books"
        :key="book.bookId"
        :offset="books.length > 0 ? 0.5 : 0"
      >
        <BookPreview
            :bookId="book.bookId"
          :bookName="book.bookName"
          :imgSrc="book.imgSrc"
        ></BookPreview>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import BookPreview from '../BookPreview'
export default {
  name: 'Top',
  data() {
    return {
      books: [],
    }
  },
  components: {
    BookPreview,
  },
  methods: {
    getTopBooks() {
      this.$API
        .g_getTops({})
        .then((data) => {
          for (let i = 0; i < data.length; i++) {
            let book = data[i]
            let temp = {
              bookId: '',
              bookName: '',
              imgSrc: '',
            }
            temp.bookId = book.id
            temp.bookName = book.name
            temp.imgSrc = book.imgUrl
            this.books.push(temp)
          }
        })
        .catch((err) => {})
    },
  },
  mounted() {
    this.getTopBooks()
  },
}
</script>

<style scoped>
.row {
  margin-bottom: 3%;
}
</style>
