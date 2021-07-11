<template>
  <el-container>
    <el-main >
      <el-card class="orderCard">
        <el-steps :active="active" finish-status="success" align-center="align-center">
          <el-step title="查看购物车"></el-step>
          <el-step title="拍下商品"></el-step>
          <el-step title="付款"></el-step>
          <el-step title="确认收货"></el-step>
          <el-step title="评价"></el-step>
        </el-steps>
      </el-card>
      <el-card>
        <el-form ref="form"  model="form" label-width="150px">
          <el-form-item label="确定收货地址：">
            <el-select v-model="form.region" placeholder="请选择收货地址">
              <el-option label="北京市北京市上园村三号" value="beijing"></el-option>
              <el-option label="北京市北京市上园村" value="beijing"></el-option>
            </el-select>
            <el-button>管理收货地址</el-button>
          </el-form-item>
          <el-form-item label="选择支付方式：">
            <el-radio-group v-model="form.resource">
              <el-radio :label="3">微信</el-radio>
              <el-radio :label="6">支付宝</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="总价：">
            {{this.$route.query.total}}
          </el-form-item>
          <el-form-item label="订单详情">
            <el-table
                :data="this.detailData"
                style="width: 100%">
              <el-table-column prop="bookId" label="图书编号" width="180"></el-table-column>
              <el-table-column prop="bookName" label="图书名称" width="180"></el-table-column>
              <el-table-column prop="price" label="单价" width="180"></el-table-column>
              <el-table-column prop="bookNum" label="数量" width="180"></el-table-column>
              <el-table-column prop="bookTotal" label="金额" width="180"></el-table-column>
            </el-table>
          </el-form-item>
          <el-form-item>
            <el-button type="primary"  @click="onSubmit">提交订单</el-button>
            <el-button>取消</el-button>
          </el-form-item>

        </el-form>

      </el-card>
    </el-main>

  </el-container>

</template>

<script>
export default {
  name: "OrderInfor",
  data() {
    return {
      active: 1,
      radio: 2,
      form: {
      },
      detailData:[{
          bookName:'一本好书',
          bookId:'001',
          shopName:'新华书店',
          category:'天文',
          price:'23',
          bookNum:'4',
          bookTotal:0,
        },{
          bookName:'很好书',
          bookId:'002',
          shopName:'新华书店',
          category:'天文',
          price:'23',
          bookNum:'4',
          bookTotal:0,
        }]

    };
  },
  methods: {
    onSubmit() {
      console.log('submit!');
      if (this.active++ > 4) this.active = 0;
    },
    getData(){
      let books=this.$route.query.bookList
      this.detailData=this.$route.query.bklist
      this.$API
          .p_confirmOrder({
            id:this.$store.state.userID,
            books,
          })
          .then((data) => {
          })
          .catch((err) => {})
    }
  },
  mounted() {
    this.getData()
  }
}
</script>

<style scoped>
.orderCard{
  margin-top: 10%;
}
</style>
