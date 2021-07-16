import Vue from 'vue'

export function alertSuccess (msg = '操作成功') {
    Vue.prototype.$message({
        type: 'success',
        message: msg,
        showClose: true
    })
}
export function alertError (msg = '操作失败') {
    Vue.prototype.$message({
        type: 'error',
        message: msg,
        showClose: true
    })
}