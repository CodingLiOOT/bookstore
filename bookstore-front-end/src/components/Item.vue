<template>
  <div>
    <el-row class="card">
      <el-col :span="20" :offset="2">
        <el-card class="book">
          <div>
            <el-header>
              <div>{{ this.book.bookName }}</div>
              <el-divider></el-divider>
            </el-header>
          </div>
          <el-container>
            <el-aside>
              <div class="photo1">
                <el-image :src="this.book.imgUrl"></el-image>
              </div>
            </el-aside>

            <el-main>
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span>{{ this.book.bookName }}</span>
                </div>
                <!--详情&表单-->
                <el-form ref="form" :model="this.form" label-width="80px">
                  <el-form-item label="单价">
                    {{ this.book.price }} 元/本
                  </el-form-item>
                  <el-form-item label="出版社">
                    {{ this.book.publisher }}
                  </el-form-item>
                  <el-form-item label="所属分类">
                    {{ this.book.categoryName }}
                  </el-form-item>
                  <el-form-item label="销量">
                    {{ this.book.dealNum }}
                  </el-form-item>
                  <el-form-item label="数量">
                    <el-input-number
                      v-model="num"
                      @change="handleChange"
                      :step="1"
                      :min="1"
                      :max="this.book.inventory"
                      label="描述文字"
                    >
                    </el-input-number>
                    库存{{ this.book.inventory }}
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="buy()">购买</el-button>
                    <el-button type="primary" @click="addToCart()"
                      >加入购物车</el-button
                    >
                  </el-form-item>
                </el-form>
              </el-card>
            </el-main>
          </el-container>
        </el-card>
        <el-tabs type="border-card">
          <el-tab-pane label="图书详情">
            <el-divider>图书详情</el-divider>
            <span>店铺名：</span>
            <span>{{ this.book.storeName }}</span>
            <el-divider direction="vertical"></el-divider>
            <span>书名：</span>
            <span>{{ this.book.bookName }}</span>
            <el-divider direction="vertical"></el-divider>
            <span>图书编号：</span>
            <span>{{ this.book.bookId }}</span>
            <el-divider direction="vertical"></el-divider>
            <span>出版社：</span>
            <span>{{ this.book.publisher }}</span>
            <el-divider direction="vertical"></el-divider>
            <span>价格：</span>
            <span>{{ this.book.price }}</span>
            <el-divider direction="vertical"></el-divider>
            <span>类别：</span>
            <span>{{ this.book.categoryName }}</span>
            <el-divider>详情图</el-divider>
            <div class="photo1">
              <el-image :src="this.book.imgUrl"></el-image>
            </div>
          </el-tab-pane>
          <el-tab-pane label="评论">
            <el-divider>评论</el-divider>
            <el-table :data="this.tableData" style="width: 100%">
              <el-table-column prop="comment" label="评论"> </el-table-column>
              <el-table-column prop="rate" label="评分">
                <template slot-scope="scope">
                  　　　　
                  <el-rate v-model="scope.row.rate" :colors="colors" disabled>
                  </el-rate>
                </template>
              </el-table-column>
              <el-table-column prop="userName" label="买家"> </el-table-column>
              <el-table-column prop="date" label="发表时间"> </el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Item',
  components: {},
  data() {
    return {
      form: {},
      book: {
        bookId: '', //书id
        imgUrl: '',
        storeName: '', //店名
        inventory: '', //库存
        publisher: '',
        name: '', //书名
        state: '',
        price: '',
        dealNum: '',
        categoryName: '', //类别名
      },
      tableData: [],
      screenWidth: 0,
      num: 1,
      colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
    }
  },

  methods: {
    getBookList() {
      let bookList = [
        {
          id: this.$route.query.bookId,
          num: this.num,
        },
      ]
      return bookList
    },
    buy() {
      if (this.disabled === true) {
        this.$router.push({
          path: '/user/login',
          query: {},
        })
      } else {
        let bkl = this.getBookList()
        this.$API
          .p_settlement(bkl)
          .then((data) => {
            let bklist = [
              {
                id: this.$route.query.bookId,
                bookName: this.book.bookName,
                price: this.book.price,
                bookNum: this.num,
              },
            ]
            let total = this.book.price * this.num
            this.$router.push({
              path: '/orderInfor',
              query: {
                bklist: bklist,
                bookList: bkl,
                total: total,
              },
            })
          })
          .catch((err) => {})
      }
    },
    addToCart() {
      if (this.disabled === true) {
        this.$router.push({
          path: '/user/login',
          query: {},
        })
      } else {
        let books = [
          {
            id: this.$route.query.bookId,
            num: this.num,
          },
        ]
        this.$API
          .p_addToCart({
            userId: this.$store.state.userID,
            books,
          })
          .then((data) => {
            this.$router.push({
              path: '/cart',
            })
          })
          .catch((err) => {})
      }
    },
    onSubmit() {
      console.log('submit!')
    },
    handleChange(value) {
      console.log(value)
    },
    getDetail() {
      this.$API
        .p_bookDetail({
          bookId: this.$route.query.bookId,
          userId: this.$store.state.userID,
        })
        .then((data) => {
          for (let i = 0; i < data.bookDetail.length; i++) {
            let book = data.bookDetail[i]
            let temp = this.book
            temp.bookId = book.id
            temp.imgUrl = book.imgUrl
            temp.storeName = book.storeName //店名
            temp.bookName = book.name
            temp.price = book.price
            temp.publisher = book.publisher
            temp.inventory = book.inventory
            temp.categoryName = book.categoryName
            temp.dealNum = book.dealNum
            console.log(temp)
          }
        })
        .catch((err) => {})
    },
    getComment() {
      this.$API
        .p_getCommentByBook({
          bookId: this.$route.query.bookId,
        })
        .then((data) => {
          for (let i = 0; i < data.commentList.length; i++) {
            this.tableData.push(data.commentList[i])
          }
        })
        .catch((err) => {})
    },
  },
  mounted() {
    this.getDetail()
    this.getComment()
    this.disabled = this.$store.state.userID === undefined
  },
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 30px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-image {
  width: 100%;
  height: 100%;
}
.book {
  margin-top: 10%;
}
</style>
