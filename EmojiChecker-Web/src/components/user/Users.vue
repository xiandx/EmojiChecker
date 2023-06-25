<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片试图区 卡片Card组件中套Layout布局组件，Layout栅格里放Input、Button;-->
    <!-- ElementUI是可以组件穿插套用搭配使用的！已经引入了总体的css，就不用再在这里贴对应的css了，需要修改的样式再重写覆盖就行。-->
    <el-card class="box-card">
      <!-- 搜索与添加区域 把搜索框放置到了栅格中(24栅格)，span对应比例，gutter设置列与列的间隙距离-->
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input
            v-model="queryInfo.query"
            placeholder="请输入内容"
            class="input-with-select"
            clearable
            @clear="getUserList"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getUserList"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true"
            >添加用户</el-button
          >
        </el-col>
      </el-row>

      <!-- 数据列表，data为表格绑定数据源userList，内部所需的数据都会从userList中做提取-->
      <el-table :data="userList" style="width: 100%" border stripe>
        <!-- type为index可以设置这一列为表格的索引列 -->
        <el-table-column type="index" label="序号"></el-table-column>
        <el-table-column
          prop="username"
          label="姓名"
          width="120"
        ></el-table-column>
        <el-table-column prop="email" label="邮箱" width="180">
        </el-table-column>
        <el-table-column prop="mobile" label="电话" width="180">
        </el-table-column>
        <el-table-column prop="role_name" label="角色" width="120">
        </el-table-column>
        <!-- 既指定了prop，又内置作用域插槽，那么prop将不再生效 -->
        <el-table-column label="状态" width="90">
          <!-- 作用域插槽大作用：通过scope.row获取到一行的数据，给开关状态绑定mg_state符合ElementUI布尔值设置开关的规则 -->
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.mg_state"
              active-color="#13ce66"
              inactive-color="#bac3c4"
              @change="userStateChange(scope.row)"
            >
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column prop="" label="操作">
          <!-- 要删除或修改用户时也只能通过用户ID来选择，所以还是得需要这一行的数据，还是得借助作用域插槽 -->
          <template slot-scope="scope">
            <!-- 修改 -->
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showEditDialog(scope.row.id)"
            ></el-button>
            <!-- 删除 -->
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="removeUserById(scope.row.id)"
            ></el-button>
            <!-- 修改角色 文字提示组件-->
            <el-tooltip
              effect="dark"
              content="分配角色"
              placement="right"
              :enterable="false"
            >
              <el-button
                type="info"
                icon="el-icon-setting"
                size="mini"
                @click="setRole(scope.row)"
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页区域 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum"
        :page-sizes="[1, 3, 5, 10]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </el-card>
    <!-- 添加用户弹出的对话框 -->
    <el-dialog
      title="添加用户"
      :visible.sync="addDialogVisible"
      width="50%"
      :close-on-click-modal="false"
      @close="addDialogClosed"
    >
      <!-- 对话框中插入form表单ui组件,绑定数据,设定验证规则 -->
      <el-form
        :model="addFormData"
        :rules="addFormRules"
        ref="addFormRef"
        label-width="70px"
        class="demo-ruleForm"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addFormData.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="addFormData.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addFormData.email"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="mobile">
          <el-input v-model="addFormData.mobile"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 修改用户的对话框 -->
    <el-dialog
      title="修改用户"
      :visible.sync="editDialogVisible"
      width="50%"
      :close-on-click-modal="false"
      @close="editDialogClosed"
    >
      <!-- 对话框中插入form表单ui组件,绑定数据,设定验证规则--同添加用户时的校验规则就可以-->
      <el-form
        :model="editForm"
        :rules="addFormRules"
        ref="editFormRef"
        label-width="70px"
        class="demo-ruleForm"
      >
        <el-form-item label="用户名">
          <el-input v-model="editForm.username" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="editForm.email"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="mobile">
          <el-input v-model="editForm.mobile"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editUser">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 点击分配角色的按钮后弹窗 -->
    <el-dialog
      title="分配角色"
      :visible.sync="showRoleDialogVisible"
      width="50%"
      @close="setRoleDialogClosed"
    >
      <div>
        <p>当前的用户：{{ userInfo.username }}</p>
        <p>当前的角色：{{ userInfo.role_name }}</p>
        <p>选择新角色：</p>
        <!-- value指的是将选择的那一项的id存进双向绑定的selectedRoleID中 -->
        <el-select v-model="selectedRoleId" placeholder="请选择">
          <el-option
            v-for="item in rolesList"
            :key="item.id"
            :label="item.roleName"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showRoleDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveRoleInfo"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'home',
  data() {
    // 验证手机号规则----EleUI中的自定义校验规则方式----符合后输入框会变绿
    var checkMobile = (rule, value, cb) => {
      const regMobile =
        /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/
      if (regMobile.test(value)) {
        // 手机号合法---直接调用cd,Elem会认为校验通过,如果传入了一个错误对象默认校验失败
        return cb()
      }
      cb(new Error('请输入合法的手机号'))
    }
    return {
      // 获取用户列表的参数对象
      queryInfo: {
        query: '',
        // 设置当前请求的哪一页
        pagenum: 1,
        // 设置每页请求多少条数据
        pagesize: 2,
      },
      userList: [],
      total: 0,
      // 控制对话框的显示与隐藏
      addDialogVisible: false,
      // 添加用户的表单数据
      addFormData: {
        username: '',
        password: '',
        email: '',
        mobile: '',
      },
      // 添加表单的验证规则对象
      addFormRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          {
            min: 3,
            max: 10,
            message: '用户名的长度在3到10之间',
            trigger: 'blur',
          },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          {
            min: 6,
            max: 15,
            message: '密码的长度在3到10之间',
            trigger: 'blur',
          },
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          {
            type: 'string',
            message: '邮箱格式不正确',
            trigger: 'blur',
            transform(value) {
              if (
                !/^\w+((-\w+)|(\.\w+))*@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(
                  value
                )
              ) {
                return true
              }
            },
          },
        ],
        mobile: [
          { required: true, message: '请输入手机号码', trigger: 'blur' },
          {
            validator: checkMobile,
            trigger: 'blur',
          },
        ],
      },
      // 控制修改用户对话框的展示与隐藏
      editDialogVisible: false,
      // 通过ID获取到的用户数据对象
      editForm: {},
      // 控制分配角色对话框的打开与关闭
      showRoleDialogVisible: false,
      // 需要被分配角色的用户的信息
      userInfo: '',
      // 所有的角色数据列表
      rolesList: [],
      // 已选中的角色ID值
      selectedRoleId:''
    }
  },
  created() {
    this.getUserList()
  },
  methods: {
    // 获取用户列表数据
    async getUserList() {
      // get请求方式要用params进行指定
      const { data: res } = await this.$http.get('users', {
        params: this.queryInfo,
      })
      if (res.meta.status !== 200) return this.$message.error(res.meta.msg)
      // 用户数组
      this.userList = res.data.users
      // 当前的页码
      this.pagenum = res.data.pagenum
      // 总共条目数
      this.total = res.data.total
    },
    // 监听修改每页的条目量的事件
    handleSizeChange(newPageSize) {
      // 这里的参数默认规定就是ui组件中page-sizes属性的数组元素
      this.queryInfo.pagesize = newPageSize
      // 当前页发生了变化应该重新发送请求，显示当前页的数据
      this.getUserList()
    },
    // 监听 页码值 改变的事件
    handleCurrentChange(newPageNum) {
      // 这里的参数默认规定就是ui组件中的页码
      this.queryInfo.pagenum = newPageNum
      // 页码切换了，对应那一页的数据也要重新获取并渲染
      this.getUserList()
    },
    // 监听switch组件中开关状态的改变
    async userStateChange(userInfo) {
      const { data: res } = await this.$http.put(
        `users/${userInfo.id}/state/${userInfo.mg_state}`
      )
      if (res.meta.status !== 200) {
        //  没有请求成功状态就不能修改，所以得重置回来，不让页面呈现假的状态（点了修改，页面变化了，但实际没变，看似成功，实则是假的）
        userInfo.mg_state = !userInfo.mg_state
        return this.$message.error(res.meta.msg)
      }
      this.$message.success('更新用户状态成功')
      // console.log(userInfo)
    },
    // 关闭添加用户的对话框时触发close事件--重置表单数据，避免双向绑定造成的下次打开还残留上次数据的问题
    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },
    // 点击确定后先做预校验再判是否添加
    addUser() {
      this.$refs.addFormRef.validate(async (valid) => {
        if (!valid) return
        // 发起添加用户的网络请求--由于定义的addFromData中的数据名和服务器需要接收的数据名一致，所以传入addFormData就可以
        const { data: res } = await this.$http.post('users', this.addFormData)
        if (res.meta.status !== 201) return this.$message.error('添加用户失败')
        this.$message.success('添加用户成功')
        // 隐藏添加用户的对话框
        this.addDialogVisible = false
        // 用户存在新增，用户列表数据需要重新获取、渲染
        this.getUserList()
      })
    },
    // 展示编辑用户的对话框
    async showEditDialog(id) {
      // 根据ID查询用户信息
      const { data: res } = await this.$http.get('users/' + id)
      if (res.meta.status !== 200)
        return this.$message.error('用户信息获取失败！')
      this.editForm = res.data
      // 展示对话框
      this.editDialogVisible = true
    },
    // 监听用户信息修改的对话框的关闭事件
    editDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },
    // 点击确定按钮后进行预校验，校验通过再发送修改请求
    editUser() {
      this.$refs.editFormRef.validate(async (valid) => {
        if (!valid) return
        const { data: res } = await this.$http.put(
          'users/' + this.editForm.id,
          { email: this.editForm.email, mobile: this.editForm.mobile }
        )
        if (res.meta.status !== 200)
          return this.$message.error('更新用户信息失败！')
        // 关闭对话框
        this.editDialogVisible = false
        // 刷新数据
        this.getUserList()
        // 提示成功
        this.$message.success('更新用户信息成功！')
      })
    },
    // 根据id删除对应的用户信息
    async removeUserById(id) {
      // 弹窗询问是否确认删除
      const confirmRes = await this.$confirm(
        '此操作将永久删除该用户信息, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ).catch((err) => err)
      // 如果用户取消了删除操作会默认报错，需要使用catch捕获报错，此时返回结果会是cancle;确定对应confirm
      if (confirmRes == 'cancel') return this.$message.info('已取消删除操作！')
      // 否则发送删除用户的请求
      const { data: res } = await this.$http.delete('users/' + id)
      if (res.meta.status !== 200) return this.$message.error('删除用户失败！')
      // 刷新数据
      this.getUserList()
      // 提示
      return this.$message.success('已成功删除该用户！')
    },
    // 展示分配角色的对话框
    async setRole(userInfo) {
      this.userInfo = userInfo
      // 在展示对话框前，获取所有的角色列表以供分配角色的下拉框做数据源
      const { data: res } = await this.$http.get('roles')
      if (res.meta.status !== 200) {
        this.$message.error('角色列表获取失败！')
      }
      this.rolesList = res.data
      this.showRoleDialogVisible = true
    },
    // 点击确定按钮分配角色
    async saveRoleInfo(){
      // 判断有没有选
      if(!this.selectedRoleId){
        return this.$message.error('请先选择角色！')
      }
      // userInfo.id指定给哪个用户，后面的rid指定给分配的角色的ID
      const {data:res}= await this.$http.put(`users/${this.userInfo.id}/role`,{rid:this.selectedRoleId})
      if(res.meta.status!==200){
        return this.$message.error('更新角色失败！')
      }
      this.$message.success('更新角色成功！')
      this.getUserList()
      this.showRoleDialogVisible = false
    },
    // 监听分配角色对话框的关闭事件
    setRoleDialogClosed(){
      // 避免下次打开弹窗时残留上次的option信息，双向绑定置空就把option还原了
      this.selectedRoleId=''
      // this.userInfo='' 可要可不要，毕竟每次打开弹窗前已经给userInfo重新赋值了，也不会有残留的干扰
    }
  },
}
</script>

<style lang="less" scoped>
</style>