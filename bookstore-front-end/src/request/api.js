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
}
