import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import '@/views/el-custom.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import { ModuleRegistry } from 'ag-grid-community'
import {
  ClientSideRowModelModule,
  ValidationModule,
  NumberEditorModule,
  DateEditorModule,
  TextEditorModule,
  SelectEditorModule,
} from 'ag-grid-community'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.use(ElementPlus)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

ModuleRegistry.registerModules([
  ClientSideRowModelModule,
  ValidationModule,
  NumberEditorModule,
  DateEditorModule,
  TextEditorModule,
  SelectEditorModule,
])

app.mount('#app')
