import { get, post, uploadFile } from './http'


export default class api {

  /**
   * 登录接口，发送用户信息到后端查验用户信息
   * @param {Object} p [发送的参数]
   * @returns {Promise<unknown>}
   */
  static p_Login = p => post('/user/login', p)

  /**
   * 注册接口，发送用户信息到后端注册
   * @param {Object} p [发送的参数]
   * @returns {Promise<unknown>}
   */
  static p_Register = p => post('/user/register', p)

  /**
 * 忘记密码接口，发送用户信息到后端更改密码
 * @param {Object} p [发送的参数]
 * @returns {Promise<unknown>}
 */
  static p_Forget = p => post('/user/forgetPassword', p)

  /**
   * 邮箱验证码接口，发送邮箱到后端
   * @param {Object} p [发送的参数：邮箱验证码]
   * @returns {Promise<unknown>}
   */
  static p_SendCode = p => post('/user/sendVerifyCode', p)

  /**
   * 新书列表接口
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static g_getNewBooks = p => get('/mainPage/getNewBooks', p)

  /**
   * 热销榜书列表接口
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static g_getTops = p => get('/mainPage/getTopBooks', p)

  /**
   * 猜你喜欢书列表接口
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static g_getLikes = p => post('/mainPage/getRecommendedBooks', p)

  /**
   * 图书类别接口
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static g_getAllCate = p => post('/category/getCategories', p)

  /**
   * 轮播接口
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_getLunBo = p => post('/lunbo/getAllLunbos', p)

  /**
   * 获取用户信息接口
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_getUserInfo = p => post('/user/getInformation', p)

  /**
   * 获取购物车信息接口
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_getAllCart = p => post('/cart/getAllCart', p)

  /**
   * 结算购物车内选中的商品接口
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_settlement = p => post('/cart/settlement', p)

  /**
   * 删除购物车内商品接口
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_deleteFromCart = p => post('/cart/deletefromcart', p)

  /**
   * 修改购物车内商品个数接口
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_modifyNumFromCart = p => post('/cart/modifyNumFromCart', p)

  /**
   * 修改购物车内商品个数接口
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_bookDetail = p => post('/book/getDetail', p)

  /**
   * 增加购物车内商品接口
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_addToCart = p => post('/cart/addtocart', p)

  /**
   * 获取订单列表
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_getOrderList = p => post('/order/getOrderList', p)

  /**
   * 修改订单状态
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_changestate = p => post('/order/changestate', p)

  /**
   * 提交订单
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_confirmOrder = p => post('/order/confirmOrder', p)

  /**
   * 修改个人信息
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_modifyInformation = p => post('/user/modifyInformation', p)

  /**
   * 修改某个地址
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_modifyAddress = p => post('/user/modifyAddress', p)

  /**
   * 获取所有地址
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_getAddress = p => post('/user/getAddress', p)

  /**
   * 添加地址
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_addAddress = p => post('/user/addAddress', p)

  /**
   * 删除地址
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_deleteAddress = p => post('/user/deleteAddress', p)

  /**
   * 获取某页的书
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_getAllBooks = p => post('/book/getAllBooksByCategory', p)

  /**
   * 获取某页的书
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_getCategories = p => post('/category/getCategories', p)

  /**
   * 搜索书名
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_search = p => post('/book/search', p)

  /**
   * 筛选书名
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_shaixuan = p => post('/book/shaixuan', p)

  /**
   * 评论
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_comment = p => post('/comment/sendComment', p)

  /**
   * 评论
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_getCommentByOrder = p => post('/comment/getCommentByOrder', p)

  /**
   * 评论
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_getCommentByBook = p => post('/comment/getCommentByBook', p)

  /**
   * 更改用户状态
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_changeUserState = p => post('/user/changeUserState', p)

  /**
   * 获取某页的书籍
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_getRightUsers = p => post('/user/getRightUsers', p)

  /**
   * 获取某页的书
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_getRightBooks = p => post('/book/getRightBooks', p)

  /**
   * 修改某本书的状态
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_changeBookState = p => post('/book/changeBookState', p)

  /**
   * 获取所有轮播信息
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_getAllLunbos = p => post('/lunbo/getAllLunbos', p)

  /**
   * 删除某个轮播信息
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_deleteLunbo = p => post('/lunbo/deleteFromLunbo', p)

  /**
   * 增加轮播信息
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_addLunbo = p => post('/lunbo/addToLunbo', p)

  /**
   * 获取某个轮播信息
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_getOneLunbo = p => post('/lunbo/getOneLunbo', p)

  /**
   * 获取某个轮播信息
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_getCloud = p => post('/mainPage/getCloud', p)

}
