<template>
  <div>
    <el-row v-bind:style="{minHeight: Height+'px'}">
      <el-col :span="20" :offset="2">
        <el-card class="card">
          <!--          商品列表-->
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="全部商品" name="first">
              <el-row>
                <el-col :span="2" :offset="0">
                  <!--所有商品全选-->
                  <input type="checkbox" id="all" class="whole_check">
                  <label for="all" :class="fetchData.status?'mark':''" @click="cartchoose()"></label>
                  全选
                </el-col>
                <el-col :span="2" :offest="4">信息</el-col>
                <el-col :span="2" :offset="6">单价</el-col>
                <el-col :span="2" :offset="2">数量</el-col>
                <el-col :span="2" :offset="2">金额</el-col>
                <el-col :span="2" :offset="2">操作</el-col>
              </el-row>
              <div v-for="shop in fetchData.shops" :key="shop.shopId">
                <el-row class="infoCard">
                  <div class="shopName">
                    <input type="checkbox" id="shop_a" class="shopChoice">
                    <label for="shop_a" class="shop" :class="shop.check?'mark':''" @click="shopchoose(shop)"></label>
                    {{shop.shopName}}
                  </div>
                </el-row>
                <el-card v-for="book in shop.books" :key="book.bookId" shadow="hover">
                  <el-row>
                    <el-col :span="2" :offset="0">
                      <el-row>
                        <el-col :span="2">
                          <input type="checkbox" id="checkbox_2" class="son_check">
                          <label for="checkbox_2" :class="book.checked?'mark':''" @click="choose(shop,book)"></label>
                        </el-col>
                        <el-col :span="15" :offset="6">
                          <img :src="require('../../assets/'+book.imgSrc)" :alt="picture" class="image">
                        </el-col>
                      </el-row>
                    </el-col>
                    <el-col :span="2" :offset="0">
                      <div>{{book.bookName}}</div>
                    </el-col>
                    <el-col :span="2" :offset="6">
                      <div>{{book.single}}</div>
                    </el-col>
                    <el-col :span="2" :offset="2">
                      <div>
                        <el-input-number v-model="book.num" @change="handleChange(book.bookId)" :min="1" size="small" label="描述文字"></el-input-number>
                      </div>
                    </el-col>
                    <el-col :span="2" :offset="2">
                      <div>{{book.total}}</div>
                    </el-col>
                    <el-col :span="2" :offset="2">
                      <el-button type="text">删除</el-button>
                    </el-col>
                  </el-row>
                </el-card>
              </div>
            </el-tab-pane>
            <el-tab-pane label="已失效商品" name="second">

            </el-tab-pane>
          </el-tabs>
          <!--          底部-->
        </el-card>
      </el-col>
    </el-row>
    <el-footer class="myFooter">
      <div class="bar-wrapper">
        <div class="bar-right">
          <div class="piece">已选商品<strong class="piece_num">{{this.fetchData.allnum}}</strong>件</div>
          <div class="totalMoney">共计: <strong class="total_text">￥{{this.fetchData.allsum}}</strong></div>
          <div class="calBtn"><a href="javascript:;">结算</a></div>
        </div>
      </div>
    </el-footer>
  </div>
</template>

<script>
export default {
  name: "Cart",
  components:{
  },
  data() {
    return {
      picture:'',
      activeName: 'first',
      fetchData:{
        shops:[
          {
            shopId:1,
            shopName:'搜猎人艺术生活',
            books:[
              {
                bookId:101,
                bookName:'机试指南',
                single:480,
                num:1,
                imgSrc:'1.jpg',
                total:'480',
                checked:false//商品选中状态
              },
              {
                bookId:102,
                bookName:'机试指南',
                single:480,
                num:1,
                imgSrc:'1.jpg',
                total:'480',
                checked:false//商品选中状态
              },
              {
                bookId:103,
                bookName:'机试指南',
                single:480,
                num:1,
                imgSrc:'1.jpg',
                total:'480',
                checked:false//商品选中状态
              },
            ],
            check:false,//店铺选中状态
            choose:0,//商品选中个数
          },
          {
            shopId:2,
            shopName:'搜猎人艺术生活',
            books:[
              {
                bookId:104,
                bookName:'机试指南',
                single:480,
                num:1,
                imgSrc:'1.jpg',
                total:'480',
                checked:false//商品选中状态
              },
              {
                bookId:105,
                bookName:'机试指南',
                single:480,
                num:1,
                imgSrc:'1.jpg',
                total:'480',
                checked:false//商品选中状态
              },
            ],
            check:false,//店铺选中状态
            choose:0,//商品选中个数
          },
        ],
        status:false,//全选选中状态
        allchoose:0,//店铺选中个数
        allsum:0,//总计价格
        allnum:0//总计数量
      },
      Height:'',
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    handleChange(bookId) {
      // alert(bookId)
      for(let i=0;i<this.fetchData.shops.length;i++){
        for(let j=0;j<this.fetchData.shops[i].books.length;j++){
          let book=this.fetchData.shops[i].books[j]
          if(book.bookId===bookId){
            book.total=book.single*book.num
          }
        }
      }
      console.log(bookId);
    },
    // 全选
    cartchoose(){
      this.fetchData.status=!this.fetchData.status//取反改变状态
      this.fetchData.status?this.fetchData.shops.forEach((item)=>this.shoptrue(item)):this.fetchData.shops.forEach((item)=>this.shopfalse(item))
    },//根据取反后的状态进行相应的店铺按钮操作
    // 店铺
    shoptrue(item){
      item.books.forEach((pro)=>{
        pro.checked===false?this.choosetrue(item,pro):''
      })
    },//循环店铺中的商品，先筛选出目前没选中的商品，给它执行choosetrue函数
    shopfalse(item){
      item.books.forEach((pro)=>{
        pro.checked===true?this.choosefalse(item,pro):''
      })
    },//循环店铺中的商品，先筛选出目前被选中的商品，给它执行choosefalse函数
    shopchoose(item){
      !item.check?this.shoptrue(item):this.shopfalse(item)
    },
    //单个商品
    choosetrue(item,pro){
      pro.checked=true//将商品选中状态改为true
      ++item.choose===item.books.length?item.check=true:''//这里执行了两部，选中商品数量先+1，再与该店铺商品数量比较，如果相等就更改店铺选中状态为true
      item.check?++this.fetchData.allchoose===this.fetchData.shops.length?this.fetchData.status=true:this.fetchData.status=false:''//如果店铺选中状态改为true，选中店铺数量先+1，再与店铺数量比较，如果相等就更改全选选中状态为true
    },
    choosefalse(item,pro){
      pro.checked=false//将商品选中状态改为false
      --item.choose//选中商品数量-1
      if(item.check){//如果店铺是被选中的，更改店铺选中状态
        item.check=false
        --this.fetchData.allchoose//并且选中店铺数量-1
      }
      this.fetchData.status=false//无论之前全选的状态，将其改为false就行
    },
    choose(item,pro){
      !pro.checked?this.choosetrue(item,pro):this.choosefalse(item,pro)
    },//这里是绑定到html上的方法，取反是由于你在触发方法的时候取的是之前的状态
  },
  mounted(){
    //动态设置内容高度 让footer始终居底   header+footer的高度是100
    this.Height = document.documentElement.clientHeight - 100;
    //监听浏览器窗口变化　
    window.onresize = ()=> {this.Height = document.documentElement.clientHeight -100}
  }
}
</script>

<style scoped>
.card{
  margin-top: 10%;
}
.infoCard{
  margin-top: 2%;
}
.shopName{
  float: left;
  margin-bottom: 1%;
}
.image{
  width: 100%;
  height: 100%;
}
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
label.mark{
  background: url("../../assets/mark1.png") no-repeat -1px -1px;
}

a:hover {
  color: #ff8000;
}
.myFooter{
  /*#e5e8f5*/
  background-color: #e6e6e7;
  position: fixed;
  width: 85%;
  left: 7.5%;
  bottom: 0;
  z-index: 1000;
}
</style>
