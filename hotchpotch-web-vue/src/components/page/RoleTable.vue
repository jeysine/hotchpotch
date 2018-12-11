<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" icon="save" class="handle-del mr10" @click="handleSave()">新增</el-button>
                <el-select v-model="select_cate" placeholder="筛选条件" class="handle-select mr10">
                    <el-option key="1" label="全部" value="all"></el-option>
                    <el-option key="2" label="角色名" value="name"></el-option>
                </el-select>
                <el-input v-model="select_word" placeholder="筛选关键词" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
            </div>
            <el-table :data="data" border style="width: 100%" ref="multipleTable" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column prop="name" label="角色名"></el-table-column>
                <el-table-column prop="active" label="状态" :formatter="formatter"></el-table-column>
                <el-table-column prop="createDate" label="创建时间"></el-table-column>
                <el-table-column prop="createBy" label="创建人"></el-table-column>
                <el-table-column label="操作" width="180">
                    <template slot-scope="scope">
                        <el-button size="small" @click="handleAuth(scope.$index, scope.row)">授权</el-button>
                        <!--<el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">{{ active }}</el-button>-->
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination @current-change="handleCurrentChange" layout="prev, pager, next" :total="10" :page-sizes="[10, 20, 30, 40]" :page-size="pageSize" :current-page="pageNum">
                </el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="授权" :visible.sync="authVisible" width="30%">
            <el-table :data="menuData" border style="width: 100%" ref="authMenuTable" @>
                <el-table-column prop="title" label="菜单"></el-table-column>
                <el-table-column label="授权" width="55">
                    <template slot-scope="menuScope">
                        <input type="checkbox" :value="menuScope.row.requireAuth" :checked="menuScope.row.requireAuth" @click="authSelectionChange(menuScope.$index, menuScope.row)"/>
                    </template>
                </el-table-column>
            </el-table>
            <span slot="footer" class="dialog-footer">
                <el-button @click="authVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveAuth">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 编辑弹出框 -->
        <el-dialog title="新增"  :visible.sync="editVisible" width="30%">

            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item label="角色名" prop="name">
                    <el-input v-model="form.name" placeholder="1-10个字符，支持任意字符"></el-input>
                </el-form-item>
                <el-form-item label="角色编码" prop="code">
                    <el-input v-model="form.code" placeholder="1-10个字符，支持下划线，推荐用英文大写, 如ADMIN"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit('form')">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        data() {
            return {
                selectUrl: '/jeysine/admin/private/role/page',
                saveOrUpdateUrl: '/jeysine/admin/private/role',
                menuAuthUrl: '/jeysine/admin/private/menu/auth',
                menuData: [],
                tableData: [],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                del_list: [],
                form: {},
                is_search: false,
                editVisible: false,
                authVisible: false,
                pageSize: 10,
                pageNum: 1,
                idx: -1,
                handleRoleId: "",
                rules: {
                    name: [
                        { required: true, message: '请输入角色名', trigger: 'blur' }
                    ],
                    code: [
                        { required: true, message: '请输入角色编码', trigger: 'blur' }
                    ]
                }
            }
        },
        created() {
            this.menuData = []
            this.getData();
        },
        computed: {
            data() {
                return this.tableData
            }
        },
        methods: {
            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
            },
            // 获取 easy-mock 的模拟数据
            getData() {
                let url = this.selectUrl + '?pageSize=' + this.pageSize + '&pageNum=' + this.pageNum
                let queryField = this.handleQueryDate()
                let _this = this
                this.$axios.post(url, queryField).then((res) => {
                    if (_this.validateCode(res)) {
                        _this.tableData = res.data.data.list;
                    }

                })
            },
            search() {
                this.is_search = true;
                this.getData()
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleSave() {
                this.save = true
                this.form = { }
                this.editVisible = true;
            },
            saveEdit(formName) {
                this.$refs[formName].validate((valid) => {
                    if(!valid) {
                        return ;
                    }
                    let _this = this
                    this.form.code = 'ROLE_' + this.form.code
                    this.form.active = true
                    this.$axios.post(this.saveOrUpdateUrl, this.form).then((res) => {
                        if (!_this.validateCode(res)) {
                            _this.$message.error(res.data.message);
                            return ;
                        }
                        _this.tableData.unshift(res.data.data)
                        _this.$message.success(`新增成功`);
                        this.editVisible = false;
                    })
                });
            },
            saveAuth() {
                let _this = this
                this.$axios.post(this.menuAuthUrl, this.menuData).then((res) => {
                    _this.menuData = res.data.data;
                })
                this.authVisible = false;
            },
            handleAuth(index, row) {
                let _this = this
                this.handleRoleId = this.tableData[index].id
                let url = this.menuAuthUrl + '?roleId=' + this.tableData[index].id
                this.$axios.get(url).then((res) => {
                    _this.menuData = res.data.data;
                    this.authVisible = true;
                })

            },

            handleDelete(index, row) {
                this.idx = index;
                this.delVisible = true;
            },
            handleQueryDate() {
                let queryField = {}
                switch (this.select_cate) {
                    case 'all' : queryField = { }; break;
                    case 'name' : queryField = { name : this.select_word }; break;
                    case 'active' : queryField = { active : this.select_word }; break;
                    default: queryField = { }; break;
                }
                return queryField
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            authSelectionChange(index, row) {
                this.menuData[index].roleId = this.handleRoleId
                this.menuData[index].editAuth = row.editAuth = !row.editAuth
                this.menuData[index].requireAuth = row.requireAuth = !row.requireAuth
            },
            formatter(row, column) {
                let type = ''
                switch (row.active) {
                    case true:
                        type = '正常';
                        break;
                    case false:
                        type = '注销';
                        break;
                }
                return type;
            }
        }
    }

</script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }
    .del-dialog-cnt{
        font-size: 16px;
        text-align: center
    }
</style>
