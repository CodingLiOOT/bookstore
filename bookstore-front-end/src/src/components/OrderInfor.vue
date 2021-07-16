<template>
  <el-container>
    <el-dialog title="地址" :visible.sync="addressDialogVisible">
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column fixed prop="address" label="地址" width="250">
        </el-table-column>
        <el-table-column prop="phone" label="手机号" width="180">
        </el-table-column>
        <el-table-column prop="consigneeName" label="收货人姓名" width="150">
        </el-table-column>
        <el-table-column label="操作" width="100">
          <template slot-scope="scope">
            <el-button
              @click="selectAddr(scope.row.addressId)"
              type="text"
              size="small"
              >选择</el-button
            >
            <el-button
              @click="modifyAddr(scope.row.addressId)"
              type="text"
              size="small"
              >修改</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-button type="primary" @click="addAddressVisible = true"
        >添加地址</el-button
      >
    </el-dialog>
    <el-dialog title="添加地址" :visible.sync="addAddressVisible">
      <el-form ref="form" :model="addForm" label-width="80px">
        <el-form-item label="地址">
          <el-input v-model="addForm.address"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="addForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="收件人">
          <el-input v-model="addForm.consigneeName"></el-input>
        </el-form-item>
      </el-form>
      <el-button type="primary" @click="addAddress">确认添加</el-button>
    </el-dialog>
    <el-dialog title="修改地址" :visible.sync="modifyAddressVisible">
      <el-form ref="form" :model="modifyForm" label-width="80px">
        <el-form-item label="地址">
          <el-input v-model="modifyForm.address"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="modifyForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="收件人">
          <el-input v-model="modifyForm.consigneeName"></el-input>
        </el-form-item>
      </el-form>
      <el-button type="primary" @click="submitModify">确认修改</el-button>
    </el-dialog>
    <el-main>
      <el-card class="orderCard">
        <el-steps
          :active="active"
          finish-status="success"
          align-center="align-center"
        >
          <el-step title="查看购物车"></el-step>
          <el-step title="拍下商品"></el-step>
          <el-step title="付款"></el-step>
          <el-step title="确认收货"></el-step>
          <el-step title="评价"></el-step>
        </el-steps>
      </el-card>
      <el-card>
        <el-form ref="form" model="form" label-width="150px">
          <el-form-item label="确定收货地址：">
            <div v-if="this.tableData.length > 0">
              <el-row>
                <el-col :span="12" :offset="2">
                  <el-card>
                    <el-row> 地址：{{ this.address.address }} </el-row>
                    <el-row> 收件人：{{ this.address.consigneeName }} </el-row>
                    <el-row> 联系方式：{{ this.address.phone }} </el-row>
                  </el-card>
                </el-col>
                <el-col :span="3" :offset="1">
                  <el-button @click="manage">管理收货地址</el-button>
                </el-col>
              </el-row>
            </div>
            <div v-else>
              <el-button @click="manage">管理收货地址</el-button>
            </div>
          </el-form-item>
          <el-form-item label="选择支付方式：">
            <el-radio v-model="pay" :label="1">微信</el-radio>
            <el-radio v-model="pay" :label="2">支付宝</el-radio>
          </el-form-item>
          <el-form-item label="总价：">
            {{ this.$route.query.total }}
          </el-form-item>
          <el-form-item label="订单详情">
            <el-table :data="this.detailData" style="width: 100%">
              <el-table-column
                prop="id"
                label="图书编号"
                width="180"
              ></el-table-column>
              <el-table-column
                prop="bookName"
                label="图书名称"
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
              <el-table-column
                prop="bookTotal"
                label="金额"
                width="180"
              ></el-table-column>
            </el-table>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submit">提交订单</el-button>
            <el-button @click="returnPage">取消</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: 'OrderInfor',
  data() {
    return {
      pay: 1,
      addAddressVisible: false,
      addressDialogVisible: false,
      modifyAddressVisible: false,
      addressData: [],
      active: 1,
      radio: 2,
      tableData: [],
      address: {},
      form: {},
      detailData: [
        {
          bookName: '一本好书',
          id: '001',
          shopName: '新华书店',
          category: '天文',
          price: '23',
          bookNum: '4',
          bookTotal: 0,
        },
        {
          bookName: '很好书',
          id: '002',
          shopName: '新华书店',
          category: '天文',
          price: '23',
          bookNum: '4',
          bookTotal: 0,
        },
      ],
      addForm: {
        address: '',
        phone: '',
        consigneeName: '',
      },
      modifyForm: {
        address: '',
        phone: '',
        consigneeName: '',
        addressId: '',
      },
    }
  },
  methods: {
    submitModify() {
      this.$API
        .p_modifyAddress({
          userId: this.$store.state.userID,
          id: this.modifyForm.addressId,
          consigneeName: this.modifyForm.consigneeName,
          phone: this.modifyForm.phone,
          address: this.modifyForm.address,
        })
        .then((data) => {
          this.modifyAddressVisible = false
          this.getData()
        })
        .catch((err) => {})
    },
    modifyAddr(val) {
      for (let i = 0; i < this.tableData.length; i++) {
        if (val === this.tableData[i].addressId) {
          let td = this.tableData[i]
          this.modifyForm.address = td.address
          this.modifyForm.addressId = td.addressId
          this.modifyForm.consigneeName = td.consigneeName
          this.modifyForm.phone = td.phone
        }
      }
      this.modifyAddressVisible = true
    },
    addAddress() {
      this.$API
        .p_addAddress({
          userId: this.$store.state.userID,
          consigneeName: this.addForm.consigneeName,
          phone: this.addForm.phone,
          address: this.addForm.address,
        })
        .then((data) => {
          this.addForm = {}
          this.addAddressVisible = false
          this.getData()
        })
        .catch((err) => {})
    },
    selectAddr(val) {
      for (let i = 0; i < this.tableData.length; i++) {
        if (val === this.tableData[i].addressId) {
          let td = this.tableData[i]
          this.address.address = td.address
          this.address.addressId = td.addressId
          this.address.consigneeName = td.consigneeName
          this.address.phone = td.phone
        }
      }
      this.addressDialogVisible = false
    },
    manage() {
      this.addressDialogVisible = true
    },
    onSubmit() {
      console.log('submit!')
      if (this.active++ > 4) this.active = 0
    },
    getData() {
      this.tableData = []
      this.detailData = this.$route.query.bklist
      this.$API
        .p_getAddress({
          userId: this.$store.state.userID,
        })
        .then((data) => {
          for (let i = 0; i < data.length; i++) {
            let s = data[i]
            let temp = {
              addressId: '',
              address: '',
              phone: '',
              consigneeName: '',
            }
            temp.addressId = s.id
            temp.address = s.address
            temp.phone = s.phone
            temp.consigneeName = s.consigneeName
            this.tableData.push(temp)
            console.log(this.tableData)
          }
          Object.assign(this.address, this.tableData[0])
        })
        .catch((err) => {})
    },
    returnPage() {
      if (window.history.length <= 1) {
        this.$router.push({ path: '/cart/settlement' })
        return false
      } else {
        this.$router.go(-1)
      }
    },
    submit(val) {
      let bookList = this.$route.query.bookList
      let total = this.$route.query.total
      let orderId
      this.$API
        .p_confirmOrder({
          userId: this.$store.state.userID,
          shippingId: this.address.addressId,
          bookList,
          payType: '1',
          totalPrice: total,
        })
        .then((data) => {
          orderId = data.orderId
          this.$router.push({
            path: '/Paycode',
            query: {
              orderId: orderId,
            },
          })
        })
        .catch((err) => {})
      console.log(val)
    },
  },
  mounted() {
    this.getData()
  },
}
</script>

<style scoped>
.orderCard {
  margin-top: 10%;
}
</style>
