<template>
	<view>
		<map  class="map"
		 			:latitude="latitude"
		 			:longitude="longitude"
		 			scale="16"
					:markers="covers"
					show-location:true
					@markertap
		 			>
		 			</map>
	</view>
</template>

<script>	
var QQMap = require('../../static/js/qqmap-wx-jssdk.min.js')
var qqmapsdk = new QQMap({key:'UTYBZ-ES563-SJS3I-3ZCK2-PK6QH-UWFXX'})
export default {	
	data() {
		return {
			id:0,
			title:'map',
			latitude:'40.001434',
			longitude:'116.321403',
			covers: [],
		}
	},
	methods: {
		markertap(e){
			console.log(e)
				var clickmarkerId = e.mp.detail.markerId
				//根据e.mp.detail.markerId跟撒点的id进行判断，如果相等就进行赋值
				for(var i=0;i<this.marker.length;i++){
					if(this.marker[i].id == clickmarkerId){
						this.sendlongitude = this.marker[i].longitude
						this.sendlatitude = this.marker[i].latitude
						this.sendname = this.marker[i].name
						this.sendaddress = this.marker[i].address
					}
				}
		},
		getSetting() {
			const that = this
			uni.getSetting({
				success(res) {
					if (res.authSetting['scope.userLocation']) {
						that.getLocation()
						
					} else {
						that.getAuthorize()
						
					}
				},
				fail(err) {
					console.log(err)
				}
			})
		},
		getLocation(){
			const that = this
			uni.getLocation({
				type: 'wgs84',
				success(data) {
					//成功回调
					that.$data.latitude = data.latitude;
					that.$data.longitude= data.longitude;
					qqmapsdk.search({
						keyword:'停车场',
						location:{
							latitude:data.latitude,
							longitude:data.longitude
						},
						success:function(res){
							console.log(res)
							for(var i = 0;i<res.data.length;i++){
								that.$data.covers.push({
									id:+res.data[i].id,
									latitude:res.data[i].location.lat,
									longitude:res.data[i].location.lng,
									iconPath:'../../static/images/parking.png',
									width:30,
									height:30,
									callout:{
										content:res.data[i].title,
										bgColor:'#000000',
										color:'#ffffff',
										fontSize:10,
										display:'BYCLICK',
										padding:'8',
										borderRadius:20			
									}
								})
							}
						},
						fail:function(res){
							console.log(res)
						}
					})
					that.$data.covers = [{
						id:0,
						latitude:data.latitude,
						longitude:data.longitude,
						iconPath:'../../static/images/location.png',
						width:40,
						height:30
					}]
				},
				fail(err) {
					console.log(err)
				}
			})
		},
		getAuthorize(){
			const that = this
			uni.authorize({
				scope:'scope.userLocation',
				success(res) {
					that.getLocation()
				},
				fail() {
					uni.showModal({
						title:'提示',
						content:'请授权',
						showCancel:false,
						confirmText:'确认授权',
						success() {
							uni.openSetting({
								success(res) {
									console.log(res)
								},
								fail(err) {
									console.log(err)
								}
							})
						}
					})
				}
			})
		}
	},
	
	onShow() {
		this.getSetting()
		// console.log(mapState(["addressInfo"]))
		
	}

	
}
</script>

<style>
.map{
	width: 100%;
	height: 100vh;
}
</style>