<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import Header from './components/Header'
import Header1 from './components/Header1'
import Header2 from './components/Header2'
export default {
  name: 'App',
  components: {
    Header,
    Header1,
    Header2,
  },
  provide() {
    //父组件中通过provide来提供变量，在子组件中通过inject来注入变量。
    return {
      reload: this.reload,
    }
  },
  data() {
    return {
      flag: true, //控制视图是否显示的变量
      type: 1,
    }
  },
  mounted() {},
  methods: {
    reload() {
      this.flag = true //先关闭，
      this.$nextTick(function () {
        this.flag = false //再打开
      })
    },
  },
  computed: {
    getUserId() {
      return this.$store.state.userID === undefined
    },
    getUserState() {
      return this.$store.state.state
    },
  },
}
</script>

<style>
</style>
