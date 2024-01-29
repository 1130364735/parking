<template>
	<div class="hello">
		<el-row>
			<el-button type="primary" @click="dujvan()">杜鹃公园</el-button>
			<el-button type="primary" @click="heping()">和平街</el-button>
			<el-button type="success" @click="wendemu()">温德姆大酒店和郫县金融中心</el-button>
			<el-button type="success" @click="aotelaisi()">花样世界奥特莱斯和绿地缤纷城</el-button>
			<el-button type="success" @click="zhengwuzhongxin()">郫都区政务中心和北门菜市</el-button>
			<el-button type="success" @click="hengchuangshudu()">恒创蜀都和蜀都新天地</el-button>
			<el-button type="success" @click="zhongtieshiji()">中铁世纪中心和百伦广场</el-button>
			<el-button type="success" @click="keyunzhongxin()">客运中心和万达广场</el-button>
		</el-row>
	</div>
</template>

<script>
	import axios from 'axios';
	export default {
		name: 'HelloWorld',
		props: {
			msg: String
		},
		methods: {
			heping() {
				this.$confirm('确定要更新和平街?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$message({
						type: 'success',
						message: '更新开始!'
					});
					axios.post('/api/parking/heping').then(res => {
						console.log(res)
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消更新'
					});
				});
			},
			dujvan() {
				this.$confirm('确定要更新杜鹃公园?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$message({
						type: 'success',
						message: '更新开始!'
					});
					axios.post('/api/parking/dujvan').then(res => {
						console.log(res)
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消更新'
					});
				});
			},
			wendemu() {
				this.$confirm('确定要更新温德姆大酒店和郫县金融中心?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$message({
						type: 'success',
						message: '更新开始!'
					});
					axios.post('/api/parking/wendemu').then(res => {
						console.log(res)
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消更新'
					});
				});
			},
			aotelaisi() {
				this.$confirm('确定要更新花样世界奥特莱斯和绿地缤纷城?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$message({
						type: 'success',
						message: '更新开始!'
					});
					axios.post('/api/parking/aotelaisi').then(res => {
						console.log(res)
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消更新'
					});
				});
			},
			zhengwuzhongxin() {
				this.$confirm('确定要更新郫都区政务中心和北门菜市?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$message({
						type: 'success',
						message: '更新开始!'
					});
					axios.post('/api/parking/zhengwuzhongxin').then(res => {
						console.log(res)
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消更新'
					});
				});
			},
			hengchuangshudu() {
				this.$confirm('确定要更新恒创蜀都和蜀都新天地?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$message({
						type: 'success',
						message: '更新开始!'
					});
					axios.post('/api/parking/hengchuangshudu').then(res => {
						console.log(res)
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消更新'
					});
				});
			},
			zhongtieshiji() {
				this.$confirm('确定要更新中铁世纪中心和百伦广场?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$message({
						type: 'success',
						message: '更新开始!'
					});
					axios.post('/api/parking/zhongtieshiji').then(res => {
						console.log(res)
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消更新'
					});
				});
			},
			keyunzhongxin() {
				this.$confirm('确定要更新客运中心和万达广场?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$message({
						type: 'success',
						message: '更新开始!'
					});
					axios.post('/api/parking/keyunzhongxin').then(res => {
						console.log(res)
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消更新'
					});
				});
			},



		},
		created() {
			document.title = '停车场数据'
		},
	}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	h3 {
		margin: 40px 0 0;
	}

	ul {
		list-style-type: none;
		padding: 0;
	}

	li {
		display: inline-block;
		margin: 0 10px;
	}

	a {
		color: #42b983;
	}
</style>
