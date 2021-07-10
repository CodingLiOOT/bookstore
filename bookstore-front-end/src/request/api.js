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
  static g_getTops = p => get('/mainPage/getTops', p)

  /**
   * 猜你喜欢书列表接口
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static g_getLikes = p => get('/mainPage/getLikes', p)

  /**
   * 图书类别接口
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static g_getAllCate = p => post('/mainPage/getAllCate', p)

  /**
   * 轮播接口
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_getLunBo = p => post('/mainPage/getLunBo', p)

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
  static p_deleteFromCart = p => post('/cart/deleteFromCart', p)

  /**
   * 修改购物车内商品个数接口
   * @param {Object} p [发送的参数：]
   * @returns {Promise<unknown>}
   */
  static p_modifyNumFromCart = p => post('/cart/modifyNumFromCart', p)

}
