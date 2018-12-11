<template>
    <div class="login-wrap">
        <div class="ms-title">后台管理系统</div>
        <div class="ms-login">
            <el-form :model="user" :rules="rules" ref="user" label-width="0px" class="demo-ruleForm">
                <el-form-item prop="account">
                    <el-input v-model="user.account" placeholder="account"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="password" v-model="user.password" @keyup.enter.native="submitForm('user')"></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm('user')">登录</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                user: {
                    account: '',
                    password: ''
                },
                rules: {
                    account: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                let _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.login()
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            login() {
                let _this = this
                this.$axios.post('/jeysine/admin/public/user/login', this.user).then(function (res) {
                    if (_this.validateCode(res)) {
                        _this.user = res.data.data
                        sessionStorage.setItem('ms_username',_this.user.account);
                        _this.$router.push('/');
                    } else {
                        _this.error('帐号或密码错误')
                    }
                }, function () {
                    _this.systemError(_this)
                })
            },
            error(message) {
                this.$notify.error({
                    title: '错误',
                    message: message
                });
            }
        }
    }
</script>

<style scoped>
    .login-wrap{
        position: relative;
        width:100%;
        height:100%;
    }
    .ms-title{
        position: absolute;
        top:50%;
        width:100%;
        margin-top: -230px;
        text-align: center;
        font-size:30px;
        color: #fff;

    }
    .ms-login{
        position: absolute;
        left:50%;
        top:50%;
        width:300px;
        height:160px;
        margin:-150px 0 0 -190px;
        padding:40px;
        border-radius: 5px;
        background: #fff;
    }
    .login-btn{
        text-align: center;
    }
    .login-btn button{
        width:100%;
        height:36px;
    }
</style>
