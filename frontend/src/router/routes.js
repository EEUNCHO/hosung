import BaseLayout from '@/layouts/BaseLayout.vue'
import Home from '@/views/HomeView.vue'
import Board from '@/views/board.vue'
// import Purchase from "@/views/Purchase.vue";
// import Distribution from "@/views/Distribution.vue";
// import Collection from "@/views/Collection.vue";

const constantRoutes = [
  {
    path: '/',
    component: BaseLayout,
    children: [
      { path: '', component: Home }, // Home 페이지
      { path: 'board', component: Board }, // 게시판
    ],
  },
]

export default constantRoutes
