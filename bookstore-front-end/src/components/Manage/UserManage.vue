<template>
  <el-container class="manage">
    <el-header>久柒图书电商后台管理系统</el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu :default-active="this.$router.path" router mode="vertical">
          <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
            {{ item.navItem }}
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <el-tabs :tab-position="left">
            <el-tab-pane label="正常用户">
              <el-table
                  :data="normalUsers"
                  style="width: 100%"
                  >
                <el-table-column
                    fixed
                    prop="username"
                    label="用户名"
                    width="320">
                </el-table-column>
                <el-table-column
                    fixed
                    prop="mail"
                    label="邮箱"
                    width="320">
                </el-table-column>
                <el-table-column
                    prop="createdDate"
                    label="注册时间"
                    width="320">
                </el-table-column>
                <el-table-column
                    label="操作"
                    width="300">
                  <template slot-scope="scope">
                    <el-button @click="invalid(scope.row.id)" type="text" size="small">
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
                  :total="validTotal">
              </el-pagination>
            </el-tab-pane>
            <el-tab-pane label="违规用户">
              <el-table
                  :data="invalidUsers"
                  style="width: 100%"
                  >
                <el-table-column
                    fixed
                    prop="username"
                    label="用户名"
                    width="320">
                </el-table-column>
                <el-table-column
                    fixed
                    prop="mail"
                    label="邮箱"
                    width="320">
                </el-table-column>
                <el-table-column
                    prop="createdDate"
                    label="注册时间"
                    width="320">
                </el-table-column>
                <el-table-column
                    label="操作"
                    width="300">
                  <template slot-scope="scope">
                    <el-button @click="valid(scope.row.id)" type="text" size="small">
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
                  :total="invalidTotal">
              </el-pagination>
            </el-tab-pane>
          </el-tabs>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
import UserManage from "./UserManage";
export default {
  name: "Manage",
  data(){
    return{
      index:'',
      navList:[
        {name:'/userManage',navItem:'用户管理'},
        {name:'/bookManage',navItem:'图书管理'},
        // {name:'/LunboManage',navItem:'轮播管理'},
      ],
      normalUsers:[],
      invalidUsers:[],
      currentValid:0,
      validTotal:0,
      currentInvalid:0,
      invalidTotal:0,
    }
  },
  components:{
    UserManage
  },
  methods:{
    handleValidChange(val) {
      this.currentValid=val
      this.getValidUsers()
      console.log(`当前页: ${val}`);
    },
    handleInvalidChange(val) {
      this.currentInvalid=val
      this.getInvalidUsers()
      console.log(`当前页: ${val}`);
    },
    invalid(val){
      this.$API
          .p_changeUserState({
            id:val,
            state:0
          })
          .then((data) => {
            this.$notify({
              title: '成功',
              message: '设置违规用户成功',
              type: 'success'
            });
            this.$router.go(0);
          })
          .catch((err) => {})

    },
    valid(val){
      this.$API
          .p_changeUserState({
            id:val,
            state:1
          })
          .then((data) => {
            this.$notify({
              title: '成功',
              message: '取消违规用户成功',
              type: 'success'
            });
            this.$router.go(0);
          })
          .catch((err) => {})
    },
    getValidUsers(){
      this.$API
          .p_getRightUsers({
            state:1,
            startNum:this.currentValid
          })
          .then((data) => {
            this.validTotal=data.allNum
            for (let i = 0; i < data.userList.length; i++) {
              let user = data.userList[i]
              let temp = {
                id:'',
                username:'',
                createdDate:'',
                mail:'',
              }
              temp.id=user.id
              temp.username=user.username
              temp.createdDate=user.createdDate
              temp.mail=user.mail
              this.normalUsers.push(temp)
            }
          })
          .catch((err) => {})
    },
    getInvalidUsers(){
      this.$API
          .p_getRightUsers({
            state:0,
            startNum:this.currentInvalid
          })
          .then((data) => {
            this.invalidTotal=data.allNum
            for (let i = 0; i < data.userList.length; i++) {
              let user = data.userList[i]
              let temp = {
                id:'',
                username:'',
                createdDate:'',
                mail:'',
              }
              temp.id=user.id
              temp.username=user.username
              temp.createdDate=user.createdDate
              temp.mail=user.mail
              this.invalidUsers.push(temp)
            }
          })
          .catch((err) => {})
    }
  },
  mounted() {
    this.getValidUsers()
    this.getInvalidUsers()
  }
}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.manage{
  margin-top: 65px;
}
</style>
