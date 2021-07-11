<template>
 <div>
<!--   删除某个地址-->
   <el-dialog
       title="提示"
       :visible.sync="deleteVisible"
       width="30%">
     <span>确认删除？</span>
     <span slot="footer" class="dialog-footer">
    <el-button @click="deleteVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
   </el-dialog>
<!--   修改收货地址-->
   <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
     <el-form :model="addressForm">
       <el-form-item label="地址">
         <el-input v-model="addressForm.address" autocomplete="off"></el-input>
       </el-form-item>
       <el-form-item label="收件人">
         <el-input v-model="addressForm.consigneeName" autocomplete="off"></el-input>
       </el-form-item>
       <el-form-item label="联系方式">
         <el-input v-model="addressForm.phone" autocomplete="off"></el-input>
       </el-form-item>
     </el-form>
     <div slot="footer" class="dialog-footer">
       <el-button @click="dialogFormVisible = false">取 消</el-button>
       <el-button type="primary" @click="submitModify">确 定</el-button>
     </div>
   </el-dialog>
<!--   添加收货地址-->
   <el-dialog title="收货地址" :visible.sync="addVisible">
     <el-form :model="addAddressForm">
       <el-form-item label="地址">
         <el-input v-model="addAddressForm.address" autocomplete="off"></el-input>
       </el-form-item>
       <el-form-item label="收件人">
         <el-input v-model="addAddressForm.consigneeName" autocomplete="off"></el-input>
       </el-form-item>
       <el-form-item label="联系方式">
         <el-input v-model="addAddressForm.phone" autocomplete="off"></el-input>
       </el-form-item>
     </el-form>
     <div slot="footer" class="dialog-footer">
       <el-button @click="dialogFormVisible = false">取 消</el-button>
       <el-button type="primary" @click="addAddress">确 定</el-button>
     </div>
   </el-dialog>
   <el-row>
     <el-table
         :data="tableData"
         border
         style="width: 100%">
       <el-table-column
           fixed
           prop="address"
           label="地址"
           width="500">
       </el-table-column>
       <el-table-column
           prop="addressId"
           label="编号"
           width="50">
       </el-table-column>
       <el-table-column
           prop="phone"
           label="手机号"
           width="180">
       </el-table-column>
       <el-table-column
           prop="consigneeName"
           label="收货人姓名"
           width="150">
       </el-table-column>
       <el-table-column
           label="操作"
           width="100">
         <template slot-scope="scope">
           <el-button @click="modifyAddr(scope.row.addressId)" type="text" size="small">修改</el-button>
           <el-button @click="openDelDialog(scope.row.addressId)" type="text" size="small" style="color: red">删除</el-button>
           <el-button type="text" size="small"></el-button>
         </template>
       </el-table-column>
     </el-table>
   </el-row>
   <el-row class="buto">
     <el-button type="primary" @click="addVisible = true">添加地址</el-button>
   </el-row>
 </div>
</template>

<script>
export default {
name: "Address",
  data(){
    return{
      deleteVisible:false,
      dialogFormVisible:false,
      addVisible:false,
      addAddressForm:{
        address:'',
        phone:'',
        consigneeName:'',
      },
      addressForm: {
        addressId:'',
        address:'',
        phone:'',
        consigneeName:'',
      },
      tableData: [
        {
          addressId:'1',
          address:'地址',
          phone:'123',
          consigneeName:'我',
        },
        {
          addressId:'2',
          address:'地址2',
          phone:'1234',
          consigneeName:'我2',
        }
      ],
      delAddressId:'',
    }
  },
  methods:{
    submitModify(){
      this.$API
          .p_modifyAddress({
            userId: this.$store.state.userID,
            shippingId:this.addressForm.addressId,
            consigneeName:this.addressForm.consigneeName,
            phone:this.addressForm.phone,
            address:this.addressForm.address
          })
          .then((data) => {

          })
          .catch((err) => {})
    },
    modifyAddr(val){
      for(let i=0;i<this.tableData.length;i++){
        let adr=this.tableData[i]
        if(val===adr.addressId){
          this.addressForm.address=adr.address
          this.addressForm.addressId=adr.addressId
          this.addressForm.consigneeName=adr.consigneeName
          this.addressForm.phone=adr.phone
        }
      }
      // 弹出窗口
      this.dialogFormVisible = true
    },
    getAllAddresses(){
      this.$API
          .p_getAddress({
            userId:this.$store.state.userID
          })
          .then((data) => {
            for (let i = 0; i < data.shippingList.length; i++) {
              let s = data.shippingList[i]
              let temp = {
                addressId:'',
                address:'',
                phone:'',
                consigneeName:'',
              }
              temp.addressId=s.addressId;
              temp.address=s.address;
              temp.phone=s.phone;
              temp.consigneeName=s.consigneeName;
              this.tableData.push(temp)
            }
          })
          .catch((err) => {})
    },
    addAddress(){
      this.$API
          .p_addAddress({
            userId: this.$store.state.userID,
            consigneeName:this.addressForm.consigneeName,
            phone:this.addressForm.phone,
            address:this.addressForm.address
          })
          .then((data) => {
            for (let i = 0; i < data.length; i++) {
              let book = data[i]
              let temp = {
                bookId: '',
                bookName: '',
                imgSrc: '',
              }
              temp.bookId = book.id
              temp.bookName = book.name
              temp.imgSrc = book.imgUrl
              this.books.push(temp)
            }
          })
          .catch((err) => {})
    },
    openDelDialog(val){
      this.delAddressId=val
      // 弹出窗口
      this.deleteVisible = true
    },
    deleteAddress(){
      this.$API
          .p_deleteAddress({
            shippingId:this.delAddressId
          })
          .then((data) => {
          })
          .catch((err) => {})
    },
  },
  mounted() {
    this.getAllAddresses()
  }
}
</script>

<style scoped>
.buto{
  margin-top: 2%;
}
</style>
