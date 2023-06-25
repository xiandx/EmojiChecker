<template>
  <div class="welMain">
    <h2>情绪监控管理系统介绍</h2>
    <h2>
      此项目由 Vue2 + ElementUI + Axios + Mysql + Java + spring boot + javafx为主要技术依赖进行开发
    </h2>
    <h3>
      通过学生管理可对学生用户进行增删改查
    </h3>
    <h3>
      通过情绪监控可对学生的上课情绪进行可视化
    </h3>
    <!-- <hr>
    <ol>
      <li><h3>愿我们的每一份努力都不被辜负！</h3></li>
      <li><h3>愿积善者必有余庆！</h3></li>
      <li><h3>悟已往之不见，知来者之可追。</h3></li>
    </ol>
    <ul>
      <li>
        <h3>
          想象一下，2060年，已经年近古稀，坐在空无一人的房间，臃肿老态，没钱没势，生活几乎不能自理，你闭上眼睛虔诚地向上帝许了一个愿望：“祈求上帝让我在年轻一次吧！”
        </h3>
      </li>
      <li><h3>上帝说：“好的”</h3></li>
      <li>
        <h3>
          于是，你慢慢睁开眼，回到了2022年的今天，那这次，您打算怎么过活？
        </h3>
      </li>
      <el-switch
        v-model="fateValue"
        :disabled="onceSelect"
        active-text="不负韶华"
        inactive-text="碌碌无为"
      >
      </el-switch>
    </ul> -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      fateValue: false,
    }
  },
  computed: {
    onceSelect: function () {
      if (this.fateValue) {
        this.$message({
          showClose: true,
          message: '人生只有一次，而你选择了不负韶华，加油(ง •_•)ง！',
          type: 'success',
        })
        return this.fateValue
      }
    },
  },
}
</script>

<style lang="less" scoped>
.welMain{
  font-family: '楷体';
}
</style>