<template>
  <el-container>
    <el-aside width="200px">
      <el-scrollbar>
        <el-menu :default-openeds="['1']" default-active="1-2">
          <el-sub-menu index="1">
            <template #title>
              <el-icon><Menu /></el-icon> {{ 'MENU' }}
            </template>
            <el-menu-item-group>
              <el-menu-item index="1-1" @click="movehandle(1)">
                <el-icon><House /></el-icon>{{ 'HOME' }}
              </el-menu-item>
              <el-menu-item index="1-2" @click="movehandle(2)">
                <el-icon><Document /></el-icon>{{ '게시판' }}
              </el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
        </el-menu>
      </el-scrollbar>
    </el-aside>
    <el-container>
      <el-header
        style="
          position: relative;
          text-align: right;
          background-color: var(--el-color-primary-light-7);
        "
      >
        {{ today }}
      </el-header>
      <el-main>
        <el-scrollbar height="600px">
          <RouterView />
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  setup() {},
  created() {
    this.movehandle(2)
  },
  data() {
    return {
      curDate: new Date(),
      today: null,
    }
  },
  mounted() {
    this.updateNow()
  },
  methods: {
    movehandle(item) {
      switch (item) {
        case 1:
          this.$router.push({ path: '/' })
          break
        case 2:
          this.$router.push({ path: '/board' })
          break
      }
    },
    updateNow() {
      this.today =
        this.curDate.getFullYear() +
        '년 ' +
        (this.curDate.getMonth() + 1) +
        '월 ' +
        this.curDate.getDate() +
        '일 '
    },
  },
}
</script>
