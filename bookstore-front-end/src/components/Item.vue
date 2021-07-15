<template>

  <el-container>

    <el-main>

    <el-card class="book">
      <div>
        <el-header>
          <div>{{this.name}}</div>
          <el-divider></el-divider>
        </el-header>
      </div>
      <el-container>
        <el-aside >
          <div class="photo1">
            <el-image   :src="imgUrl" ></el-image>
          </div>
        </el-aside>

        <el-main>
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span >{{ this.name }}</span>
            </div>
            <!--详情&表单-->
              <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="单价">
                  {{ this.price }}  元/本
                </el-form-item>
                <el-form-item label="出版社">
                  {{ this.publisher }}
                </el-form-item>
                <el-form-item label="所属分类">
                  {{ this.categoryId }}
                </el-form-item>
                <el-form-item label="销量">
                  {{ this.dealNum}}
                </el-form-item>
                <el-form-item label="数量">
                  <el-input-number v-model="num" @change="handleChange" :min="1" :max="this.inventory" label="描述文字">
                  </el-input-number>
                  库存{{this.inventory}}
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="onSubmit">购买</el-button>
                  <el-button type="primary" @click="onSubmit">加入购物车</el-button>
                </el-form-item>
              </el-form>
          </el-card>
        </el-main>
      </el-container>

    </el-card>
    <el-tabs type="border-card">
      <el-tab-pane label="图书详情">
        <el-divider>图书详情</el-divider>
        <span>书名：</span>
        <span>{{this.name}}</span>
        <el-divider direction="vertical"></el-divider>
        <span>图书编号：</span>
        <span>{{this.id}}</span>
        <el-divider direction="vertical"></el-divider>
        <span>出版社：</span>
        <span>{{this.publisher}}</span>
        <el-divider direction="vertical"></el-divider>
        <span>价格：</span>
        <span>{{this.price}}</span>
        <el-divider direction="vertical"></el-divider>
        <span>类别：</span>
        <span>{{this.categoryId}}</span>
        <el-divider>详情图</el-divider>
        <div class="photo1">
          <el-image :src="imgUrl"></el-image>
        </div>
      </el-tab-pane>
      <el-tab-pane label="评论">
        <el-divider>评论</el-divider>
        <el-table
            :data="tableData"
            style="width: 100%">
          <el-table-column
              prop="commentDetail"
              label="评论">
          </el-table-column>
          <el-table-column
              prop="score"
              label="评分">
          </el-table-column>
          <el-table-column
              prop="userId"
              label="买家">
          </el-table-column>
          <el-table-column
              prop="cTime"
              label="发表时间">
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: "Item",
  components:{
  },
  data(){
    return {
      form: {
      },
      tableData: [{
        commentDetail: '很好看',
        score:'5',
        userId: '王***',
        cTime: '2016-05-02'
      }, {
        commentDetail: '很好看',
        score:'4',
        userId: '王***',
        cTime: '2016-05-02'
      }],
      screenWidth :0,
      num: 1,
      //////////////////////
      storeName:'假qwe书店',//店名
      /////////////////////
      imgUrl:'http://images.amazon.com/images/P/0001047647.01.MZZZZZZZ.jpg',
      storeId:'',//店ID
      inventory:'',//库存
      publisher:'',
      name:'',
      state:'',
      price:'',
      dealNum:'',
      categoryId:'',//类别
    };
  },

  methods: {
    onSubmit() {
      console.log('submit!');
    },
    handleChange(value) {
      console.log(value);
    },
    getDetail(){

    }

  },
  mounted(){
    this.getDetail()
  }

}
</script>

<style scoped>


.el-carousel__item h3 {
  color: #475669;
  font-size: 30px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-image {
  width: 100%;
  height: 100%;
}

</style>