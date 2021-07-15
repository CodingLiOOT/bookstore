<template>
  <div>
    <el-row>
      <el-col :span="2" :offset="0">
        <!--所有商品全选-->
        <input type="checkbox" id="all" class="whole_check" />
        全选
      </el-col>
      <el-col :span="2" :offest="4">信息</el-col>
      <el-col :span="2" :offset="10">单价</el-col>
    </el-row>
    <div v-for="shop in this.fd.shops" :key="shop.storeName">
      <el-row class="infoCard">
        <div class="shopName">
          <input type="checkbox" id="shop_a" class="shopChoice" />
          {{ shop.storeName }}
        </div>
      </el-row>
      <el-card
        v-for="book in shop.books"
        :key="book.bookId"
        shadow="hover"
        class="cartBookCard"
      >
        <el-row>
          <el-col :span="2" :offset="0">
            <el-row>
              <el-col :span="2">
                <input type="checkbox" id="checkbox_2" class="son_check" />
              </el-col>
              <el-col :span="15" :offset="6">
                <img :src="book.imgSrc" :alt="picture" class="image" />
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="6" :offset="0">
            <div>{{ book.bookName }}</div>
          </el-col>
          <el-col :span="2" :offset="6">
            <div>{{ book.single }}</div>
          </el-col>
        </el-row>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Cart',
  components: {},
  data() {
    return {
      disabled: true,
      picture: '',
      activeName: 'first',
      fd: {
        shops: [],
        status: false,
        allchoose: '',
        allsum: '',
        allnum: 0,
      },
      Height: '',
    }
  },
  methods: {
    openBook(val) {
      this.$router.push({
        path: '/item',
        query: {
          bookId: val,
        },
      })
    },
    determine() {
      if (this.fd.allnum !== 0 && this.fd.allnum !== undefined) {
        this.disabled = false
      } else {
        this.disabled = true
      }
    },
    getCartList() {
      this.fd = {
        shops: [],
        status: false,
        allchoose: 0,
        allsum: 0,
        allnum: 0,
      }
      this.$API
        .p_getAllCart({
          id: this.$store.state.userID,
          state: 0,
        })
        .then((data) => {
          for (let j = 0; j < data.storeList.length; j++) {
            let s = data.storeList[j]
            let shop = {
              storeName: '',
              books: [],
              check: false, //店铺选中状态
              choose: 0, //商品选中个数
            }
            shop.storeName = s.storeName
            for (let i = 0; i < s.books.length; i++) {
              let temp = {
                bookId: '',
                bookName: '',
                single: 0,
                num: 0,
                imgSrc: '',
                total: 0,
                checked: false, //商品选中状态
                inventory: 0,
              }
              let book = s.books[i]
              temp.bookId = book.id
              temp.bookName = book.name
              temp.single = book.price
              temp.num = book.num
              temp.inventory = book.inventory
              temp.imgSrc = book.imgUrl
              temp.total = book.num * book.price
              temp.checked = false
              shop.books.push(temp)
            }
            console.log(shop)
            this.fd.shops.push(shop)
          }
          this.fd.status = false
          this.fd.allchoose = 0
          this.fd.allsum = 0
          this.fd.allnum = 0
          console.log(this.fd)
        })
        .catch((err) => {})
      console.log(this.fd)
    },
    getSelectedList() {
      let bookList = []
      for (let i = 0; i < this.fd.shops.length; i++) {
        for (let j = 0; j < this.fd.shops[i].books.length; j++) {
          let book = this.fd.shops[i].books[j]
          if (book.checked) {
            let temp = {
              id: '',
              num: 0,
            }
            temp.id = book.bookId
            temp.num = book.num
            bookList.push(temp)
          }
        }
      }
      return bookList
    },
    settlement() {
      let bkl = this.getSelectedList()

      this.$API
        .p_settlement(bkl)
        .then((data) => {})
        .catch((err) => {})
      let bklist = []
      let total = 0
      for (let i = 0; i < this.fd.shops.length; i++) {
        for (let j = 0; j < this.fd.shops[i].books.length; j++) {
          let book = this.fd.shops[i].books[j]
          if (book.checked) {
            let temp = {
              id: '',
              bookName: '',
              price: 0,
              bookNum: 0,
            }
            temp.id = book.bookId
            temp.bookNum = book.num
            temp.bookName = book.bookName
            temp.price = book.single
            temp.bookTotal = book.single * book.num
            total = total + temp.bookTotal
            bklist.push(temp)
          }
        }
      }
      this.$router.push({
        path: '/orderInfor',
        query: {
          bklist: bklist,
          bookList: bkl,
          total: total + 0.0,
        },
      })
    },
    deleteBooks() {
      let books = []
      for (let i = 0; i < this.fd.shops.length; i++) {
        for (let j = 0; j < this.fd.shops[i].books.length; j++) {
          let book = this.fd.shops[i].books[j]
          if (book.checked) {
            let temp = {
              id: '',
              // num:0,
            }
            temp.id = book.bookId
            // temp.num=book.num;
            books.push(temp)
          }
        }
      }
      this.$API
        .p_deleteFromCart({
          userId: this.$store.state.userID,
          books,
        })
        .then((data) => {
          this.getCartList()
        })
        .catch((err) => {})
    },
    handleClick(tab, event) {
      console.log(tab, event)
    },
    handleChange(bookId) {
      let num
      for (let i = 0; i < this.fd.shops.length; i++) {
        for (let j = 0; j < this.fd.shops[i].books.length; j++) {
          let book = this.fd.shops[i].books[j]
          if (book.bookId === bookId) {
            book.total = book.single * book.num
            num = book.num
          }
        }
      }
      this.getTotal()
      this.determine()
      let books = [
        {
          id: bookId,
          num: num,
        },
      ]
      this.$API
        .p_modifyNumFromCart({
          userId: this.$store.state.userID,
          books,
        })
        .then((data) => {})
        .catch((err) => {})
      console.log(bookId)
    },
    // 全选
    cartchoose() {
      this.fd.status = !this.fd.status //取反改变状态
      this.fd.status
        ? this.fd.shops.forEach((item) => this.shoptrue(item))
        : this.fd.shops.forEach((item) => this.shopfalse(item))
      this.getTotal()
      this.determine()
    }, //根据取反后的状态进行相应的店铺按钮操作
    // 店铺
    shoptrue(item) {
      item.books.forEach((pro) => {
        pro.checked === false ? this.choosetrue(item, pro) : ''
      })
    }, //循环店铺中的商品，先筛选出目前没选中的商品，给它执行choosetrue函数
    shopfalse(item) {
      item.books.forEach((pro) => {
        pro.checked === true ? this.choosefalse(item, pro) : ''
      })
    }, //循环店铺中的商品，先筛选出目前被选中的商品，给它执行choosefalse函数
    shopchoose(item) {
      !item.check ? this.shoptrue(item) : this.shopfalse(item)
      this.getTotal()
      this.determine()
    },
    //单个商品
    choosetrue(item, pro) {
      pro.checked = true //将商品选中状态改为true
      ++item.choose === item.books.length ? (item.check = true) : '' //这里执行了两部，选中商品数量先+1，再与该店铺商品数量比较，如果相等就更改店铺选中状态为true
      item.check
        ? ++this.fd.allchoose === this.fd.shops.length
          ? (this.fd.status = true)
          : (this.fd.status = false)
        : '' //如果店铺选中状态改为true，选中店铺数量先+1，再与店铺数量比较，如果相等就更改全选选中状态为true
    },
    choosefalse(item, pro) {
      pro.checked = false //将商品选中状态改为false
      --item.choose //选中商品数量-1
      if (item.check) {
        //如果店铺是被选中的，更改店铺选中状态
        item.check = false
        --this.fd.allchoose //并且选中店铺数量-1
      }
      this.fd.status = false //无论之前全选的状态，将其改为false就行
    },
    choose(item, pro) {
      !pro.checked ? this.choosetrue(item, pro) : this.choosefalse(item, pro)
      this.getTotal()
      this.determine()
    }, //这里是绑定到html上的方法，取反是由于你在触发方法的时候取的是之前的状态
    getTotal() {
      this.fd.allsum = 0
      this.fd.allnum = 0
      for (let i = 0; i < this.fd.shops.length; i++) {
        for (let j = 0; j < this.fd.shops[i].books.length; j++) {
          let book = this.fd.shops[i].books[j]
          if (book.checked) {
            this.fd.allnum++
            this.fd.allsum = this.fd.allsum + parseFloat(book.total)
          }
        }
      }
    },
  },
  mounted() {
    this.determine()
    this.getCartList()
    //动态设置内容高度 让footer始终居底   header+footer的高度是100
    this.Height = document.documentElement.clientHeight - 100
    //监听浏览器窗口变化
    window.onresize = () => {
      this.Height = document.documentElement.clientHeight - 100
    }
  },
}
</script>

<style scoped>
.card {
  margin-top: 65px;
}
.infoCard {
  margin-top: 2%;
}
.shopName {
  float: left;
  margin-bottom: 1%;
}
.image {
  width: 100%;
  height: 100%;
}
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
label.mark {
  background: url('../../assets/mark1.png') no-repeat -1px -1px;
}

a:hover {
  color: #ff8000;
}
.myFooter {
  /*#e5e8f5*/

  position: fixed;
  width: 85%;
  left: 7.5%;
  bottom: 0;
  z-index: 1000;
  border-radius: 30px;
}
.footer {
  border-radius: 30px;
}
.cartBookCard:hover {
  cursor: pointer;
}
</style>
