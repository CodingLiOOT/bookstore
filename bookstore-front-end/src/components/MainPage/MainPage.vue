<template>
  <div>
    <div class="card">
      <MainCard></MainCard>
    </div>
  </div>
</template>

<script>
import MainCard from './MainCard'
export default {
  components: {
    MainCard,
  },
  name: 'MainPage',
}
</script>

<style scoped>
.card {
  margin-top: 65px;
}
</style>
