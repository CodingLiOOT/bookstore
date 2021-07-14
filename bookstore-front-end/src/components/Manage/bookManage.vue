<template>
  <el-container class="manage">
    <el-header>久柒图书电商后台管理系统</el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu :default-active="this.$router.path" router mode="vertical">
          <el-menu-item
            v-for="(item, i) in navList"
            :key="i"
            :index="item.name"
          >
            {{ item.navItem }}
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <el-tabs :tab-position="left">
            <el-tab-pane label="正常图书">
              <el-table :data="normalBooks" style="width: 100%">
                <el-table-column prop="imgUrl" label="预览" width="200">
                  <template slot-scope="scope">
                    　　　　
                    <img :src="scope.row.imgUrl" :alt="picture" class="image" />
                    　　</template
                  >
                </el-table-column>
                <el-table-column prop="name" label="书名" width="250">
                </el-table-column>
                <el-table-column prop="publisher" label="出版社" width="250">
                </el-table-column>
                <el-table-column prop="price" label="单价" sortable width="170">
                </el-table-column>
                <el-table-column prop="inventory" label="库存" width="170">
                </el-table-column>
                <el-table-column label="操作" width="170">
                  <template slot-scope="scope">
                    <el-button
                      @click="invalid(scope.row.id)"
                      type="text"
                      size="small"
                    >
                      设为违规
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination
                @current-change="handleValidChange"
                :current-page.sync="currentValid"
                :page-size="100"
                layout="prev, pager, next, jumper"
                :total="validTotal"
              >
              </el-pagination>
            </el-tab-pane>
            <el-tab-pane label="违规图书">
              <el-table :data="invalidBooks" style="width: 100%">
                <el-table-column prop="imgUrl" label="预览" width="200">
                  <template slot-scope="scope">
                    　　　　
                    <img :src="scope.row.imgUrl" :alt="picture" class="image" />
                    　　</template
                  >
                </el-table-column>
                <el-table-column prop="name" label="书名" width="250">
                </el-table-column>
                <el-table-column prop="publisher" label="出版社" width="250">
                </el-table-column>
                <el-table-column prop="price" label="单价" sortable width="170">
                </el-table-column>
                <el-table-column prop="inventory" label="库存" width="170">
                </el-table-column>
                <el-table-column label="操作" width="170">
                  <template slot-scope="scope">
                    <el-button
                      @click="valid(scope.row.id)"
                      type="text"
                      size="small"
                    >
                      取消违规
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination
                @current-change="handleInvalidChange"
                :current-page.sync="currentInvalid"
                :page-size="100"
                layout="prev, pager, next, jumper"
                :total="invalidTotal"
              >
              </el-pagination>
            </el-tab-pane>
          </el-tabs>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
import UserManage from './UserManage'
export default {
  name: 'Manage',
  data() {
    return {
      index: '',
      navList: [
        { name: '/userManage', navItem: '用户管理' },
        { name: '/bookManage', navItem: '图书管理' },
        // {name:'/LunboManage',navItem:'轮播管理'},
      ],
      normalBooks: [],
      invalidBooks: [],
      currentValid: 0,
      validTotal: 0,
      currentInvalid: 0,
      invalidTotal: 0,
      picture: '书预览图',
    }
  },
  components: {
    UserManage,
  },
  methods: {
    handleValidChange(val) {
      this.currentValid = val
      this.getValidUsers()
      console.log(`当前页: ${val}`)
    },
    handleInvalidChange(val) {
      this.currentInvalid = val
      this.getInvalidUsers()
      console.log(`当前页: ${val}`)
    },
    invalid(val) {
      this.$API
        .p_changeBookState({
          id: val,
          state: 0,
        })
        .then((data) => {
          this.$notify({
            title: '成功',
            message: '设置违规图书成功',
            type: 'success',
          })
          this.$router.go(0)
        })
        .catch((err) => {})
    },
    valid(val) {
      this.$API
        .p_changeBookState({
          id: val,
          state: 1,
        })
        .then((data) => {
          this.$notify({
            title: '成功',
            message: '取消违规图书成功',
            type: 'success',
          })
          this.$router.go(0)
        })
        .catch((err) => {})
    },
    getValidBooks() {
      this.$API
        .p_getRightBooks({
          state: 1,
          startNum: this.currentValid,
        })
        .then((data) => {
          this.validTotal = data.allNum
          for (let i = 0; i < data.bookList.length; i++) {
            let user = data.bookList[i]
            let temp = {
              id: '',
              name: '',
              publisher: '',
              inventory: 0,
              price: 0,
              imgUrl: '',
            }
            temp.id = user.id
            temp.name = user.name
            temp.publisher = user.publisher
            temp.inventory = user.inventory
            temp.price = user.price
            temp.imgUrl = user.imgUrl
            this.normalBooks.push(temp)
          }
        })
        .catch((err) => {})
    },
    getInvalidBooks() {
      this.$API
        .p_getRightBooks({
          state: 0,
          startNum: this.currentValid,
        })
        .then((data) => {
          this.validTotal = data.allNum
          for (let i = 0; i < data.bookList.length; i++) {
            let user = data.bookList[i]
            let temp = {
              id: '',
              name: '',
              publisher: '',
              inventory: 0,
              price: 0,
              imgUrl: '',
            }
            temp.id = user.id
            temp.name = user.name
            temp.publisher = user.publisher
            temp.inventory = user.inventory
            temp.price = user.price
            temp.imgUrl = user.imgUrl
            this.invalidBooks.push(temp)
          }
        })
        .catch((err) => {})
    },
  },
  mounted() {
    this.getInvalidBooks()
    this.getValidBooks()
  },
}
</script>

<style scoped>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.manage {
  margin-top: 65px;
}
.image {
  width: 100px;
  height: 130px;
}
</style>
