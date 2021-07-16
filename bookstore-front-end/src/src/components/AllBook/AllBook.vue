<template>
  <el-row>
    <el-col :span="16" :offset="4">
      <el-card class="allBookCard">
        <el-table :data="books" style="width: 100%">
          <!--          <el-table-column-->
          <!--              prop="bookId"-->
          <!--              label="编号"-->
          <!--              sortable-->
          <!--              width="120">-->
          <!--          </el-table-column>-->
          <el-table-column prop="imgUrl" label="预览" sortable width="120">
            <template slot-scope="scope">
              　　　　
              <img :src="scope.row.imgUrl" :alt="picture" class="pic" />
              　　</template
            >
          </el-table-column>
          <el-table-column prop="name" label="详细信息" sortable width="300">
            <template slot-scope="scope">
              <el-row>
                {{ scope.row.bookName }}
              </el-row>
              <el-row>
                {{ scope.row.publisher }}
              </el-row>
            </template>
          </el-table-column>
          <!--          <el-table-column-->
          <!--              prop="publisher"-->
          <!--              label="出版社"-->
          <!--              sortable-->
          <!--              width="180">-->
          <!--          </el-table-column>-->
          <el-table-column
            prop="categoryName"
            label="类别"
            sortable
            width="200"
          >
          </el-table-column>
          <el-table-column prop="price" label="单价" sortable width="180">
          </el-table-column>
          <el-table-column label="操作" width="100">
            <template slot-scope="scope">
              <el-button
                @click="modify(scope.row.bookId)"
                type="text"
                size="small"
                >查看</el-button
              >
              <!--              <el-button @click="modify1(scope.row.bookId)" type="text" size="small">修改</el-button>-->
              <el-button type="text" size="small"></el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          :page-size="100"
          layout="prev, pager, next, jumper"
          :total="total"
        >
        </el-pagination>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
import BookPreview from '../BookPreview'
export default {
  name: 'AllBook',
  data() {
    return {
      books: [],
      tableData: [],
      picture: '',
      currentPage: 1,
      total: 0,
      cat: '所有',
      content: '',
    }
  },
  components: {
    BookPreview,
  },
  methods: {
    modify(val) {
      // let books=[{
      //   id:val
      // }]
      // this.$API
      //     .p_shaixuan({books})
      //     .then((data) => {
      //     })
      //     .catch((err) => {})
      this.$router.push({
        path: '/item',
        query: {
          bookId: val,
        },
      })
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange(val) {
      this.currentPage = val
      // this.books=this.tableData.slice(this.currentPage,this.currentPage+5)
      this.getAllBooks()
      console.log(`当前页: ${val}`)
    },
    getAllBooks() {
      this.books = []
      let books = [
        {
          startNum: (this.currentPage - 1) * 10 + 1,
        },
      ]
      this.$API
        .p_getAllBooks({
          cartId: this.cat,
          books,
        })
        .then((data) => {
          this.total = data.allNum
          for (let i = 0; i < data.bookList.length; i++) {
            let book = data.bookList[i]
            let temp = {
              bookId: '',
              name: '',
              imgUrl: '',
              publisher: '',
              price: '',
              categoryName: '',
            }
            temp.bookId = book.id
            temp.bookName = book.name
            temp.imgUrl = book.imgUrl
            temp.publisher = book.publisher
            temp.price = book.price
            temp.categoryName = book.categoryName
            this.books.push(temp)
          }
        })
        .catch((err) => {})
    },
    getBySearch() {
      this.books = []
      let books = [
        {
          startNum: (this.currentPage - 1) * 10 + 1,
          name: this.content,
        },
      ]
      this.$API
        .p_search({
          books,
        })
        .then((data) => {
          this.total = data.allNum
          for (let i = 0; i < data.bookList.length; i++) {
            let book = data.bookList[i]
            let temp = {
              id: '',
              name: '',
              imgUrl: '',
              publisher: '',
              price: '',
              categoryName: '',
            }
            temp.bookId = book.id
            temp.bookName = book.name
            temp.imgUrl = book.imgUrl
            temp.publisher = book.publisher
            temp.price = book.price
            temp.categoryName = book.categoryName
            this.books.push(temp)
          }
        })
        .catch((err) => {})
    },
  },
  mounted() {
    let type = this.$route.query.t
    if (type === 'cat') {
      this.cat = this.$route.query.val
      this.getAllBooks()
    } else if (type === 'book') {
      this.content = this.$route.query.val
      this.getBySearch()
    } else {
      this.cat = '所有'
      this.getAllBooks()
    }
  },
}
</script>

<style scoped>
.allBookCard {
  margin-top: 65px;
}
.pic {
  height: 100px;
  width: 80px;
}
</style>
