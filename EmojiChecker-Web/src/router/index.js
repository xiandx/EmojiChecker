import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import Users from '../components/user/Users.vue'
import Rights from '../components/power/Rights.vue'
import Roles from '../components/power/Roles.vue'
import Cate from '../components/goods/Cate.vue'
import Params from '../components/goods/Params.vue'
import GoodsList from '../components/goods/List.vue'
import AddGood from '../components/goods/Add.vue'
import EditGood from '../components/goods/Edit.vue'
import Order from '../components/order/Order.vue'
import Report from '../components/report/Report.vue'
import test from '../components/test.vue'
import histo from '../components/chart/histogram.vue'
import pie from '../components/chart/pie.vue'
import line from '../components/chart/line.vue'
Vue.use(VueRouter)

const routes = [
    // 如果路径是/那么就重定向到login路径
    { path: '/', redirect: '/login' },
    { path: '/login', component: Login },
    {
        path: '/home',
        component: Home,
        // 如果路由地址是/home就重定向到/welcome
        redirect: '/welcome',
        children: [
            { path: '/welcome', component: Welcome },
            { path: '/users', component: Users },
            { path: '/rights', component: Rights },
            { path: '/roles', component: Roles },
            { path: '/categories', component: Cate },
            { path: '/params', component: Params },
            { path: '/goods', component: GoodsList },
            { path: '/goods/add', component: AddGood },
            { path: '/goods/edit', component: EditGood },
            { path: '/orders', component: Order },
            { path: '/reports', component: Report },
            { path: '/test', component: test },
            { path: '/chart/histogram', component: histo },
            { path: '/chart/pie', component: pie },
            { path: '/chart/line', component: line }
        ]
    }
]

const router = new VueRouter({
        routes
    })
    // 挂载路由导航守卫
router.beforeEach((to, from, next) => {
    if (to.path === '/login') return next()
    const tokenStr = window.sessionStorage.getItem('token')
    if (!tokenStr) return next('/login')
    next()
})
export default router