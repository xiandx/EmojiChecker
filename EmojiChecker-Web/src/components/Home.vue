<template>
  <el-container class="home-container">
    <!-- 头部 -->
    <el-header>
      <div class="header-container">
        <img src="../assets/logo.png" alt="" />
        <span>情绪监控管理系统</span>
      </div>
      <el-button type="info" @click="logout">退出</el-button>
    </el-header>
    <el-container>
      <!-- 侧边 -->
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <!-- 给侧边栏加上按钮实现菜单栏的展开/收起 -->
        <div class="toggle-button" @click="toggleCollapse">|||</div>
        <!-- 侧边菜单栏 -->
        <el-menu
          background-color="#e8f4f5"
          text-color="#545c64"
          active-text-color="#f56c6c"
          unique-opened
          :collapse="isCollapse"
          :collapse-transition="false"
          router
          :default-active="activePath"
        >
          <!-- 一级菜单 -->
          <!-- 每一个el-submenu都有一个index属性，如果index属性相同，那么就会导致同名的标签在点击时同时展开同时收起 -->
          <!-- 这里的index被规定了只接收字符串，而id是数，所以做了下拼接 -->
          <el-submenu
            :index="item.id + ''"
            v-if="item = menuList[0]"
            :key="item.id"
          >
            <!-- 一级菜单的模板区域 -->
            <template slot="title">
              <!-- 图标 -->
              <!-- <i :class="iconsObj[item.id]"></i> -->
              <!-- 文本 -->
              <!-- <span>{{ item.authName }}</span> -->
              <span>学生管理</span>
            </template>
            <!-- 二级菜单 -->
            <!-- 为ele-menu开启router路由模式后，规定：会在激活导航时以 index 作为 path 进行路由跳转。所以把index值设置为路径，也不影响index是唯一标识的作用-->
            <el-menu-item
              v-for="subItem in item.children"
              :index="'/' + subItem.path"
              :key="subItem.id"
              @click="saveRoutePath('/' + subItem.path)"
            >
            <!-- @click="saveRoutePath('/' + subItem.path)" -->
              <template slot="title">
                <!-- 图标 -->
                <!-- <i class="el-icon-menu"></i> -->
                <!-- 文本
                <span>{{ subItem.authName }}</span> -->
                <span>学生管理</span>
              </template>
            </el-menu-item>
          </el-submenu>

          <el-submenu
            :index="test"
            :key="item.id"
          >
            <!-- 一级菜单的模板区域 -->
            <template slot="title">
              <!-- 图标 -->
              <!-- <i :class="iconsObj[item.id]"></i> -->
              <!-- 文本 -->
              <!-- <span>{{ item.authName }}</span> -->
              <span>情绪监控</span>
            </template>
            <!-- 二级菜单 -->
            <el-menu-item :index="histo">
              <template slot="title">
                <router-link to='/chart/histogram'><span>直方图</span></router-link>
              </template>
            </el-menu-item>
            <el-menu-item :index="test">
              <template slot="title">
                <router-link to='/chart/pie'><span>饼图</span></router-link>
              </template>
            </el-menu-item>
            <el-menu-item :index="histo">
              <template slot="title">
                <router-link to='/chart/line'><span>折线图</span></router-link>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <!-- 主体 -->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: 'home',
  created() {
    //  组件被创建后调用函数获取菜单
    this.getMenuList()
    // home页面一刷新会重新解析渲染页面，就从session中获取当前的路径给到activePath
    this.activePath = window.sessionStorage.getItem('acitvePath')
  },
  data() {
    return {
      // 将获取到的菜单数据存到data中以便在页面上渲染出来——左侧菜单数据
      // 服务器返回的数组是一个二维数组，也就对应着一级菜单和二级菜单，二级菜单在一级菜单的children中
      // 两层的菜单渲染用双层for循环即可，二级菜单内容的获取----遍历一级菜单的children
      menuList: [],
      // 将图标与菜单标签的id对应上，实现根据自己的id渲染对应的图标
      iconsObj: {
        125: 'iconfont icon-user',
        103: 'iconfont icon-tijikongjian',
        101: 'iconfont icon-shangpin',
        102: 'iconfont icon-danju',
        145: 'iconfont icon-baobiao',
      },
      // 控制折叠或展开的flag
      isCollapse: false,
      // 被激活的链接地址
      activePath: '',
    }
  },
  methods: {
    logout() {
      // 退出两步：清空session、重定向到登录页
      window.sessionStorage.clear()
      this.$router.push('/login')
    },
    // 获取所有菜单
    async getMenuList() {
      const { data: res } = await this.$http.get('menus')
      // 如果请求不成功，提示错误信息——服务器的信息反馈在data.meta的msg中
      if (res.meta.status !== 200) return this.$message.error(res.meta.msg)
      // 否则请求成功则将获取到的菜单数据给到组件data中定义的数组
      this.menuList = res.data
    },
    // 点击按钮切换菜单的折叠与展开
    toggleCollapse() {
      this.isCollapse = !this.isCollapse
    },
    // 保存链接的激活状态——保存住当前的页面对应的菜单栏条目，以备在页面刷新后条目依旧高亮
    saveRoutePath(activePath) {
      window.sessionStorage.setItem('acitvePath', activePath)
      // !!!
      this.activePath=activePath
    },
  },
}
</script>

<style lang="less" scoped>
.home-container {
  height: 100%;
}
// 头部采用flex布局
.el-header {
  background-color: rgba(219, 238, 240, 0.651);
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  //  让按钮上下居中（垂直居中）而不是上下贴边
  align-items: center;
  font-size: 20px;
  .header-container {
    display: flex;
    align-items: center;
    img {
      width: 70px;
    }
    span {
      margin-left: 15px;
    }
  }
}
.el-aside {
  background-color: rgba(219, 238, 240, 0.651);
  .el-menu {
    border-right: null;
  }
}
.el-main {
  background-color: rgb(170, 226, 230);
}
.iconfont {
  // 图标和文本之间有点间距
  margin-right: 10px;
}
.toggle-button {
  background-color: rgb(210, 241, 243);
  font-size: 10px;
  line-height: 24px;
  color: rgb(168, 149, 162);
  text-align: center;
  cursor: pointer;
  // 让字符之间有一定的0.2em的距离
  letter-spacing: 0.2em;
}
.router-link-active {
  text-decoration: none;
  color: red;
}
a {
  text-decoration: none;
  color: red;
}
</style>