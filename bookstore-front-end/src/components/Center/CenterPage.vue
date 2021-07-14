<template>
  <el-row>
    <!--    评论dialog-->
    <el-dialog title="提示" :visible.sync="commentVisible" width="50%">
      <div v-for="book in books" :key="book.bookId">
        {{ book.bookName }}
        <el-form :model="book.commentForm">
          <el-form-item label="评价">
            <el-input
              v-model="book.commentForm.content"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="评分">
            <el-rate v-model="book.commentForm.rate" :colors="colors">
            </el-rate>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="commentVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitComment">确 定</el-button>
      </span>
    </el-dialog>
    <!--    查看评论dialog-->
    <el-dialog title="提示" :visible.sync="getCommentVisible" width="50%">
      <div v-for="book in booksCom" :key="book.bookId">
        <el-row>
          <el-col>
            {{ book.bookName }}
          </el-col>
        </el-row>
        <el-row>
          <el-form>
            <el-form-item label="评价">
              {{ book.comment }}
            </el-form-item>
            <el-form-item label="评分">
              <el-rate v-model="book.rate" :colors="colors"> </el-rate>
            </el-form-item>
          </el-form>
        </el-row>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="getCommentVisible = false">确 定</el-button>
      </span>
    </el-dialog>
    <el-col :span="20" :offset="2">
      <el-card class="center">
        <div>个人中心</div>
        <el-tabs :tab-position="left">
          <el-tab-pane label="订单列表">
            <el-tabs
              :tab-position="tabPosition"
              v-model="activeName"
              @tab-click="handleClick"
            >
              <!-- 列表1 -->
              <el-tab-pane label="待付款" name="1">
                <el-table
                  ref="filterTable"
                  :data="tableData"
                  style="width: 100%"
                >
                  <!--      下拉框信息列表-->
                  <el-table-column type="expand">
                    <template slot-scope="scope">
                      <el-form
                        label-position="left"
                        inline
                        class="demo-table-expand"
                      >
                        <el-form-item label="详细信息：">
                          <el-table
                            :data="tableData[scope.$index].detailData"
                            style="width: 100%"
                          >
                            <el-table-column
                              prop="bookName"
                              label="图书"
                              width="250"
                            ></el-table-column>
                            <el-table-column
                              prop="shopName"
                              label="店铺"
                              width="180"
                            ></el-table-column>
                            <el-table-column
                              prop="category"
                              label="所属分类"
                              width="180"
                            ></el-table-column>
                            <el-table-column
                              prop="price"
                              label="单价"
                              width="180"
                            ></el-table-column>
                            <el-table-column
                              prop="bookNum"
                              label="数量"
                              width="180"
                            ></el-table-column>
                          </el-table>
                        </el-form-item>
                      </el-form>
                    </template>
                  </el-table-column>
                  <el-table-column
                    prop="orderId"
                    label="订单编号"
                    sortable
                    width="180"
                    column-key="orderId"
                  >
                  </el-table-column>
                  <el-table-column
                    prop="moment(date).utcOffset(480).format('YYYY-MM-DD HH:mm:ss')"
                    label="创建日期"
                    sortable
                    width="180"
                    column-key="date"
                  >
                  </el-table-column>
                  <el-table-column prop="num" label="总数" width="100">
                  </el-table-column>
                  <el-table-column prop="sum" label="总价" width="100">
                  </el-table-column>
                  <el-table-column label="操作" width="200">
                    <template slot-scope="scope">
                      <el-button
                        type="text"
                        size="small"
                        @click="settle1(scope.row.orderId)"
                      >
                        去支付
                      </el-button>
                      <el-button
                        class="red"
                        type="text"
                        size="small"
                        @click="cancel(scope.row.orderId)"
                      >
                        取消支付
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
              <!-- 列表2 -->
              <el-tab-pane label="待发货" name="2">
                <el-table
                  ref="filterTable"
                  :data="tableData"
                  style="width: 100%"
                >
                  <!--      下拉框信息列表-->
                  <el-table-column type="expand">
                    <template slot-scope="props">
                      <el-form
                        label-position="left"
                        inline
                        class="demo-table-expand"
                      >
                        <el-form-item label="详细信息：">
                          <el-table
                            :data="tableData[props.$index].detailData"
                            style="width: 100%"
                          >
                            <el-table-column
                              prop="bookName"
                              label="图书"
                              width="250"
                            ></el-table-column>
                            <el-table-column
                              prop="shopName"
                              label="店铺"
                              width="180"
                            ></el-table-column>
                            <el-table-column
                              prop="category"
                              label="所属分类"
                              width="180"
                            ></el-table-column>
                            <el-table-column
                              prop="price"
                              label="单价"
                              width="180"
                            ></el-table-column>
                            <el-table-column
                              prop="bookNum"
                              label="数量"
                              width="180"
                            ></el-table-column>
                          </el-table>
                        </el-form-item>
                      </el-form>
                    </template>
                  </el-table-column>
                  <el-table-column
                    prop="orderId"
                    label="订单编号"
                    sortable
                    width="180"
                    column-key="orderid"
                  >
                  </el-table-column>
                  <el-table-column
                    prop="date"
                    label="创建日期"
                    sortable
                    width="180"
                    column-key="date"
                  >
                  </el-table-column>
                  <el-table-column prop="num" label="总数" width="100">
                  </el-table-column>
                  <el-table-column prop="sum" label="总价" width="100">
                  </el-table-column>
                  <el-table-column label="操作" width="120">
                    <template slot-scope="scope">
                      <el-button
                        type="text"
                        size="small"
                        plain
                        @click="open1(scope.row.orderId)"
                      >
                        提醒发货
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
              <!-- 列表3 -->
              <el-tab-pane label="待收货" name="3">
                <el-table
                  ref="filterTable"
                  :data="tableData"
                  style="width: 100%"
                >
                  <!--      下拉框信息列表-->
                  <el-table-column type="expand">
                    <template slot-scope="scope">
                      <el-form
                        label-position="left"
                        inline
                        class="demo-table-expand"
                      >
                        <el-form-item label="详细信息：">
                          <el-table
                            :data="tableData[scope.$index].detailData"
                            style="width: 100%"
                          >
                            <el-table-column
                              prop="bookName"
                              label="图书"
                              width="250"
                            ></el-table-column>
                            <el-table-column
                              prop="shopName"
                              label="店铺"
                              width="180"
                            ></el-table-column>
                            <el-table-column
                              prop="category"
                              label="所属分类"
                              width="180"
                            ></el-table-column>
                            <el-table-column
                              prop="price"
                              label="单价"
                              width="180"
                            ></el-table-column>
                            <el-table-column
                              prop="bookNum"
                              label="数量"
                              width="180"
                            ></el-table-column>
                          </el-table>
                        </el-form-item>
                      </el-form>
                    </template>
                  </el-table-column>
                  <el-table-column
                    prop="orderId"
                    label="订单编号"
                    sortable
                    width="180"
                    column-key="orderid"
                  >
                  </el-table-column>
                  <el-table-column
                    prop="date"
                    label="创建日期"
                    sortable
                    width="180"
                    column-key="date"
                  >
                  </el-table-column>
                  <el-table-column prop="num" label="总数" width="100">
                  </el-table-column>
                  <el-table-column prop="sum" label="总价" width="100">
                  </el-table-column>
                  <el-table-column label="操作" width="120">
                    <template slot-scope="scope">
                      <el-button
                        type="text"
                        size="small"
                        plain
                        @click="open2(scope.row.orderId)"
                      >
                        确认收货
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
              <!-- 列表4 -->
              <el-tab-pane label="待评价" name="4">
                <el-table
                  ref="filterTable"
                  :data="tableData"
                  style="width: 100%"
                >
                  <!--      下拉框信息列表-->
                  <el-table-column type="expand">
                    <template slot-scope="scope">
                      <el-form
                        label-position="left"
                        inline
                        class="demo-table-expand"
                      >
                        <el-form-item label="详细信息：">
                          <el-table
                            :data="tableData[scope.$index].detailData"
                            style="width: 100%"
                          >
                            <el-table-column
                              prop="bookName"
                              label="图书"
                              width="250"
                            ></el-table-column>
                            <el-table-column
                              prop="shopName"
                              label="书名"
                              width="180"
                            ></el-table-column>
                            <el-table-column
                              prop="category"
                              label="所属分类"
                              width="180"
                            ></el-table-column>
                            <el-table-column
                              prop="price"
                              label="单价"
                              width="180"
                            ></el-table-column>
                            <el-table-column
                              prop="bookNum"
                              label="数量"
                              width="180"
                            ></el-table-column>
                          </el-table>
                        </el-form-item>
                      </el-form>
                    </template>
                  </el-table-column>
                  <el-table-column
                    prop="orderId"
                    label="订单编号"
                    sortable
                    width="180"
                    column-key="orderId"
                  >
                  </el-table-column>
                  <el-table-column
                    prop="date"
                    label="创建日期"
                    sortable
                    width="180"
                    column-key="date"
                  >
                  </el-table-column>
                  <el-table-column prop="num" label="总数" width="100">
                  </el-table-column>
                  <el-table-column prop="sum" label="总价" width="100">
                  </el-table-column>
                  <el-table-column label="操作" width="120">
                    <template slot-scope="scope">
                      <el-button
                        type="text"
                        size="small"
                        @click="comment(scope.row.orderId)"
                      >
                        去评价
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
              <!-- 列表5 -->
              <el-tab-pane label="已评价" name="5">
                <el-table
                  ref="filterTable"
                  :data="tableData"
                  style="width: 100%"
                >
                  <!--      下拉框信息列表-->
                  <el-table-column type="expand">
                    <template slot-scope="scope">
                      <el-form
                        label-position="left"
                        inline
                        class="demo-table-expand"
                      >
                        <el-form-item label="详细信息：">
                          <el-table
                            :data="tableData[scope.$index].detailData"
                            style="width: 100%"
                          >
                            <el-table-column
                              prop="bookName"
                              label="图书"
                              width="250"
                            ></el-table-column>
                            <el-table-column
                              prop="shopName"
                              label="店铺"
                              width="180"
                            ></el-table-column>
                            <el-table-column
                              prop="category"
                              label="所属分类"
                              width="180"
                            ></el-table-column>
                            <el-table-column
                              prop="price"
                              label="单价"
                              width="180"
                            ></el-table-column>
                            <el-table-column
                              prop="bookNum"
                              label="数量"
                              width="180"
                            ></el-table-column>
                          </el-table>
                        </el-form-item>
                      </el-form>
                    </template>
                  </el-table-column>
                  <el-table-column
                    prop="orderId"
                    label="订单编号"
                    sortable
                    width="180"
                    column-key="orderid"
                  >
                  </el-table-column>
                  <el-table-column
                    prop="date"
                    label="创建日期"
                    sortable
                    width="180"
                    column-key="date"
                  >
                  </el-table-column>
                  <el-table-column prop="num" label="总数" width="100">
                  </el-table-column>
                  <el-table-column prop="sum" label="总价" width="100">
                  </el-table-column>

                  <el-table-column label="操作" width="120">
                    <template slot-scope="scope">
                      <el-button
                        type="text"
                        size="small"
                        @click="getComment(scope.row.orderId)"
                      >
                        查看评价
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
            </el-tabs>
          </el-tab-pane>
          <el-tab-pane label="个人信息">
            <Setting></Setting>
          </el-tab-pane>
        </el-tabs>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
import Setting from './Setting'

export default {
  name: 'CenterPage',
  components: {
    Setting,
  },
  data() {
    return {
      tabPosition: 'left',
      bookName: '',
      tableData: [
        { orderId: '', date: '', num: '', sum: '', state: '', detailData: [] },
      ],
      activeName: '1',
      commentVisible: false,
      getCommentVisible: false,
      commentForm: {
        content: '',
        rate: '',
      },
      colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
      books: [],
      orderId: '',
      booksCom: [],
    }
  },
  methods: {
    cancel(val) {
      this.$API
        .p_changestate({
          id: val,
          state: 0,
        })
        .then((data) => {
          this.$notify({
            title: '成功',
            message: '取消支付成功',
            type: 'success',
          })
          this.getUnPay()
        })
        .catch((err) => {})
    },
    getComment(val) {
      this.booksCom = []
      this.getCommentVisible = true
      this.$API
        .p_getCommentByOrder({
          orderId: val,
        })
        .then((data) => {
          for (let i = 0; i < data.commentList.length; i++) {
            let c = data.commentList[i]
            this.booksCom.push(c)
          }
        })
        .catch((err) => {})
    },
    handleClick(tab, event) {
      switch (tab.name) {
        case '1':
          this.getUnPay()
          break
        case '2':
          this.getUndelivered()
          break
        case '3':
          this.getNotReceived()
          break
        case '4':
          this.getNotEvaluated()
          break
        case '5':
          this.getEvaluated()
          break
      }
    },
    submitComment(val) {
      let comment = {
        userId: this.$store.state.userID,
        orderId: this.orderId,
        bookList: [],
      }
      for (let i = 0; i < this.books.length; i++) {
        let temp = {
          id: '',
          comment: '',
          rate: '',
        }
        temp.id = this.books[i].bookId
        temp.comment = this.books[i].commentForm.content
        temp.rate = this.books[i].commentForm.rate
        comment.bookList.push(temp)
      }
      this.$API
        .p_comment(comment)
        .then((data) => {
          this.$notify({
            title: '成功',
            message: '评价成功',
            type: 'success',
          })
          this.$API
            .p_changestate({
              id: this.orderId,
              state: 5,
            })
            .catch((err) => {})
        })
        .catch((err) => {})
      this.commentVisible = false
    },
    getUnPay() {
      this.tableData = []
      this.$API
        .p_getOrderList({
          id: this.$store.state.userID,
          state: 1,
        })
        .then((data) => {
          this.update(data)
        })
        .catch((err) => {})
    },

    getUndelivered() {
      this.tableData = []
      this.$API
        .p_getOrderList({
          id: this.$store.state.userID,
          state: 2,
        })
        .then((data) => {
          this.update(data)
        })
        .catch((err) => {})
    },

    getNotReceived() {
      this.tableData = []
      this.$API
        .p_getOrderList({
          id: this.$store.state.userID,
          state: 3,
        })
        .then((data) => {
          this.update(data)
        })
        .catch((err) => {})
    },

    getNotEvaluated() {
      this.tableData = []
      this.$API
        .p_getOrderList({
          id: this.$store.state.userID,
          state: 4,
        })
        .then((data) => {
          this.update(data)
        })
        .catch((err) => {})
    },

    getEvaluated() {
      this.tableData = []
      this.$API
        .p_getOrderList({
          id: this.$store.state.userID,
          state: 5,
        })
        .then((data) => {
          this.update(data)
        })
        .catch((err) => {})
    },

    settle1(val) {
      console.log(val)
      this.$router.push({
        path: '/Paycode',
        query: {
          orderId: val,
        },
      })
    },
    open1(val) {
      this.$notify({
        title: '成功',
        message: '提醒卖家发货成功',
        type: 'success',
      })
      this.$API
        .p_changestate({
          id: val,
          state: 3,
        })
        .catch((err) => {})
    },
    open2(val) {
      this.$notify({
        title: '成功',
        message: '确认收货成功',
        type: 'success',
      })
      this.$API
        .p_changestate({
          id: val,
          state: 4,
        })
        .catch((err) => {})
    },
    comment(val) {
      this.orderId = val
      this.commentVisible = true
      this.books = []
      for (let i = 0; i < this.tableData.length; i++) {
        if (val === this.tableData[i].orderId) {
          for (let j = 0; j < this.tableData[i].detailData.length; j++) {
            let temp = {
              bookId: '',
              bookName: '',
              commentForm: {},
            }
            temp.bookId = this.tableData[i].detailData[j].id
            temp.bookName = this.tableData[i].detailData[j].bookName
            this.books.push(temp)
          }
        }
      }
    },

    update(data) {
      for (let i = 0; i < data.length; i++) {
        let d = data[i]
        let order = {
          orderId: '',
          date: '',
          num: '',
          sum: '',
          state: '',
          detailData: [],
        }
        order.orderId = d.orderId
        order.date = d.date
        order.num = d.num
        order.sum = d.sum
        order.state = d.state
        for (let j = 0; j < d.detailData.length; j++) {
          let dt = d.detailData[j]
          let temp = {
            bookName: '',
            id: '',
            shopName: '',
            category: '',
            price: 0,
            bookNum: 0,
          }
          temp.bookName = dt.bookName
          temp.id = dt.bookId
          temp.shopName = dt.shopName
          temp.category = dt.category
          temp.price = dt.price
          temp.bookNum = dt.bookNum
          order.detailData.push(temp)
        }
        this.tableData.push(order)
      }
    },
  },
  mounted() {
    //动态设置内容高度 让footer始终居底   header+footer的高度是100
    //this.Height = document.documentElement.clientHeight - 100;
    //监听浏览器窗口变化
    this.getUnPay()
  },
}
</script>

<style scoped>
input[type='checkbox'] {
  display: none;
}
label {
  position: relative;
  display: inline-block;
  z-index: 1;
  border: 1px solid #b8b8b8;
  margin-left: 10px;
  border-radius: 1px;
  width: 12px;
  height: 12px;
  cursor: pointer;
}

a:hover {
  color: #ff8000;
}
.center {
  margin-top: 65px;
}
.red {
  color: red;
}
</style>
