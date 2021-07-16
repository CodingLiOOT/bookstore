<template>
  <el-container class="manage">
    <!--    <el-header>久柒图书电商后台管理系统</el-header>-->
    <el-container>
      <el-aside width="200px" class="side">
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
          <el-card>
            <el-tabs
              :tab-position="left"
              @tab-click="handleClick"
              v-model="activeName"
            >
              <el-tab-pane label="轮播显示" name="1">
                <el-row>
                  <el-col
                    :span="8"
                    v-for="lunbo in this.lunbos"
                    :key="lunbo.lunboId"
                    :offset="3"
                  >
                    <el-card class="lunboCard">
                      <img :src="lunbo.imgUrl" class="image" />
                      <div>
                        <span>{{ lunbo.lunboName }}</span>
                        <div class="bottom clearfix">
                          <el-button
                            type="text"
                            class="button"
                            @click="deletePic(lunbo.lunboId)"
                            >删除
                          </el-button>
                        </div>
                      </div>
                    </el-card>
                  </el-col>
                </el-row>
              </el-tab-pane>
              <el-tab-pane label="添加轮播" name="2">
                <el-row>
                  <el-col :span="16" :offset="4">
                    <el-form :model="lunboForm">
                      <el-form-item label="活动名称">
                        <el-input
                          v-model="lunboForm.lunboName"
                          autocomplete="off"
                        ></el-input>
                      </el-form-item>
                      <el-form-item label="上传图片">
                        <el-upload
                          class="upload-demo"
                          action=""
                          :on-preview="handlePreview"
                          :on-remove="handleRemove"
                          :on-success="handleSuccess"
                          :before-remove="beforeRemove"
                          multiple
                          :limit="3"
                          :on-exceed="handleExceed"
                          :headers="myHeaders"
                          :file-list="fileList"
                          :http-request="uploadOk"
                        >
                          <el-button size="small" type="primary"
                            >选择文件</el-button
                          >
                          <div slot="tip" class="el-upload__tip">
                            只能上传jpg/png文件，且不超过15mb
                          </div>
                        </el-upload>
                      </el-form-item>
                      <el-button @click="lunboVisible = false">取 消</el-button>
                      <el-button type="primary" @click="addLunbo"
                        >确 定</el-button
                      >
                    </el-form>
                  </el-col>
                </el-row>
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
import UserManage from './UserManage'
import OSS from 'ali-oss'

export default {
  name: 'Manage',
  data() {
    return {
      activeName: '1',
      index: '',
      navList: [
        { name: '/userManage', navItem: '用户管理' },
        { name: '/bookManage', navItem: '图书管理' },
        { name: '/LunboManage', navItem: '轮播管理' },
      ],
      myHeaders: {
        token: this.$store.state.token,
      },
      fileList: [],
      file: null,
      filename: '',
      lunbos: [],
      lunboVisible: false,
      lunboForm: {
        lunboName: '',
        imgUrl: '',
      },
      onProgress: 0,
      url: '',
    }
  },
  components: {
    UserManage,
  },
  methods: {
    handleClick(tab, event) {
      switch (tab.name) {
        case '1':
          this.getAllLunbos()
          break
        case '2':
          break
      }
    },
    getUrl() {
      let ossParameter = {
        region: 'oss-cn-beijing',
        accessKeyId: 'LTAI5tLD8bQBPdsaZswgxUG4',
        accessKeySecret: '3RBPbn2lpoGRRGkMOS8CyBa2I13tmf',
        bucket: 'semester',
      }
      //生成上传oss
      const client = new OSS(ossParameter)
      //路径
      let path = this.$store.state.userID + Date() + this.file.name
      client.put(path, this.file).then((result) => {
        //生成访问地址
        let url = client.signatureUrl(result.name, {
          expires: 3153600000,
        })
        console.log(url)
        this.url = url
      })
    },
    deletePic(val) {
      if (this.lunbos.length === 1) {
        this.$notify({
          title: '提示',
          message: '轮播数必须大于1',
          type: 'success',
        })
      } else {
        this.$API
          .p_deleteLunbo({
            lunboId: val,
          })
          .then((data) => {
            this.getAllLunbos()
          })
          .catch((err) => {})
      }
    },
    addLunbo() {
      this.send()
      this.lunboForm = {}
    },
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePreview(file) {
      console.log(file)
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      )
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    handleSuccess(res, file, fileList) {
      this.$notify.success({
        title: '成功',
        message: `文件上传成功`,
      })
    },
    uploadOk(val) {
      this.file = val.file
      this.filename = val.file.name
      this.getUrl()
    },
    getAllLunbos() {
      this.lunbos = []
      this.$API
        .p_getAllLunbos({})
        .then((data) => {
          for (let i = 0; i < data.lunboList.length; i++) {
            let d = data.lunboList[i]
            let temp = {
              lunboId: '',
              lunboName: '',
              imgUrl: '',
            }
            temp.lunboId = d.lunboId
            temp.lunboName = d.lunboName
            temp.imgUrl = d.imgUrl
            this.lunbos.push(d)
            console.log(this.lunbos)
          }
        })
        .catch((err) => {})
    },
    send() {
      let lunbo = {}
      this.$API
        .p_addLunbo({
          filename: this.file.name,
          lunboName: this.lunboForm.lunboName,
          imgUrl: this.url,
        })
        .then((data) => {
          this.$notify({
            title: '成功',
            message: '添加轮播成功',
            type: 'success',
          })
        })
        .catch((err) => {})
    },
  },
  mounted() {
    this.getAllLunbos()
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

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 230px;
  height: 200px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: '';
}

.clearfix:after {
  clear: both;
}
.side {
  margin-top: 20px;
}
.lunboCard {
  width: 270px;
}
</style>
