<template>
  <div>
    <el-row class="card">
      <el-col :span="18" :offset="3">
        <el-card shadow="hover">
          <!--            搜索框-->
          <el-row>
            <el-col :span="16" :offset="4">
              <el-input
                  v-model="search"
                  @focus="focus"
                  @blur="blur"
                  @keyup.enter.native="searchHandler"
                  placeholder="搜索书籍名"
              >
                <el-button slot="append" icon="el-icon-search" id="search" @click="searchHandler"></el-button>
              </el-input>
              <!---设置z-index优先级,防止被走马灯效果遮挡-->
              <el-card
                  @mouseenter="enterSearchBoxHanlder"
                  v-if="isSearch"
                  id="search-box"
                  style="position:fixed;z-index:15"
              >
                <div v-if="isHistorySearch">
                  <el-row>
                    <div class="search-title" v-show="history">历史搜索</div>
                    <div class="remove-history" v-show="history" @click="removeAllHistory">清空历史记录</div>
                  </el-row>
                  <el-row>
                    <el-col :offset="0" :span="24">
                      <el-tag
                          v-for="search in historySearchList"
                          :key="search.id"
                          closable
                          class="word-tag"
                          :type="search.type"
                          @close="closeHandler(search)"
                      >{{search.name}}</el-tag>
                    </el-col>

                  </el-row>
                  <el-row>
                    <div class="search-title">热门搜索</div>
                  </el-row>
                  <el-row>
                    <div v-for="search in hotSearchList" :key="search.id" class="topSearch">{{search}}</div>
                  </el-row>
                </div>
                <div v-if="isSearchList">
                  <div
                      v-for="search in searchList"
                      :key="search.id"
                      class="topSearch">
                    {{search}}
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
          <el-row class="first">
<!--            图书类别-->
            <el-col :span="6" :offset="0">
              <el-row>图书类别</el-row>
              <el-row
                  v-for="kind in kindList"
                  :key="kind.id"
                  class="bookKind">
                {{kind}}
              </el-row>
            </el-col>
<!--            图书推荐-->
            <el-col :span="12">
              <div class="block">
                <span class="demonstration"></span>
                <el-carousel trigger="click" height="350px" width="400px">
                  <el-carousel-item v-for="item in imgList" :key="item.id">
                    <img :src="item.idView" class="adImage" alt="推荐">
<!--                    <el-image :src="item.idView" class="image" :fit="fit">-->
<!--                    </el-image>-->
                  </el-carousel-item>
                </el-carousel>
              </div>
            </el-col>
            <el-col :span="6">
              <el-row class="information">
                <img src="../../assets/head.jpg" class="userHead" alt="头像" />
              </el-row>
              <el-row class="information">
                欢迎登录！
              </el-row>
              <el-row class="information">
                <el-col :span="8">
                  <el-button type="primary" round size="small">登录</el-button>
                </el-col>
                <el-col :span="8">
                  <el-button type="primary" round size="small">注册</el-button>
                </el-col>
                <el-col :span="8">
                  <el-button type="primary" round size="small">开店</el-button>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
<!--          推荐榜-->
          <el-row class="information">
            <Top></Top>
          </el-row>
          <el-row class="information">
            <Top></Top>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {RandomUtil} from "../../utils/RandomUtil.js"
import {Store} from "../../utils/store.js";
import Top from "../MainPage/Top"
export default {
  name: "MainCard",
  components:{
    Top
  },
  data() {
    return {
      kindList:['教育','小说','名著'],
      imgList: [
        {id:0,idView: require('../../assets/1.jpg')},
        {id:1,idView: require('../../assets/head.jpg')},
        {id:2,idView: require('../../assets/login_bg.jpg')},
        {id:3,idView: require('../../assets/logo.png')}
      ],
      search: "", //当前输入框的值
      isFocus: false, //是否聚焦
      hotSearchList: ["暂无热门搜索"], //热门搜索数据
      historySearchList: [], //历史搜索数据
      searchList: ["暂无数据"], //搜索返回数据,
      history: false,
      types: ["", "success", "info", "warning", "danger"] //搜索历史tag式样
    };
  },
  methods: {
    focus() {
      this.isFocus = true;
      this.historySearchList =
          Store.loadHistory() == null ? [] : Store.loadHistory();
      this.history = this.historySearchList.length == 0 ? false : true;
    },
    blur() {
      let self = this;
      this.searchBoxTimeout = setTimeout(function() {
        self.isFocus = false;
      }, 300);
    },
    enterSearchBoxHanlder() {
      clearTimeout(this.searchBoxTimeout);
    },
    searchHandler() {
      //随机生成搜索历史tag式样
      let n = RandomUtil.getRandomNumber(0, 5);
      let exist =
          this.historySearchList.filter(value => {
            return value.name == this.search;
          }).length == 0
              ? false
              : true;
      if (!exist) {
        this.historySearchList.push({ name: this.search, type: this.types[n] });
        Store.saveHistory(this.historySearchList);
      }
      this.history = this.historySearchList.length == 0 ? false : true;
    },
    closeHandler(search) {
      this.historySearchList.splice(this.historySearchList.indexOf(search), 1);
      Store.saveHistory(this.historySearchList);
      clearTimeout(this.searchBoxTimeout);
      if (this.historySearchList.length == 0) {
        this.history = false;
      }
    },
    removeAllHistory() {
      Store.removeAllHistory();
    }
  },
  computed: {
    isHistorySearch() {
      return this.isFocus && !this.search;
    },
    isSearchList() {
      return this.isFocus && this.search;
    },
    isSearch() {
      return this.isFocus;
    },
    setSize(){
      // 通过浏览器宽度(图片宽度)计算高度
      this.bannerHeight = 400 / 1920 * this.screenWidth;
    },
  },
  mounted() {
    // 首次加载时,需要调用一次
    this.screenWidth =  window.innerWidth;
    this.setSize();
    // 窗口大小发生改变时,调用一次
    window.onresize = () =>{
      this.screenWidth =  window.innerWidth;
      this.setSize();
    }
  }
}
</script>

<style scoped>
#search {
  background-color: #ffc300;
  border-radius: 0%;
}
.word-tag{
  float:left
}
.search-title {
  color: #bdbaba;
  font-size: 15px;
  float: left;
  margin-bottom: 2%;
}
.topSearch{
  float: left;
}
.remove-history {
  color: #bdbaba;
  font-size: 15px;
  float: right;
  margin-top: 1%;
  cursor: pointer;
}
.first{
  margin-top: 3%;
}
.userHead{
  width: 15%;
  height: 15%;
}
.bookKind{
  margin-right: 70%;
  margin-bottom: 3%;
}
.information{
  margin-top: 5%;
}
.adImage{
  width:100%;
  height:inherit;
}
#search-box {
  width: 45%;
  height: 10%;
  padding-bottom: 5%;
}
</style>
