export const constantRoutes = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/HomeView.vue'),
  },
  {
    path: '/board',
    name: 'board',
    component: () => import('../views/main/board.vue'),
  },
]

export default constantRoutes
