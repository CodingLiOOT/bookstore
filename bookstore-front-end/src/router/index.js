import Vue from 'vue'
import Router from 'vue-router'
import store from "../vuex"
import MainPage from "../components/MainPage/MainPage"
import Cart from "../components/shoppingCart/Cart";
import AllBook from "../components/AllBook/AllBook";
import Manage from "../components/Manage/Manage";
import UserManage from "../components/Manage/UserManage";
import LunboManage from "../components/Manage/LunboManage";
import bookManage from "../components/Manage/bookManage";

Vue.use(Router)

/**
 * 刷新页面时，重新赋值token
 */
if (sessionStorage.getItem('token')) {
  store.commit('login', {
    userID: sessionStorage.getItem('userID'),
    token: sessionStorage.getItem('token'),
    state:sessionStorage.getItem('state'),
  })
}

const router = new Router({
  mode: 'hash',
  routes: [
    {
      path:'/mainPage',
      name:'mainPage',
      component: MainPage,
    },
    {
      path:'/AllBook',
      name:'AllBook',
      component: AllBook,
    },
    {
      path:'/Cart',
      name:'Cart',
      component: Cart,
    },
    {
      path: '/',
      redirect: '/mainPage'
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
      path:'/item',
      name:'Item',
      component: () => import("../components/Item")

    },
    {
      path:'/CenterPage',
      name:'CenterPage',
      component: () => import("../components/Center/CenterPage")

    },
    {
      path:'/OrderInfor',
      name:'OrderInfor',
      component: () => import("../components/OrderInfor")

    },
    {
      path:'/Paycode',
      name:'Paycode',
      component: () => import("../components/Paycode")

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
    },
    {
      path: '/Manage',
      name: 'Manage',
      component: Manage
    },
    {
      path: '/userManage',
      name: 'userManage',
      component: UserManage
    },
    {
      path: '/bookManage',
      name: 'bookManage',
      component: bookManage
    },
    {
      path: '/LunboManage',
      name: 'LunboManage',
      component: LunboManage
    },
    {
      path: '/activityPage',
      name: 'activityPage',
      component: () => import("../components/activityPage")
    },
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
