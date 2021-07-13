<template>
  <el-row>
    <el-col :span="16" :offset="4">
      <el-card class="allBookCard">
        <el-table
            :data="books"
            style="width: 100%"
        >
          <el-table-column
              prop="imgUrl"
              label="预览"
              sortable
              width="120">
            <template slot-scope="scope">
              　　　　    <img :src="scope.row.imgUrl" :alt="picture" class="image">
              　　</template>
          </el-table-column>
          <el-table-column
              prop="name"
              label="书名"
              sortable
              width="180">
            <template slot-scope="scope">
              <el-row>
                {{scope.row.name}}
              </el-row>
              <el-row>
                {{scope.row.publisher}}
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
              width="180">
          </el-table-column>
          <el-table-column
              prop="price"
              label="单价"
              sortable
              width="180">
          </el-table-column>
        </el-table>
        <el-pagination

            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-size="100"
            layout="prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </el-card>
    </el-col>
  </el-row>

</template>

<script>
import BookPreview from "../BookPreview";
export default {
  name: "AllBook",
  data(){
    return{
      books:[],
      tableData:[],
      picture:'',
      currentPage:1,
      total:0,
      cat:'所有',
    }
  },
  components:{
    BookPreview
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.currentPage=val
      // this.books=this.tableData.slice(this.currentPage,this.currentPage+5)
      this.getAllBooks()
      console.log(`当前页: ${val}`);
    },
    getAllBooks(){
      let books=[{
        startNum:this.currentPage*10,
        endNum:this.currentPage*10+10,
      }]
      this.$API
          .p_getAllBooks({
            cartId:this.cat,
            books
          })
          .then((data) => {
            this.total=data.allNum/10+1
            for (let i = 0; i < data.bookList.length; i++) {
              let book = data.bookList[i]
              let temp = {
                id: '',
                name: '',
                imgUrl: '',
                publisher:'',
                price:'',
                categoryName:'',
              }
              temp.bookId = book.id
              temp.bookName = book.name
              temp.imgUrl = book.imgUrl
              temp.publisher=book.publisher
              temp.price=book.price
              temp.categoryName=book.categoryName
              this.books.push(temp)
            }
          })
          .catch((err) => {})
    },

  },
  mounted() {
    this.cat=this.$route.query.cat
    this.getAllBooks()
  }
}
</script>

<style scoped>
.allBookCard{
  margin-top: 10%;
}
.pic{
  height: 80px;
  width: 80px;
}
</style>
