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
    <el-button type="primary" @click="deleteAddress">确 定</el-button>
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
<!--       <el-table-column-->
<!--           prop="addressId"-->
<!--           label="编号"-->
<!--           width="50">-->
<!--       </el-table-column>-->
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
           width="130">
         <template slot-scope="scope">
           <el-button @click="modifyAddr(scope.row.addressId)" type="text" >修改</el-button>
           <el-button @click="openDelDialog(scope.row.addressId)" type="text"  style="color: red">删除</el-button>
         </template>
       </el-table-column>
     </el-table>
   </el-row>
   <el-row class="buto">
     <el-button type="primary" @click="add">添加地址</el-button>
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

      ],
      delAddressId:'',
    }
  },
  methods:{
    add(){
      this.addVisible = true
      this.addressForm={}
    },
    submitModify(){
      this.$API
          .p_modifyAddress({
            userId: this.$store.state.userID,
            id:this.addressForm.addressId,
            consigneeName:this.addressForm.consigneeName,
            phone:this.addressForm.phone,
            address:this.addressForm.address
          })
          .then((data) => {
            this.dialogFormVisible=false
            this.getAllAddresses()
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
      this.tableData=[]
      this.$API
          .p_getAddress({
            userId:this.$store.state.userID
          })
          .then((data) => {
            for (let i = 0; i < data.length; i++) {
              let s = data[i]
              let temp = {
                addressId:'',
                address:'',
                phone:'',
                consigneeName:'',
              }
              temp.addressId=s.id;
              temp.address=s.address;
              temp.phone=s.phone;
              temp.consigneeName=s.consigneeName;
              this.tableData.push(temp)
              console.log(this.tableData)
            }
          })
          .catch((err) => {})
    },
    addAddress(){
      this.$API
          .p_addAddress({
            userId: this.$store.state.userID,
            consigneeName:this.addAddressForm.consigneeName,
            phone:this.addAddressForm.phone,
            address:this.addAddressForm.address
          })
          .then((data) => {
            this.addAddressForm={}
            this.addVisible=false
            this.getAllAddresses()
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
            id:this.delAddressId
          })
          .then((data) => {
            this.deleteVisible=false
            this.getAllAddresses()
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
