<template>
  <div>
    <el-row class="card">
      <el-col :span="20" :offset="2">
        <el-container>
          <el-row>
            <el-col :span="5" :offset="2">
              <el-image
                :src="require('@/assets/lo.png')"
                style="width: 260px; height: 90px"
              ></el-image>
            </el-col>
            <el-col :span="7" :offset="7">
              <el-input
                v-model="search"
                @focus="focus"
                @blur="blur"
                @keyup.enter.native="searchHandler"
                placeholder="搜索书籍名"
                style="width: 600px; height: 20px"
              >
                <el-button
                  slot="append"
                  icon="el-icon-search"
                  id="search"
                  @click="searchHandler"
                ></el-button>
              </el-input>
              <!---设置z-index优先级,防止被走马灯效果遮挡-->
              <el-card
                @mouseenter="enterSearchBoxHanlder"
                v-if="isSearch"
                id="search-box"
                style="position: fixed; z-index: 15"
              >
                <div v-if="isHistorySearch">
                  <el-row>
                    <div class="search-title" v-show="history">历史搜索</div>
                    <div
                      class="remove-history"
                      v-show="history"
                      @click="removeAllHistory"
                    >
                      清空历史记录
                    </div>
                  </el-row>
                  <el-row>
                    <el-col :offset="0" :span="24">
                      <el-tag
                        v-for="search in historySearchList"
                        :key="search.id"
                        closable
                        class="word-tag"
                        :type="search.type"
                        @click.native="searchTag(search.name)"
                        @close="closeHandler(search)"
                        >{{ search.name }}</el-tag
                      >
                    </el-col>
                  </el-row>
                  <el-row>
                    <div class="search-title">热门搜索</div>
                  </el-row>
                  <el-row>
                    <div
                      v-for="search in hotSearchList"
                      :key="search.id"
                      class="topSearch"
                    >
                      {{ search }}
                    </div>
                  </el-row>
                </div>
                <div v-if="isSearchList">
                  <div
                    v-for="search in searchList"
                    :key="search.id"
                    class="topSearch"
                  >
                    {{ search }}
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>

          <!--            搜索框-->
        </el-container>
        <el-card class="card1">
          <el-row class="first">
            <!--            图书类别-->
            <el-col :span="8" :offset="1">
              <el-card class="card2">
                <div class="bookCat">图书类别</div>
                <!--                    <el-divider >图书类别</el-divider>-->
                <el-row v-for="kind in this.parent" :key="kind.name">
                  <span class="cate"> {{ kind.name }}>&nbsp; </span>
                  <span
                    class="cateChild"
                    v-for="child in kind.children"
                    :key="child.name"
                    @click="searchCategory(child.name)"
                  >
                    {{ child.name
                    }}<el-divider direction="vertical"></el-divider>
                  </span>
                  <!--                      <el-divider></el-divider>-->
                </el-row>
              </el-card>
            </el-col>
            <!--            轮播图图书推荐-->
            <el-col :span="7" :offset="1">
              <div class="block">
                <span class="demonstration"></span>
                <el-carousel
                  trigger="click"
                  height="350px"
                  width="400px"
                  class="ca"
                >
                  <el-carousel-item v-for="item in lunbos" :key="item.lunboId">
                    <img
                      :src="item.imgUrl"
                      class="adImage"
                      alt="推荐"
                      @click="intoLunbo(item.lunboId)"
                    />
                    <!--                    <el-image :src="item.idView" class="image" :fit="fit">-->
                    <!--                    </el-image>-->
                  </el-carousel-item>
                </el-carousel>
              </div>
            </el-col>
            <el-col :span="6" :offset="0">
              <div class="svgHover">
                <svg
                  :width="width"
                  :height="height"
                  @mousemove="listener($event)"
                  @click="goToLike"
                >
                  <a :href="tag.href" v-for="(tag, index) in tags" :key="index">
                    <text
                      :x="tag.x"
                      :y="tag.y"
                      :font-size="20 * (600 / (600 - tag.z))"
                      :fill-opacity="(400 + tag.z) / 600"
                      class="cloud"
                    >
                      {{ tag.text }}
                    </text>
                  </a>
                </svg>
              </div>
            </el-col>
          </el-row>
          <!--          推荐榜-->
          <el-row class="information">
            <div class="book">
              <Top></Top>
            </div>
          </el-row>
          <el-row class="information" id="like">
            <Like></Like>
          </el-row>
          <el-row class="information">
            <NewBook></NewBook>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { RandomUtil } from '../../utils/RandomUtil.js'
import { Store } from '../../utils/store.js'
import Top from '../MainPage/Top'
import Like from './Like'
import NewBook from './NewBook'
import Person from './Person'
import PersonLogin from './PersonLogin'
import { alertSuccess } from '../../utils/message'
export default {
  name: 'MainCard',
  components: {
    Top,
    Like,
    NewBook,
    Person,
    PersonLogin,
  },
  data() {
    return {
      width: 300, //svg宽度
      height: 300, //svg高度
      tagsNum: 20, //标签数量
      RADIUS: 80, //球的半径
      speedX: Math.PI / 720, //球一帧绕x轴旋转的角度
      speedY: Math.PI / 720, //球-帧绕y轴旋转的角度
      tags: [],
      kindList: ['教育', '小说', '名著'],
      imgList: [],
      search: '', //当前输入框的值
      isFocus: false, //是否聚焦
      hotSearchList: ['暂无热门搜索'], //热门搜索数据
      historySearchList: [], //历史搜索数据
      searchList: ['暂无数据'], //搜索返回数据,
      history: false,
      types: ['', 'success', 'info', 'warning', 'danger'], //搜索历史tag式样
      bannerHeight: '',
      flag: false,
      parent: [],
      lunbos: [],
    }
  },
  methods: {
    goToLike() {
      document.getElementById('like').scrollIntoView()
    },
    rotateX(angleX) {
      var cos = Math.cos(angleX)
      var sin = Math.sin(angleX)
      for (let tag of this.tags) {
        var y1 = (tag.y - this.CY) * cos - tag.z * sin + this.CY
        var z1 = tag.z * cos + (tag.y - this.CY) * sin
        tag.y = y1
        tag.z = z1
      }
    },
    rotateY(angleY) {
      var cos = Math.cos(angleY)
      var sin = Math.sin(angleY)
      for (let tag of this.tags) {
        var x1 = (tag.x - this.CX) * cos - tag.z * sin + this.CX
        var z1 = tag.z * cos + (tag.x - this.CX) * sin
        tag.x = x1
        tag.z = z1
      }
    },
    listener(event) {
      //响应鼠标移动
      var x = event.clientX - this.CX
      var y = event.clientY - this.CY
      this.speedX =
        x * 0.0001 > 0
          ? Math.min(this.RADIUS * 0.00002, x * 0.0001)
          : Math.max(-this.RADIUS * 0.00002, x * 0.0001)
      this.speedY =
        y * 0.0001 > 0
          ? Math.min(this.RADIUS * 0.00002, y * 0.0001)
          : Math.max(-this.RADIUS * 0.00002, y * 0.0001)
    },
    searchTag(val) {
      this.$router.push({
        path: '/AllBook',
        query: {
          t: 'book',
          val: val,
        },
      })
    },
    searchCategory(val) {
      this.$router.push({
        path: '/AllBook',
        query: {
          t: 'cat',
          val: val,
        },
      })
    },
    getLunBo() {
      this.$API
        .p_getLunBo({})
        .then((data) => {
          for (let i = 0; i < data.lunboList.length; i++) {
            let lunbo = data.lunboList[i]
            let temp = {
              lunboName: '',
              imgUrl: '',
              lunboId: '',
            }
            temp.lunboName = lunbo.lunboName
            temp.imgUrl = lunbo.imgUrl
            temp.lunboId = lunbo.lunboId
            this.lunbos.push(temp)
            console.log(this.lunbos)
          }
        })
        .catch((err) => {})
    },
    getFlag() {
      if (this.$store.state.userID === undefined) {
        this.flag = false
      } else {
        this.flag = true
      }
    },
    focus() {
      this.isFocus = true
      this.historySearchList =
        Store.loadHistory() == null ? [] : Store.loadHistory()
      this.history = this.historySearchList.length == 0 ? false : true
    },
    blur() {
      let self = this
      this.searchBoxTimeout = setTimeout(function () {
        self.isFocus = false
      }, 300)
    },
    enterSearchBoxHanlder() {
      clearTimeout(this.searchBoxTimeout)
    },
    searchHandler() {
      //随机生成搜索历史tag式样
      let n = RandomUtil.getRandomNumber(0, 5)
      let exist =
        this.historySearchList.filter((value) => {
          return value.name == this.search
        }).length == 0
          ? false
          : true
      if (!exist) {
        this.historySearchList.push({ name: this.search, type: this.types[n] })
        Store.saveHistory(this.historySearchList)
      }
      this.history = this.historySearchList.length == 0 ? false : true
      this.$router.push({
        path: '/AllBook',
        query: {
          t: 'book',
          val: this.search,
        },
      })
    },
    closeHandler(search) {
      this.historySearchList.splice(this.historySearchList.indexOf(search), 1)
      Store.saveHistory(this.historySearchList)
      clearTimeout(this.searchBoxTimeout)
      if (this.historySearchList.length == 0) {
        this.history = false
      }
    },
    removeAllHistory() {
      Store.removeAllHistory()
    },
    getCategories() {
      this.$API
        .p_getCategories({})
        .then((data) => {
          for (let i = 0; i < data.length; i++) {
            let parent = {
              name: '',
              children: [],
            }
            parent.name = data[i].name
            for (let j = 0; j < data[i].childCategory.length; j++) {
              let c = data[i].childCategory[j]
              let child = {
                name: '',
              }
              child.name = c.name
              parent.children.push(child)
            }
            this.parent.push(parent)
            console.log(this.parent)
          }
        })
        .catch((err) => {})
    },
    intoLunbo(val) {
      this.$router.push({
        path: '/activityPage',
        query: {
          lunboId: val,
        },
      })
    },
  },
  computed: {
    isHistorySearch() {
      return this.isFocus && !this.search
    },
    isSearchList() {
      return this.isFocus && this.search
    },
    isSearch() {
      return this.isFocus
    },
    setSize() {
      // 通过浏览器宽度(图片宽度)计算高度
      this.bannerHeight = (400 / 1920) * this.screenWidth
    },
    CX() {
      //球心x坐标
      return this.width / 2
    },
    CY() {
      //球心y坐标
      return this.height / 2
    },
  },
  mounted() {
    setInterval(() => {
      this.rotateX(this.speedX)
      this.rotateY(this.speedY)
    }, 17)
    this.getLunBo()
    this.getFlag()
    this.getCategories()
    // 首次加载时,需要调用一次
    this.screenWidth = window.innerWidth
    this.setSize()
    // 窗口大小发生改变时,调用一次
    window.onresize = () => {
      this.screenWidth = window.innerWidth
      this.setSize()
    }
  },
  created() {
    //初始化标签位置
    let tags = []
    this.$API
      .p_getCloud({
        id: this.$store.state.userID,
      })
      .then((data) => {
        for (let i = 0; i < data.tagList.length; i++) {
          let tag = {}
          let k = -1 + (2 * (i + 1) - 1) / this.tagsNum
          let a = Math.acos(k)
          let b = a * Math.sqrt(this.tagsNum * Math.PI) //计算标签相对于球心的角度
          tag.text = data.tagList[i]
          tag.x = this.CX + this.RADIUS * Math.sin(a) * Math.cos(b) //根据标签角度求出标签的x,y,z坐标
          tag.y = this.CY + this.RADIUS * Math.sin(a) * Math.sin(b)
          tag.z = this.RADIUS * Math.cos(a)
          // tag.href = 'https://imgss.github.io';//给标签添加链接
          tags.push(tag)
        }
        this.tags = tags //让vue替我们完成视图更新
      })
      .catch((err) => {})
  },
  render(h) {
    return h('div', {
      attrs: { id: 'word_cloud_view_id' },
      style: {
        height: '400px',
        width: '400px',
      },
    })
  },
}
</script>

<style scoped>
#search {
  background-color: #adc4f7;
  border-radius: 0%;
}
.word-tag {
  float: left;
  cursor: pointer;
}
.cloud {
  color: red;
}
.search-title {
  color: #bdbaba;
  font-size: 15px;
  float: left;
  margin-bottom: 3%;
}
.topSearch {
  float: left;
}
.remove-history {
  color: #bdbaba;
  font-size: 15px;
  float: right;
  margin-top: 1%;
  cursor: pointer;
}
.first {
  margin-top: 3%;
}

.information {
  margin-top: 5%;
}
.adImage {
  width: 100%;
  height: inherit;
  cursor: pointer;
}
#search-box {
  width: 45%;
  height: 10%;
  padding-bottom: 5%;
}
.cat:hover {
  text-decoration: underline;
  cursor: pointer;
}
.card1 {
  border-radius: 30px;
}
.card2 {
  border-radius: 30px;
  height: 350px;
  background-image: linear-gradient(#ffffcc, #ffffcc);
  /*background-color: #c6cfe2;*/
}
.card3 {
  border-radius: 30px;
}
.ca {
  border-radius: 30px;
}
.block {
  height: 350px;
}
.cate {
  float: left;
  font-weight: bold;
  margin-bottom: 16px;
}
.cateChild {
  /*float: left;*/
  cursor: pointer;
  margin-bottom: 16px;
}
.cateChild:hover {
  color: #3f9dfe;
}
.book {
  margin-left: 4px;
  margin-bottom: 4px;
}
.bookCat {
  color: #0b447f;
  margin-bottom: 20px;
  margin-left: 0;
  margin-right: 0;
  font-weight: bold;
}
.svgHover:hover {
  cursor: pointer;
}
</style>
