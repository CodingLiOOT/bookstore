<template>
  <el-container class="manage">
    <!--    <el-header>久柒图书电商后台管理系统</el-header>-->
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
          <el-dialog title="添加轮播" :visible.sync="lunboVisible">
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
                  <el-button size="small" type="primary">选择文件</el-button>
                  <div slot="tip" class="el-upload__tip">
                    只能上传jpg/png文件，且不超过15mb
                  </div>
                </el-upload>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="lunboVisible = false">取 消</el-button>
              <el-button type="primary" @click="addLunbo">确 定</el-button>
            </div>
          </el-dialog>
          <el-row>
            <el-col :span="3" :offset="21">
              <el-button @click="lunboVisible = true">添加轮播</el-button>
            </el-col>
          </el-row>
          <el-row>
            <el-col
              :span="8"
              v-for="lunbo in this.lunbos"
              :key="lunbo.lunboId"
              :offset="index > 0 ? 2 : 0"
            >
              <el-card :body-style="{ padding: '0px' }">
                <img src="lunbo.imgUrl" class="image" />
                <div style="padding: 14px">
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
    }
  },
  components: {
    UserManage,
  },
  methods: {
    getUrl() {
      // const OSS = require("ali-oss");
      // aliyun.oss.endpoint=xiaoxueqi.oss-cn-beijing.aliyuncs.com   #域名地区
      // aliyun.oss.accessKeyId=LTAI5tLD8bQBPdsaZswgxUG4   # 权限id
      // aliyun.oss.secret=3RBPbn2lpoGRRGkMOS8CyBa2I13tmf  #秘钥
      // aliyun.oss.bucket=xiaoxueqi  #bucket名称
      let ossParameter = {
        // endpoint: 'https://semester.oss-cn-beijing.aliyuncs.com/',
        region: 'oss-cn-beijing',
        accessKeyId: 'LTAI5tLD8bQBPdsaZswgxUG4',
        accessKeySecret: '3RBPbn2lpoGRRGkMOS8CyBa2I13tmf',
        bucket: 'semester',
        // secure: true,
      }
      //生成上传oss
      const client = new OSS(ossParameter)
      //路径
      let path = this.file.name
      alert(this.file.path)

      client.put(path, this.file).then((result) => {
        alert('haha')
        //生成访问地址
        let url = client.signatureUrl(result.name, {
          expires: 3153600000,
        })
        alert(url)
      })
    },
    deletePic(val) {
      this.$API
        .p_deleteLunbo({
          lunboId: val,
        })
        .then((data) => {
          this.getAllLunbos()
        })
        .catch((err) => {})
    },
    addLunbo() {
      this.send()
      this.lunboVisible = false
      // this.$API
      //     .p_addLunbo({
      //       filename:this.file.name,
      //       lunboName:this.lunboForm.lunboName,
      //       file: this.file,
      //     })
      //     .then((data) => {
      //
      //     })
      //     .catch((err) => {})
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
      this.$API
        .p_getAllLunbos({})
        .then((data) => {
          for (let i = 0; i < data.lunboList.length; i++) {
            let data = data.lunboList[i]
            let temp = {
              lunboId: '',
              lunboName: '',
              imgUrl: '',
            }
            temp.lunboId = data.lunboId
            temp.lunboName = data.lunboName
            temp.imgUrl = data.imgUrl
            this.lunbos.push(data)
          }
        })
        .catch((err) => {})
    },
    send() {},
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
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: '';
}

.clearfix:after {
  clear: both;
}
</style>
