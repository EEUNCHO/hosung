<template>
  <el-button @click="openPopup(null)"> 신규 </el-button>

  <el-table :data="boardList" stripe>
    <el-table-column prop="boardNo" label="순번" />
    <el-table-column prop="boardTitle" label="제목">
      <template #default="scope">
        <el-button @click="openPopup(scope.row)">
          {{ scope.row.boardTitle }}
        </el-button>
      </template>
    </el-table-column>
    <el-table-column prop="boardContentFirst" label="내용" />
  </el-table>

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
import { ElMessageBox } from 'element-plus'

export default {
  name: 'boardList',
  data() {
    return {
      boardList: [],
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
  methods: {
    getBoardList() {
      axios
        .get('/api/main/getBoard')
        .then((res) => {
          console.log(res)
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
