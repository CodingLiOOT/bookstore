<template>
  <div class="ht-view"></div>
</template>

<script>
export default {
  name: 'home',
  components: {
    // HelloWorld
  },
  created: function () {
    this.dataModel = new ht.DataModel()
    this.graphView = new ht.graph.GraphView(this.dataModel)
  },
  mounted: function () {
    this.graphView.addToDOM(this.$el)
    var server = new ht.Node() //创建“服务器”数据节点
    server.setImage('../assets/1.jpg')
    this.graphView.dm().add(server) //将节点添加进数据容器 graphView.dm() 中
  },
}
</script>

<style scoped>
.ht-view {
  text-align: left;
  position: relative;
  width: 800px;
  height: 500px;
  margin: auto;
  border: 1px solid #ccc;
}
</style>