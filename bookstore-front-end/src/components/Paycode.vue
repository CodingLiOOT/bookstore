<template>
<el-container>
  <el-main>
    <el-card>
      <div class="photo1">
        <el-image :src="require('@/assets/pay.jpg')" >
        </el-image>
      </div>
      <el-button>支付完成</el-button>
      <el-button>返回</el-button>

    </el-card>
  </el-main>


</el-container>


</template>

<script>
export default {
  name: "Paycode",
  data() {
    return {

    }
  }
}
</script>

<style scoped>

</style>