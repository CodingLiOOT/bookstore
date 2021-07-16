<template>
  <div>
    <el-row>
      <el-col :offset="2">
        <el-row class="row">
          <el-col :span="3" :offset="0">
            <img :src="require('../../assets/like.png')" class="like"
          /></el-col>
        </el-row>
        <el-row>
          <el-col
            :span="4"
            v-for="book in books"
            :key="book.bookId"
            :offset="books.length > 0 ? 0.8 : 0"
          >
            <BookPreview
              :bookId="book.bookId"
              :bookName="book.bookName"
              :imgSrc="book.imgSrc"
            ></BookPreview>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import BookPreview from '../../components/BookPreview'
export default {
  name: 'Like',
  data() {
    return {
      books: [],
    }
  },
  components: {
    BookPreview,
  },
  methods: {
    getLikeBooks() {
      this.$API
        .g_getLikes({})
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
    this.getLikeBooks()
  },
}
</script>

<style scoped>
.row {
  margin-bottom: 2%;
}
.like {
  width: 120px;
  height: 50px;
}
</style>
