<template>
  <div class="login_container">
    <div class="login_box">
      <!-- 头像区 -->
      <div class="avatar_box">
        <img src="../assets/logo.png" alt="" />
      </div>
      <!-- 登录表单区 预留label宽度设为0-->
      <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="loginFormRules"
        label-width="0px"
        class="login_form"
      >
        <!-- 用户名 -->
        <el-form-item prop="username">
          <!-- prefix-icon在elem中指前置图标，值是图标库中的值，如果elem的图标中没有需要的可以从第三方图标库引用 -->
          <el-input
            v-model="loginForm.username"
            prefix-icon="iconfont icon-user"
          ></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            prefix-icon="iconfont icon-3702mima"
          ></el-input>
        </el-form-item>
        <el-form-item class="btns">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="resetLoginForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'login',
  data() {
    return {
      // 登录表单的数据对象，用户名，密码定义为它的属性
      loginForm: {
        username: 'admin',
        password: '123456',
      },
      // 表单的验证规则对象【绑定rules，写规则，指定规则赋给item的prop属性值】
      loginFormRules: {
        username: [
          { required: true, message: '请输入登录名', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在3~10之间', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 15, message: '长度在6~15之间', trigger: 'blur' },
        ],
      },
    }
  },
  methods: {
    //  通过ref来获取表单实例，调用表单的resetFields方法来对表单进行重置（回到初始状态）
    resetLoginForm() {
      this.$refs.loginFormRef.resetFields()
    },
    login() {
      // 调用validate函数来对表单数据进行预校验，函数参数是一个回调函数，回调函数第一个参数是一个变量，当校验通过是true，否则为false
      // 第二个参数是验证规则对象，哪个验证规则不通过obj就是这个规则所对应的对象
      this.$refs.loginFormRef.validate(async (valid, obj) => {
        if (!valid) return
        // 对应接口文档说的是Post请求方式，携带两个必要数据{username和password}，把父数据对象直接传过去即可
        // const result=await this.$http.post('login',this.loginForm);
        // post的返回值result是一个Promise对象，可以用await来简化，但必须存在于async修饰的函数下

        // 从result中将data属性结构出来重命名为res，data中存储着服务器响应的数据，其他属性暂不需要
        const { data: res } = await this.$http.post('login', this.loginForm)

        // Message的两种方式，一种是message函数传入对象；一种是将type当作方法传入提示语
        if (res.meta.status !== 200)
          return this.$message({
            message: '用户名或密码错误，登录失败',
            type: 'error',
          })
        this.$message.success('恭喜您，登录成功')
        window.sessionStorage.setItem('token', res.data.token)
        // 通过编程式导航跳转到后台主页，地址：/home
        this.$router.push('/home')
      })
    },
  },
}
</script>

<style lang="less" scoped>
.login_container {
  background-color: pink;
  height: 100%;
}
.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
.avatar_box {
  width: 130px;
  height: 130px;
  border: 1px solid #eee;
  border-radius: 50%;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  // 让边框和图片之间有点间隙
  padding: 10px;
  // 为了凸显外边效果可以加边框阴影
  box-shadow: 0 0 10px #ddd;
  img {
    width: 100%;
    height: 100%;
    border-radius: 50%;
  }
}
.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  // 盒模型转换为border-box
  box-sizing: border-box;
}
.btns {
  display: flex;
  justify-content: flex-end;
}
</style>