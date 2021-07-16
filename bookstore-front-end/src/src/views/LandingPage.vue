<template>
  <div class="login-wrapper">
    <router-view></router-view>
  </div>
</template>
<script>
export default {
  name: 'landingPage',
}
</script>
<style>
.login-wrapper {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  overflow: hidden;
  /*background: rgb(193, 216, 235);*/
  /*background: rgba(38, 50, 56, 0.6) url(../assets/login_bg.jpg) no-repeat fixed top center;*/
  background-image: linear-gradient( #D1E8E2,#FFFFCC);;
  background-size: 100% 100%;
}
</style>
