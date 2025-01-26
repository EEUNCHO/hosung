import BaseLayout from '@/layouts/BaseLayout.vue'
import Home from '@/views/HomeView.vue'
import Pur from '@/views/pur.vue'
import ItemDist from '@/views/itemDist.vue'
import OutstAmt from '@/views/outstAmt.vue'
import Coll from '@/views/coll.vue'
import CnptDist from '@/views/cnptDist.vue'
import CnptInq from '@/views/cnptInq.vue'
import Stock from '@/views/stock.vue'
import ItemInq from '@/views/itemInq.vue'
import Board from '@/views/board.vue'

const constantRoutes = [
  {
    path: '/',
    component: BaseLayout,
    children: [
      { path: '', component: Home }, // Home 페이지
      { path: 'pur', component: Pur }, // 매입
      { path: 'itemDist', component: ItemDist }, // 물품 분배
      { path: 'outstAmt', component: OutstAmt }, // 미수금
      { path: 'coll', component: Coll }, // 수금
      { path: 'cnptDist', component: CnptDist }, // 거래처 분배
      { path: 'cnptInq', component: CnptInq }, // 거래처 조회
      { path: 'stock', component: Stock }, // 재고
      { path: 'itemInq', component: ItemInq }, // 물품 조회
      { path: 'board', component: Board }, // 게시판
    ],
  },
]

export default constantRoutes
