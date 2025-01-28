<template>
  <el-container>
    <!-- 사이드 메뉴 -->
    <el-aside class="aside">
      <el-scrollbar>
        <el-menu :default-openeds="['H2000']" :default-active="activeMenu">
          <template v-for="menu in topMenus" :key="menu.menuId">
            <!-- 단일 메뉴 -->
            <el-menu-item
              v-if="menu.menuPath"
              :index="menu.menuPath"
              @click="moveHandle(menu.menuPath)"
            >
              <el-icon><component :is="menu.icon" /></el-icon>
              {{ menu.menuNm }}
            </el-menu-item>

            <!-- 상위 메뉴 -->
            <el-sub-menu v-else :index="menu.menuId">
              <template #title>
                <el-icon><Folder /></el-icon>
                {{ menu.menuNm }}
              </template>
              <!-- 하위 메뉴 -->
              <el-menu-item
                v-for="sub in getSubMenus(menu.menuId)"
                :key="sub.menuId"
                :index="sub.menuPath"
                @click="moveHandle(sub.menuPath)"
              >
                <el-icon><component :is="sub.icon" /></el-icon>
                {{ sub.menuNm }}
              </el-menu-item>
            </el-sub-menu>
          </template>
        </el-menu>
      </el-scrollbar>
    </el-aside>

    <!-- 메인 컨텐츠 -->
    <el-container class="main-container">
      <el-header class="header">
        <router-link to="/">
          <el-icon size="17"><HomeFilled /></el-icon>
        </router-link>
        {{ today + headerText }}
      </el-header>
      <el-main class="main-content">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import axios from 'axios'
// import { ElMessageBox } from 'element-plus'

export default {
  name: 'BaseLayout',
  data() {
    return {
      activeMenu: '1-1',
      menus: [],
      headerText: '전주청과 25번',
      curDate: new Date(),
      today: null,
    }
  },
  computed: {
    // 상위 메뉴 (menuLvl === 1)
    topMenus() {
      return this.menus.filter((menu) => menu.menuLvl === 1)
    },
  },
  created() {
    this.getMenu()
    this.updateNow()
  },
  methods: {
    getMenu() {
      axios
        .get('/api/main/getMenu')
        .then((res) => {
          this.menus = res.data
        })
        .catch((err) => {
          console.error(err)
        })
    },
    getSubMenus(parentId) {
      return this.menus.filter(
        (menu) => menu.menuLvl > 1 && menu.menuId.startsWith(parentId.slice(0, 2)),
      )
    },
    moveHandle(menuPath) {
      this.$router.push({ path: menuPath })
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

<style scoped>
.aside {
  position: fixed;
  top: 0;
  left: 0;
  width: 150px;
  height: 100vh;
  z-index: 1000;
  border-right: 1px solid var(--el-border-color);
  overflow: hidden;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.aside::after {
  content: '';
  flex-grow: 1;
  background-color: var(--el-color-primary-light-8);
  border-right: 1px solid var(--el-border-color);
}

.header {
  position: fixed;
  top: 0;
  left: 150px;
  right: 0;
  height: 60px;
  line-height: 60px;
  background-color: var(--el-color-primary-light-8);
  padding: 0 20px;
  text-align: right;
  z-index: 1000;
  font-weight: bold;
}

.main-container {
  margin-left: 150px;
  margin-top: 60px;
}

.main-content {
  padding: 20px;
  height: calc(100vh - 60px);
  overflow: auto;
}
</style>
