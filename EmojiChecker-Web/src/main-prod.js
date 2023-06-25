import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
// 导入全局样式表
import './assets/css/global.css'
// 导入字体图标
import './assets/fonts/iconfont.css'
// 导入发送请求的包
import axios from 'axios'
// 导入vue-table-with-tree-grid插件---用于商品分类中的树形表格展示功能
import TreeTable from 'vue-table-with-tree-grid'
// 导入富文本编辑器及其依赖的样式
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
// 导入nprogress进度条的JS和CSS
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
// 将axios包挂载到Vue的原型对象上，每一个Vue组件都可以通过this访问到http去发起Ajax请求
Vue.prototype.$http = axios;
// 配置请求的根路径
axios.defaults.baseURL = 'http://127.0.0.1:8888/api/private/v1/';
// 设置请求拦截
axios.interceptors.request.use(config => {
        // 在request拦截器中展示进度条
        NProgress.start()

        // console.log(config);
        // 为config做处理，根据API文档中所写的要添加一个属性Authorization，属性值就是token
        config.headers.Authorization = window.sessionStorage.getItem('token');
        // 最后必须将config返回，固定写法
        return config
    })
    // 在response拦截器中隐藏进度条
axios.interceptors.response.use(config => {
    // 响应成功了就隐藏进度条
    NProgress.done()
    return config
})
Vue.config.productionTip = false
    // 将这个插件注册为全局组件
Vue.component('tree-table', TreeTable)
    // 将富文本编辑器注册为全局可用的组件
Vue.use(VueQuillEditor)
    // 注册全局过滤器---时间过滤器---根据时间戳来按需要的格式返回时间
Vue.filter('dateFormat', function(originVal) {
        const dt = new Date(originVal * 1000)
        const y = dt.getFullYear()
            // 月份是从0开始的，所以得+1（padStart希望数据是2位的，不足两位则第一位用0替代）
        const m = (dt.getMonth() + 1 + '').padStart(2, '0')
        const d = (dt.getDate() + '').padStart(2, '0')
        const hh = (dt.getHours() + '').padStart(2, '0')
        const mm = (dt.getMinutes() + '').padStart(2, '0')
        const ss = (dt.getSeconds() + '').padStart(2, '0')
            // return `yyyy-mm-dd hh:mm:ss`
        return `${y}-${m}-${d} ${hh}:${mm}:${ss}`
    })
    // 给Vue原型上加一个数据用于存储商品id;编辑时跳转到其他页面对数据进行查询时使用
Vue.prototype.editGoodsID = { id: 0 }
new Vue({
    router,
    render: h => h(App)
}).$mount('#app')