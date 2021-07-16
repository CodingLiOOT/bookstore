<template>
  <div>
    <el-row class="information">
      <img :src="this.userInfo.imgUrl" class="userHead" alt="头像" />
    </el-row>
    <el-row class="information">
      {{ this.userInfo.userName }}
    </el-row>
    <el-row class="information"> </el-row>
  </div>
</template>

<script>
export default {
  name: 'PersonLogin',
  data() {
    return {
      userInfo: {
        userName: '',
        imgUrl: '',
      },
    }
  },
  methods: {
    getInfo() {
      this.$API
        .p_getUserInfo({ id: this.$store.state.userID })
        .then((data) => {
          this.userInfo.userName = data.userName
          console.log(data.avatar)
          if (data.avatar != null) {
            this.userInfo.imgUrl = data.avatar
          } else {
            this.userInfo.imgUrl =
              'http://images.amazon.com/images/P/0001010565.01.MZZZZZZZ.jpg'
          }
        })
        .catch((err) => {})
    },
  },
  props: {
    //参数传递需要在props里面声明
    imgSrc: {
      type: String,
      default: 'http://images.amazon.com/images/P/0001010565.01.MZZZZZZZ.jpg',
    },
    userName: {
      type: String,
      default: 'userName',
    },
  },
  mounted() {
    this.getInfo()
  },
}
</script>

<style scoped>
</style>
