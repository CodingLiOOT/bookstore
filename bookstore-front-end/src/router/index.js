import Vue from 'vue'
import Router from 'vue-router'
import store from "../vuex"
import MainPage from "../components/MainPage/MainPage"

Vue.use(Router)

/**
 * 刷新页面时，重新赋值token
 */
if (sessionStorage.getItem('token')) {
  store.commit('login', {
    userID: sessionStorage.getItem('userID'),
    token: sessionStorage.getItem('token')
  })
}

const router = new Router({
  mode: 'history',
  routes: [
    {
      path:'/mainPage',
      name:'mainPage',
      component: MainPage,
    },
    {
      path: '/',
      redirect: '/user/login'
    },
    {
      path: '/user',
      name: 'landingPage',
      redirect: '/user/login',
      component: () => import('../views/LandingPage.vue'),
      children: [
        {
          path: 'login',
          name: 'login',
          component: () => import('../components/login.vue')
        },
        {
          path: 'register',
          name: 'register',
          component: () => import('../components/Register.vue')
        },
        {
          path: 'forget',
          name: 'forget',
          component: () => import('../components/forget.vue')
        }
      ]


    },
    {
      path: '/home',
      name: 'Home',
      component: () => import("../components/Home")
    },
    {
      path: '/index',
      name: 'index',
      meta: {
        requiresAuth: true
      },
      component: () => import("../components/index")
    },
    {
      path: '/fileDemo',
      name: 'fileDemo',
      component: () => import("../components/FileDemo")
    }
  ],
})
router.beforeEach((to, from, next) => {
  if (to.matched.some((r) => r.meta.requiresAuth)) {
    if (store.state.token) {
      next()
    } else {
      next({
        path: '/user/login',
        query: {
          redirect: to.fullPath
        }
      })
    }
  } else {
    next()
  }
})

export default router
