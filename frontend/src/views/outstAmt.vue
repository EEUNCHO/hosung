<template>
  <el-form :inline="true">
    <el-form-item :label="'매입일자'">
      <el-date-picker v-model="searchDate" type="date" />
    </el-form-item>
    <el-form-item>
      <el-button @click="openPopup(null)"> 신규 </el-button>
      <el-button type="primary" @click="getBoardList"> 조회 </el-button>
    </el-form-item>
  </el-form>
  <ag-grid-vue :rowData="boardList" :columnDefs="colDefs" style="height: 600px"> </ag-grid-vue>

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
import { AgGridVue } from 'ag-grid-vue3'
import { ElMessageBox } from 'element-plus'
import moment from 'moment'

export default {
  name: 'purMng',
  components: {
    AgGridVue,
  },
  data() {
    return {
      searchDate: null,
      boardList: [],
      colDefs: [
        { headerName: '순번', field: 'boardNo', width: 85, minWidth: 85 },
        { headerName: '제목', field: 'boardTitle' },
        { headerName: '내용', field: 'boardContentFirst' },
      ],
      dialogVisible: false,
      form: {
        boardTitle: null,
        boardContent: null,
      },
      btnVisible: true,
    }
  },
  created() {
    this.getBoardList()
  },
  mounted() {
    this.searchDate = moment().format('YYYY-MM-DD')
  },
  methods: {
    getBoardList() {
      axios
        .get('/api/main/getBoard')
        .then((res) => {
          this.boardList = res.data
        })
        .catch((err) => {
          console.error(err)
        })
    },
    addBoard() {
      if (!this.form?.boardTitle) {
        ElMessageBox.alert('제목을 입력해주세요.', '오류', {
          confirmButtonText: '확인',
          type: 'warning',
        })
        return
      }

      ElMessageBox.confirm('저장하시겠습니까?.', '안내', {
        confirmButtonText: '확인',
        cancelButtonText: '취소',
        type: 'info',
      })
        .then(() => {
          axios
            .post('/api/main/addBoard', this.form)
            .then(() => {
              ElMessageBox.confirm('저장되었습니다.', '저장', { type: 'success' }).then(() => {
                this.dialogVisible = false
                this.getBoardList()
              })
            })
            .catch((err) => {
              console.error(err)
            })
        })
        .catch(() => {})
    },
    openPopup(item) {
      if (item) {
        this.form = item
        this.btnVisible = false
      } else {
        this.form = { boardTitle: null, boardContent: null }
        this.btnVisible = true
      }
      this.dialogVisible = true
    },
  },
}
</script>
