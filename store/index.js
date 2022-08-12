import Vue from "vue"
import Vuex from "vuex"
// 引入腾讯地图jssdk文件
import  QQMapWX from "../static/js/qqmap-wx-jssdk.min.js"
Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        // 默认城市
        
        addressInfo:{
            city: '苏州市',
            district: '昆山市',
            street: '',
            province: '江苏省',
            address: '',
            name: '',
            city_code: '',
            lat: '',
            lng: '',
        }

    },
    mutations: {
        
        newCityFun(state, newCity) {
            state.addressInfo.city = newCity.city
            state.addressInfo.district = newCity.district
            state.addressInfo.street = newCity.street
            state.addressInfo.province = newCity.province
            state.addressInfo.address = newCity.address
            state.addressInfo.name = newCity.name
            state.addressInfo.city_code = newCity.city_code
            state.addressInfo.lat = newCity.lat
            state.addressInfo.lng = newCity.lng
            console.log(state.addressInfo.city)
        }
    },
    actions: {
        getCity(context) {
            // 向用户发起授权请求，弹框提示
            uni.authorize({
                // 获取用户定位信息
                scope: "scope.userLocation",
                // 用户同意授权执行
                success() {
                    // 引入腾讯地图api
                    // 实例化API核心类
                    let qqmapsdk = new QQMapWX({
                        // 填写自己的Key值，这个值是与AppID绑定的
                        key: '6FQBZ-HPUR2-XVDUB-CNI5F-XQBP6-36FL7'
                    });
                    //获取位置信息
                    uni.getLocation({
                        type: 'gcj02',
                        success: function(res) {
                            console.log('当前位置的经度：' + res.longitude)
                            console.log('当前位置的纬度：' + res.latitude)
                            // 逆地址解析方法
                            qqmapsdk.reverseGeocoder({
                                location: {
                                    latitude: res.latitude,
                                    longitude: res.longitude
                                },
                                success(res) {
                                    var newCity = {}
                                    console.log(res)
                                    // 取到用户的定位城市，赋值传递出去
                                    newCity.city = res.result.address_component.city
                                    newCity.district = res.result.address_component.district
                                    newCity.street = res.result.address_component.street
                                    newCity.province = res.result.address_component.province
                                    newCity.address = res.result.address
                                    newCity.name = res.result.ad_info.name
                                    newCity.city_code = res.result.ad_info.city_code
                                    newCity.lat = res.result.location.lat
                                    newCity.lng = res.result.location.lng
                                    context.commit('newCityFun', newCity)
                                },
                                fail(res) {
                                    console.log("逆地址解析失败")
                                    console.log(res)
                                }
                            })
                        }
                    })
                },
                // 若用户不同意授权，弹框提示
                fail(res) {
                    uni.showToast({
                        icon: "none",
                        title: '注意：需要获取您的定位授权,否则部分功能将无法使用',
                        duration: 2000
                    })
                }
            })
        }
    }
})
export default store