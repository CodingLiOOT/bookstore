<template>
  <div>
    <el-row>
      <el-col :offset="2">
        <el-row class="row">
          <el-col :span="3" :offset="0">
            <img :src="require('../../assets/new.png')" class="new"/>
          </el-col>
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
  name: 'NewBook',
  components: {
    BookPreview,
  },
  data() {
    return {
      books: [],
    }
  },
  methods: {
    getNewBooks() {
      this.$API
        .g_getNewBooks({})
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
    this.getNewBooks()
  },
}
</script>

<style scoped>
.row {
  margin-bottom: 2%;
}
.new{
  width: 120px;
  height:50px
}
</style>
