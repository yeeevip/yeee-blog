<template>
  <el-dialog :title="!id ? '新增' : '修改'"  class="mod-wxMenuItm-edit"
    :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="140px">
      <el-row :gutter="20">
        <el-col :span="11">
          <el-form-item prop="pid" label="上级对象" class="cat-list">
            <el-popover v-model="treeVisible" ref="catListPopover" placement="bottom-start" trigger="click">
              <el-tree
                :data="treeList"
                :props="{ label: 'nm', children: 'children' }"
                node-key="id"
                ref="tree"
                :highlight-current="true"
                :expand-on-click-node="false"
                accordion
                @current-change="treeCurrentChangeHandle">
              </el-tree>
            </el-popover>
            <el-input v-model="dataForm.pnm" v-popover:catListPopover :readonly="true" placeholder="上级对象">
              <i v-if="dataForm.pid" slot="suffix" @click.stop="treeSetDefaultHandle()" class="el-icon-circle-close el-input__icon"></i>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="响应动作类型" prop="type">
            <!-- <el-input v-model="dataForm.type" placeholder="响应动作类型"></el-input> -->
            <el-select v-model="dataForm.type" placeholder="请选择">
              <el-option label="点击推事件" value="click"/>
              <el-option label="跳转URL" value="view"/>
              <el-option label="跳转到小程序" value="miniprogram"/>
              <el-option label="扫码推事件" value="scancode_push"/>
              <el-option label="扫码推事件且弹出“消息接收中”提示框" value="scancode_waitmsg"/>
              <el-option label="弹出系统拍照发图" value="pic_sysphoto"/>
              <el-option label="弹出拍照或者相册发图" value="pic_photo_or_album"/>
              <el-option label="弹出微信相册发图器" value="pic_weixin"/>
              <el-option label="弹出地理位置选择器" value="location_select"/>
              <el-option label="下发消息（除文本消息）" value="media_id"/>
              <el-option label="跳转图文消息URL" value="view_limited"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="标题" prop="name">
            <el-input v-model="dataForm.name" placeholder="标题"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="排序" prop="seq">
            <el-input-number v-model="dataForm.seq" :min="1" :max="10000" label="排序"></el-input-number>
          </el-form-item>
        </el-col>
        <el-col :span="22">
          <el-form-item label="网页链接" prop="url">
            <el-input v-model="dataForm.url" placeholder="网页链接"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="是否oauth" prop="hasOauth">
            <el-radio-group v-model="dataForm.hasOauth">
              <el-radio :label = 0>否</el-radio>
              <el-radio :label = 1>是</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="备注" prop="rmks">
            <el-input v-model="dataForm.rmks" placeholder="备注"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="应用授权作用域" prop="scope" v-if="dataForm.hasOauth === 1">
            <el-select v-model="dataForm.scope" placeholder="请选择">
              <el-option label="snsapi_userinfo" value="snsapi_userinfo"/>
              <el-option label="snsapi_base" value="snsapi_base"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="重定向带上的参数" prop="state" v-if="dataForm.hasOauth === 1">
            <el-input v-model="dataForm.state" placeholder="重定向带上的参数"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="菜单KEY值" prop="key">
            <el-input v-model="dataForm.ke" placeholder="菜单KEY值，用于消息接口推送"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="小程序的appid" prop="appid">
            <el-input v-model="dataForm.appid" placeholder="小程序的appid"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="小程序的页面路径" prop="pagepath">
            <el-input v-model="dataForm.pagepath" placeholder="小程序的页面路径"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="永久素材ID" prop="mediaId">
            <el-input v-model="dataForm.mediaId" placeholder="永久素材ID"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import debounce from 'lodash/debounce'

  export default {
    data () {
      return {
        id: null,
        visible: false,
        treeVisible: false,
        treeList: [],
        dataForm: {
          id: '',
          menuId: '',
          pid: '',
          pnm: '',
          type: null,
          name: '',
          ke: '',
          url: null,
          mediaId: null,
          appid: '',
          pagepath: '',
          hasOauth: 0,
          scope: '',
          state: '',
          seq: '',
          rmks: ''
        },
        dataRule: {
          menuId: [
            { required: true, message: '菜单主键不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '标题不能为空', trigger: 'blur' }
          ],
          hasOauth: [
            { required: true, message: '是否oauth不能为空', trigger: 'blur' }
          ],
          seq: [
            { required: true, message: '排序不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.id = id
        this.visible = true
        this.dataForm.pid = ''
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.id) {
            this.getInfo()
          }
          this.listMenuItmTree()
        })
      },
      treeCurrentChangeHandle (data) {
        this.dataForm.pid = data.id
        this.dataForm.pnm = data.nm
        this.treeVisible = false
      },
      treeSetDefaultHandle () {
        this.dataForm.pid = null
        this.dataForm.pnm = '第一级对象'
      },
      // 获取菜单列表
      listMenuItmTree () {
        return this.$http.get(`/sys/wxMenuItm/listTreeNode?menuId=${this.dataForm.menuId}`).then(({ data: res }) => {
          if (res.code !== 0) {
            return this.$message.error(res.msg)
          }
          this.treeList = res.data.list
        }).catch(() => {})
      },
      // 获取微信菜单子项信息
      getInfo () {
        this.$http.get('/sys/wxMenuItm/info/' + this.id).then(({data: res}) => {
          if (res.code !== 0) {
            return this.$message.error(res.msg)
          }
          this.dataForm = {
            ...this.dataForm,
            ...res.data
          }
        }).catch(() => {})
      },
      // 表单提交
      dataFormSubmit: debounce(function () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            let url = !this.id ? '/sys/wxMenuItm/add' : '/sys/wxMenuItm/upd'
            this.$http.post(url, {...this.dataForm}).then(({data: res}) => {
              if (res && res.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(res.msg)
              }
            })
          }
        })
      }, 1000, { 'leading': true, 'trailing': false })
    }
  }
</script>
