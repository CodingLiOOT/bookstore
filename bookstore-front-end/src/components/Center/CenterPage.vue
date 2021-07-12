<template>
  <el-row>
    <el-col :span="20" :offset="2">
      <el-card class="center" >
        <div>***个人中心***</div>
        <el-tabs :tab-position="left">
          <el-tab-pane label="订单列表">
            <el-tabs :tab-position="tabPosition" v-model="activeName" @tab-click="handleClick">
              <!-- 列表1 -->
              <el-tab-pane label="待付款" name="1">
                <el-table
                    ref="filterTable"
                    :data="tableData"
                    style="width: 100%">
                  <!--      下拉框信息列表-->
                  <el-table-column type="expand">
                    <template slot-scope="scope">
                      <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="详细信息：">
                          <el-table
                              :data="tableData[scope.$index].detailData"
                              style="width: 100%">
                            <el-table-column prop="id" label="图书编号" width="180"></el-table-column>
                            <el-table-column prop="shopName" label="店铺" width="180"></el-table-column>
                            <el-table-column prop="category" label="所属分类" width="180"></el-table-column>
                            <el-table-column prop="price" label="单价" width="180"></el-table-column>
                            <el-table-column prop="bookNum" label="数量" width="180"></el-table-column>
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
                      column-key="orderId">
                  </el-table-column>
                  <el-table-column
                      prop="date"
                      label="创建日期"
                      sortable
                      width="180"
                      column-key="date">
                  </el-table-column>
                  <el-table-column
                      prop="num"
                      label="总数"
                      width="100">
                  </el-table-column>
                  <el-table-column
                      prop="sum"
                      label="总价"
                      width="100">
                  </el-table-column>
                  <el-table-column
                      prop="state"
                      label="订单状态"
                      width="100">
                  </el-table-column>
                  <el-table-column label="操作" width="120">
                    <template slot-scope="scope">
                      <el-button type="text" size="small" @click="settle1(scope.row.orderId)">
                        去支付
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
                    style="width: 100%">
                  <!--      下拉框信息列表-->
                  <el-table-column type="expand">
                    <template slot-scope="props">
                      <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="详细信息：">
                          <el-table
                              :data="detailData"
                              style="width: 100%">
                            <el-table-column prop="id" label="图书编号" width="180"></el-table-column>
                            <el-table-column prop="shopName" label="店铺" width="180"></el-table-column>
                            <el-table-column prop="category" label="所属分类" width="180"></el-table-column>
                            <el-table-column prop="price" label="单价" width="180"></el-table-column>
                            <el-table-column prop="bookNum" label="数量" width="180"></el-table-column>
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
                      column-key="orderid">
                  </el-table-column>
                  <el-table-column
                      prop="date"
                      label="创建日期"
                      sortable
                      width="180"
                      column-key="date">
                  </el-table-column>
                  <el-table-column
                      prop="num"
                      label="总数"
                      width="100">
                  </el-table-column>
                  <el-table-column
                      prop="sum"
                      label="总价"
                      width="100">
                  </el-table-column>
                  <el-table-column
                      prop="state"
                      label="订单状态"
                      width="100">
                  </el-table-column>
                  <el-table-column label="操作" width="120">
                    <template slot-scope="scope">
                      <el-button
                          type="text"
                          size="small" plain @click="open1">
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
                    style="width: 100%">
                  <!--      下拉框信息列表-->
                  <el-table-column type="expand">
                    <template slot-scope="props">
                      <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="详细信息：">
                          <el-table
                              :data="detailData"
                              style="width: 100%">
                            <el-table-column prop="id" label="图书编号" width="180"></el-table-column>
                            <el-table-column prop="shopName" label="店铺" width="180"></el-table-column>
                            <el-table-column prop="category" label="所属分类" width="180"></el-table-column>
                            <el-table-column prop="price" label="单价" width="180"></el-table-column>
                            <el-table-column prop="bookNum" label="数量" width="180"></el-table-column>
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
                      column-key="orderid">
                  </el-table-column>
                  <el-table-column
                      prop="date"
                      label="创建日期"
                      sortable
                      width="180"
                      column-key="date">
                  </el-table-column>
                  <el-table-column
                      prop="num"
                      label="总数"
                      width="100">
                  </el-table-column>
                  <el-table-column
                      prop="sum"
                      label="总价"
                      width="100">
                  </el-table-column>
                  <el-table-column
                      prop="state"
                      label="订单状态"
                      width="100">
                  </el-table-column>
                  <el-table-column label="操作" width="120">
                    <template slot-scope="scope">
                      <el-button
                          type="text"
                          size="small" plain @click="open2">
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
                    style="width: 100%">
                  <!--      下拉框信息列表-->
                  <el-table-column type="expand">
                    <template slot-scope="props">
                      <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="详细信息：">
                          <el-table
                              :data="detailData"
                              style="width: 100%">
                            <el-table-column prop="id" label="图书编号" width="180"></el-table-column>
                            <el-table-column prop="shopName" label="书名" width="180"></el-table-column>
                            <el-table-column prop="category" label="所属分类" width="180"></el-table-column>
                            <el-table-column prop="price" label="单价" width="180"></el-table-column>
                            <el-table-column prop="bookNum" label="数量" width="180"></el-table-column>
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
                      column-key="orderId">
                  </el-table-column>
                  <el-table-column
                      prop="date"
                      label="创建日期"
                      sortable
                      width="180"
                      column-key="date">
                  </el-table-column>
                  <el-table-column
                      prop="num"
                      label="总数"
                      width="100">
                  </el-table-column>
                  <el-table-column
                      prop="sum"
                      label="总价"
                      width="100">
                  </el-table-column>
                  <el-table-column
                      prop="state"
                      label="订单状态"
                      width="100">
                  </el-table-column>
                  <el-table-column label="操作" width="120">
                    <template slot-scope="scope">
                      <el-button type="text" size="small" @click="comment(scope.row.orderId)">
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
                    style="width: 100%">
                  <!--      下拉框信息列表-->
                  <el-table-column type="expand">
                    <template slot-scope="props">
                      <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="详细信息：">
                          <el-table
                              :data="detailData"
                              style="width: 100%">
                            <el-table-column prop="id" label="图书编号" width="180"></el-table-column>
                            <el-table-column prop="shopName" label="店铺" width="180"></el-table-column>
                            <el-table-column prop="category" label="所属分类" width="180"></el-table-column>
                            <el-table-column prop="price" label="单价" width="180"></el-table-column>
                            <el-table-column prop="bookNum" label="数量" width="180"></el-table-column>
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
                      column-key="orderid">
                  </el-table-column>
                  <el-table-column
                      prop="date"
                      label="创建日期"
                      sortable
                      width="180"
                      column-key="date">
                  </el-table-column>
                  <el-table-column
                      prop="num"
                      label="总数"
                      width="100">
                  </el-table-column>
                  <el-table-column
                      prop="sum"
                      label="总价"
                      width="100">
                  </el-table-column>
                  <el-table-column
                      prop="state"
                      label="订单状态"
                      width="100">
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
import Setting from "./Setting";

export default {
  name: "CenterPage",
  components:{
    Setting,
  },
  data() {
    return {
      tabPosition: 'left',
      bookName:'',
      tableData:[],
      activeName:'1',
    };
  },
  methods: {
    handleClick(tab, event) {
      switch (tab.name){
        case "1":
          this.getUnPay()
          break;
        case "2":
          this.getUndelivered()
          break;
        case "3":
          this.getNotReceived()
          break;
        case "4":
          this.getNotEvaluated()
          break;
        case "5":
          this.getEvaluated()
          break;
      }
    },
    getUnPay(){
      this.tableData=[];
      // let cat={
      //   tableData:[{
      //     orderId:'111',
      //     date: '2021-11-11',
      //     num:'7',
      //     sum:'77',
      //     state: '待支付',
      //     detailData:[{
      //       bookName:'一本好书',
      //       id:'001',
      //       shopName:'新华书店',
      //       category:'天文',
      //       price:'23',
      //       bookNum:'4'
      //     },{
      //       bookName:'很好书',
      //       id:'002',
      //       shopName:'新华书店',
      //       category:'天文',
      //       price:'23',
      //       bookNum:'4'
      //     }]
      //   }]
      // };
      this.update(data);
      this.$API
          .p_getOrderList({
            id:this.$store.state.userID,
            state:1
          })
          .then((data) => {
            this.update(data);
          })
          .catch((err) => {})
    },

    getUndelivered(){
      this.tableData=[];
      // let cat={
      //   tableData:[{
      //     orderId:'111',
      //     date: '2021-11-11',
      //     num:'7',
      //     sum:'77',
      //     state: '待支付',
      //     detailData:[{
      //       bookName:'一本好书',
      //       id:'001',
      //       shopName:'新华书店',
      //       category:'天文',
      //       price:'23',
      //       bookNum:'4'
      //     },{
      //       bookName:'很好书',
      //       id:'002',
      //       shopName:'新华书店',
      //       category:'天文',
      //       price:'23',
      //       bookNum:'4'
      //     }]
      //   }]
      // };
      this.update(data);
      this.$API
          .p_getOrderList({
            id:this.$store.state.userID,
            state:2
          })
          .then((data) => {
            this.update(data);
          })
          .catch((err) => {})
    },

    getNotReceived() {
      this.tableData = [];
      // let cat={
      //   tableData:[{
      //     orderId:'111',
      //     date: '2021-11-11',
      //     num:'7',
      //     sum:'77',
      //     state: '待支付',
      //     detailData:[{
      //       bookName:'一本好书',
      //       id:'001',
      //       shopName:'新华书店',
      //       category:'天文',
      //       price:'23',
      //       bookNum:'4'
      //     },{
      //       bookName:'很好书',
      //       id:'002',
      //       shopName:'新华书店',
      //       category:'天文',
      //       price:'23',
      //       bookNum:'4'
      //     }]
      //   }]
      // };
      this.update(data);
      this.$API
          .p_getOrderList({
            id:this.$store.state.userID,
            state:3
          })
          .then((data) => {
            this.update(data);
          })
          .catch((err) => {})
    },

    getNotEvaluated(){
      this.tableData=[];
      // let cat={
      //   tableData:[{
      //     orderId:'111',
      //     date: '2021-11-11',
      //     num:'7',
      //     sum:'77',
      //     state: '待支付',
      //     detailData:[{
      //       bookName:'一本好书',
      //       id:'001',
      //       shopName:'新华书店',
      //       category:'天文',
      //       price:'23',
      //       bookNum:'4'
      //     },{
      //       bookName:'很好书',
      //       id:'002',
      //       shopName:'新华书店',
      //       category:'天文',
      //       price:'23',
      //       bookNum:'4'
      //     }]
      //   }]
      // };
      this.update(data);
      this.$API
          .p_getOrderList({
            id:this.$store.state.userID,
            state:4
          })
          .then((data) => {
            this.update(data);
          })
          .catch((err) => {})
    },

    getEvaluated(){
      this.tableData=[];
      // let cat={
      //   tableData:[{
      //     orderId:'111',
      //     date: '2021-11-11',
      //     num:'7',
      //     sum:'77',
      //     state: '待支付',
      //     detailData:[{
      //       bookName:'一本好书',
      //       id:'001',
      //       shopName:'新华书店',
      //       category:'天文',
      //       price:'23',
      //       bookNum:'4'
      //     },{
      //       bookName:'很好书',
      //       id:'002',
      //       shopName:'新华书店',
      //       category:'天文',
      //       price:'23',
      //       bookNum:'4'
      //     }]
      //   }]
      // };
      this.update(data);
      this.$API
          .p_getOrderList({
            id:this.$store.state.userID,
            state:5
          })
          .then((data) => {
            this.update(data);
          })
          .catch((err) => {})
    },

    settle1(val){
      console.log(val)
      this.$router.push({
        path: '/Paycode',
        query: {
          orderId: val
        }
      });
    },
    open1() {
      this.$notify({
        title: '成功',
        message: '提醒卖家发货成功',
        type: 'success'
      });
    },
    open2() {
      this.$notify({
        title: '成功',
        message: '确认收货成功',
        type: 'success'
      });
      this.$API
          .p_changestate({
            orderId:this.settle1(val),
            state:4
          })
          .catch((err) => {})
    },
    comment(val){
      this.$prompt('请输入评论', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({ value }) => {
        this.$message({
          type: 'success',
          message: '评论成功'
        });
        // this.$API
        //     .p_changestate({
        //       orderId:this.settle1(val),
        //       state:4
        //     })
        //     .catch((err) => {});
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },

    update(data){
      for (let i = 0; i < data.tableData.length; i++) {
        let d=data.tableData[i]
        let order={
          orderId:'',
          date: '',
          num:'',
          sum:'',
          state: '',
          detailData:[]
        }
        order.orderId=d.orderId;
        order.date=d.date;
        order.num=d.num;
        order.sum=d.sum;
        order.state=d.state;
        for(let j=0;j<d.detailData.length;j++){
          let dt=d.detailData[j];
          let temp={
            bookName:'',
            id:'',
            shopName:'',
            category:'',
            price:0,
            bookNum:0,
          }
          temp.bookName=dt.bookName;
          temp.id=dt.id;
          temp.shopName=dt.shopName;
          temp.category=dt.category;
          temp.price=dt.price;
          temp.bookNum=dt.bookNum;
          order.detailData.push(temp);
        }
        this.tableData.push(order);
      }
    },
  },
  mounted(){
    //动态设置内容高度 让footer始终居底   header+footer的高度是100
    //this.Height = document.documentElement.clientHeight - 100;
    //监听浏览器窗口变化　
    this.getUnPay()
  }

}
</script>

<style scoped>


input[type="checkbox"]{
  display: none;
}
label{
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
.center{
  margin-top: 10%;
}
</style>
