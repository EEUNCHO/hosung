<template>
  <el-form :inline="true">
    <el-form-item :label="'매입일자'">
      <el-date-picker v-model="searchDate" type="date" value-format="YYYY-MM-DD" />
    </el-form-item>
    <el-form-item>
      <el-button @click="openPopup(null)"> 신규 </el-button>
      <el-button type="primary" @click="getPurchaseList"> 조회 </el-button>
    </el-form-item>
  </el-form>
  <ag-grid-vue
    ref="agGrid"
    :rowData="purchaseList"
    :columnDefs="colDefs"
    :defaultColDef="defaultColDef"
    :gridOptions="gridOptions"
    style="width: 100%; height: 600px"
  >
  </ag-grid-vue>

  <el-dialog v-model="dialogVisible" title="신규 작성" width="500">
    <el-form :model="form">
      <el-form-item label="제목">
        <el-input v-model="form.boardTitle" />
      </el-form-item>
      <el-form-item label="내용">
        <el-input v-model="form.boardContent" :rows="5" type="textarea" />
      </el-form-item>
    </el-form>
    <template v-if="btnVisible" #footer>
      <el-button @click="dialogVisible = false"> 취소 </el-button>
      <el-button type="primary" @click="addBoard"> 저장 </el-button>
    </template>
  </el-dialog>
</template>

<script>
import axios from 'axios'
import { ref, reactive, onMounted } from 'vue'
import { AgGridVue } from 'ag-grid-vue3'
import { ElMessageBox } from 'element-plus'
import moment from 'moment'

export default {
  name: 'purMng',
  components: {
    'ag-grid-vue': AgGridVue,
  },
  setup() {
    const agGrid = ref(null)
    const searchDate = ref(moment().format('YYYY-MM-DD'))
    const dialogVisible = ref(false)
    const purchaseList = ref([])
    const btnVisible = ref(true)
    const cnptCd = ['0001', '0002']

    const form = reactive({
      boardTitle: null,
      boardContent: null,
    })

    const colDefs = [
      { headerName: '순번', field: 'rowNum', width: 60, minWidth: 60, editable: false },
      { headerName: '매입 일자', field: 'purDt', width: 110 },
      {
        headerName: '원장 번호',
        field: 'cnptCd',
        editable: true,
        cellEditor: 'agSelectCellEditor',
        cellEditorParams: {
          values: cnptCd,
        },
        width: 95,
      },
      { headerName: '물품 코드', field: 'itemCd', width: 95 },
      { headerName: '출하자', field: 'shipper', width: 200 },
      { headerName: '중량', field: 'weight', width: 60 },
      { headerName: '단위', field: 'unitCd', width: 60 },
      { headerName: '수량', field: 'purCnt', width: 60 },
      {
        headerName: '매입 단가',
        field: 'purUntprc',
        valueFormatter: (p) => {
          if (!p?.value) ''
          else return Math.floor(p.value).toLocaleString()
        },
        width: 100,
      },
      {
        headerName: '매입 금액',
        field: 'purAmt',
        valueFormatter: (p) => {
          if (!p?.value) ''
          else return Math.floor(p.value).toLocaleString()
        },
        width: 110,
      },
    ]

    const defaultColDef = {
      editable: true,
      resizable: true,
    }

    const getPurchaseList = async () => {
      try {
        const res = await axios.get('/api/pur/getPurchase', {
          params: { searchDate: searchDate.value },
        })
        purchaseList.value = res.data // 데이터 바인딩
      } catch (err) {
        console.error(err)
      }
    }

    const openPopup = (item) => {
      if (item) {
        form.boardTitle = item.boardTitle
        form.boardContent = item.boardContent
        btnVisible.value = false
      } else {
        form.boardTitle = null
        form.boardContent = null
        btnVisible.value = true
      }
      dialogVisible.value = true
    }

    const addBoard = async () => {
      if (!form.boardTitle) {
        ElMessageBox.alert('제목을 입력해주세요.', '오류', {
          confirmButtonText: '확인',
          type: 'warning',
        })
        return
      }

      try {
        await ElMessageBox.confirm('저장하시겠습니까?', '안내', {
          confirmButtonText: '확인',
          cancelButtonText: '취소',
          type: 'info',
        })
        await axios.post('/api/main/addBoard', form)
        ElMessageBox.alert('저장되었습니다.', '저장', { type: 'success' })
        dialogVisible.value = false
        getPurchaseList()
      } catch (err) {
        console.error(err)
      }
    }

    const gridOptions = {
      onCellKeyDown: (params) => {
        if (params.event.key === 'Enter') {
          const lastRowIndex = purchaseList.value.length - 1
          const isLastRow = params.rowIndex === lastRowIndex
          const isLastCol = params.column.getColId() === colDefs[colDefs.length - 1].field

          if (isLastRow && isLastCol) {
            addNewRow()
          }
        }
      },
      onCellEditingStopped: (params) => {
        const rowData = params.data
        if (params.colDef.field === 'purCnt' || params.colDef.field === 'purUntprc') {
          rowData.purAmt = (rowData.purCnt || 0) * (rowData.purUntprc || 0)
        }
        const updatedList = [...purchaseList.value]
        updatedList[params.rowIndex] = rowData
        purchaseList.value = updatedList
      },
    }

    const addNewRow = () => {
      const newRow = { rowNum: purchaseList.value.length + 1, purDt: moment().format('YYYY-MM-DD') }
      purchaseList.value = [...purchaseList.value, newRow]
    }

    onMounted(() => {
      getPurchaseList()
    })

    return {
      agGrid,
      colDefs,
      defaultColDef,
      searchDate,
      dialogVisible,
      purchaseList,
      form,
      btnVisible,
      getPurchaseList,
      addBoard,
      openPopup,
      gridOptions,
      addNewRow,
    }
  },
}
</script>
