<template>
  <el-container>
    <!-- 사이드 메뉴 -->
    <el-aside width="200px">
      <el-scrollbar>
        <el-menu :default-openeds="['H2000']" :default-active="activeMenu">
          <template v-for="menu in topMenus" :key="menu.menuId">
            <!-- 단일 메뉴 -->
            <el-menu-item
              v-if="menu.menuPath"
              :index="menu.menuPath"
              @click="moveHandle(menu.menuPath)"
            >
              <el-icon></el-icon>
              {{ menu.menuNm }}
            </el-menu-item>

            <!-- 상위 메뉴 -->
            <el-sub-menu v-else :index="menu.menuId">
              <template #title>
                <el-icon></el-icon>
                {{ menu.menuNm }}
              </template>
              <!-- 하위 메뉴 -->
              <el-menu-item
                v-for="sub in getSubMenus(menu.menuId)"
                :key="sub.menuId"
                :index="sub.menuPath"
                @click="moveHandle(sub.menuPath)"
              >
                <el-icon></el-icon>
                {{ sub.menuNm }}
              </el-menu-item>
            </el-sub-menu>
          </template>
        </el-menu>
      </el-scrollbar>
    </el-aside>

    <!-- 메인 컨텐츠 -->
    <el-container>
      <el-header class="header">
        <router-link to="/">
          <el-icon size="17"><HomeFilled /></el-icon>
        </router-link>
        {{ today + headerText }}
      </el-header>
      <el-main>
        <el-scrollbar class="main-content">
          <!-- 페이지 컨텐츠 -->
          <!-- <slot /> -->
          <router-view />
          <!-- 이곳에 자식 페이지가 렌더링됩니다 -->
        </el-scrollbar>
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
.header {
  position: relative;
  text-align: right;
  background-color: var(--el-color-primary-light-7);
  padding: 10px;
  font-weight: bold;
}
.main-content {
  height: calc(100vh - 100px); /* 헤더와 메뉴 제외한 높이 계산 */
  overflow: auto;
}
</style>
