<template>
  <el-card :body-style="{ padding: '0px' }" class="bookCard" shadow="hover">
    <!--    <img :src="imgSrc" class="image">-->
    <el-image
      :src="imgSrc"
      :fit="fill"
      class="image"
      @click.native="openBook(bookId)"
    ></el-image>
    <div style="padding: 14px">
      <span v-if="bookName.length > 20">{{ bookName.slice(0, 15) }}...</span>
      <span v-else>{{ bookName }}</span>
      <div class="bottom clearfix">
        <el-row>
          <el-col :span="12">
            <el-button
              type="text"
              :disabled="disabled"
              round
              class="btn"
              @click="addToCart(bookId)"
              >加入购物车</el-button
            >
          </el-col>
          <el-col :span="12">
            <el-button
              type="text"
              :disabled="disabled"
              round
              class="btn"
              @click="openBook(bookId)"
              >立即购买</el-button
            >
          </el-col>
        </el-row>

        <!--        <el-button type="text" round class="btn" @click="modify(bookId)">修改</el-button>-->
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  name: 'BookPreview',
  data() {
    return {
      picture: 'http://images.amazon.com/images/P/0001010565.01.MZZZZZZZ.jpg',
      name: '',
    }
  },
  props: {
    //参数传递需要在props里面声明
    imgSrc: {
      type: String,
      default: 'http://images.amazon.com/images/P/0001010565.01.MZZZZZZZ.jpg',
    },
    bookName: {
      type: String,
      default: 'defaultName',
    },
    bookId: {
      type: String,
      default: 'defaultBookId',
    },
    disabled: false,
  },
  methods: {
    modify(val) {
      let books = [
        {
          id: val,
        },
      ]
      this.$API
        .p_shaixuan({ books })
        .then((data) => {})
        .catch((err) => {})
    },
    openBook(val) {
      this.$router.push({
        path: '/item',
        query: {
          bookId: val,
        },
      })
    },
    addToCart(val) {
      console.log(this.$store.state.userID === undefined)
      if (this.$store.state.userId === undefined) {
        this.$router.push({
          path: '/user/login',
        })
      } else {
        let books = [
          {
            id: val,
            num: 1,
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
  },
  mounted() {
    this.name = this.props.bookName
    this.disabled = this.$store.state.userID === undefined
  },
}
</script>

<style scoped>
.image {
  width: 200px;
  height: 250px;
}
.bottom {
  margin-top: 2%;
}
.bookCard {
  cursor: pointer;
}
.btn {
  /*width:60%;*/
  /*height:60%;*/
  font-size: 0.2rem;
}
</style>
