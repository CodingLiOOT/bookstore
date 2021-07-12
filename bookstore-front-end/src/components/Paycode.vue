<template>
<el-container>
  <el-main>
    <el-card>
      <div class="photo1">
        <el-image :src="require('@/assets/pay.jpg')" >
        </el-image>
      </div>
      <div>订单编号：{{this.$route.query.orderId}}</div>
      <el-button @click="change">支付完成 </el-button>
      <el-button  @click="returnPage">返回</el-button>

    </el-card>
  </el-main>


</el-container>


</template>

<script>
export default {
  name: "Paycode",
  data() {

    return this.$route.query.orderId;
  },
  methods:{
    returnPage(){
      if (window.history.length <= 1) {
        this.$router.push({ path: "/mainPage" });
        return false;
      } else {
        this.$router.go(-1);

      }
    },
    change(){
      this.$API
          .p_changestate({
            orderId:this.$route.query.orderId,
            state:2
          })
          .catch((err) => {})
      this.$router.go(-1);

    },

  }

}
</script>

<style scoped>

</style>