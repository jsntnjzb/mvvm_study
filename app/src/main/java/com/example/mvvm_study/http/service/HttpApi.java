package com.example.mvvm_study.http.service;

import com.example.mvvm_study.http.entities.BaseResponse;

import java.util.Map;

import androidx.collection.ArrayMap;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface HttpApi {
    /**
     * 查询apk版本信息
     */
    @GET("getApk")
    Observable<Object> getApk(@Url String url);

    /**
     * 登陆
     */
    @POST("/deviceLogin")
    Observable<BaseResponse<Object>> login(@QueryMap ArrayMap<String, String> map);

    /**
     * 心跳
     */
    @GET("/heartbeat/{deviceCode}")
    Observable<BaseResponse<Object>> getHeartBeat(@Path("deviceCode") String deviceCode);


    /**
     * 获取配置页配置信息
     */
    @GET("deviceSetting")
    Observable<Object> getDeviceSettings();

    /**
     * 更新设备地址信息
     */
    @GET("/device/update")
    Observable<BaseResponse<Object>> updateDeviceLocation(@QueryMap Map<String, String> map);

    /**
     * 更新设备状态
     */
    @GET("/device/update")
    Observable<BaseResponse<Object>> updateDeviceStatus(@QueryMap Map<String, String> map);


    /**
     * 获取设备商品信息列表
     */
    @GET("deviceSetting/list")
    Observable<Object> getDeviceProducts(@QueryMap Map<String, String> map);

    /**
     * 查询设备
     */
    @GET("/device/{deviceCode}")
    Observable<BaseResponse<Object>> getDevice(@Path("deviceCode") String deviceCode);

    /**
     * 添加设备信息
     */
    @FormUrlEncoded
    @POST("deviceSetting/save")
    Observable<Object> saveDeviceSetting(@Field("deviceId") String deviceId, @Field("cupSize") int cupSize, @Field("materialBoxes") String materialBoxes);

    /**
     * 新增订单
     */
    @FormUrlEncoded
    @POST("goodsOrder")
    Observable<BaseResponse<Object>> createOrder(@Field("deviceCode") String deviceCode, @Field("orders") String orders);

    @FormUrlEncoded
    @POST("goodsOrder")
    Observable<BaseResponse<Object>> createOrder(@Url String url, @Field("deviceCode") String deviceCode, @Field("orders") String orders);

    /**
     * 修改订单
     */
    @GET("/goodsOrder/update")
    Observable<BaseResponse<Object>> updateOrder(@Query("id") long id, @Query("orderStatus") int orderStatus);

    /**
     * 修改订单状态
     */
    @GET("/goodsOrder/status/update")
    Observable<BaseResponse<Object>> updateOrderStatus(@Query("orderId") long id, @Query("orderStatus") int orderStatus);

    /**
     * 设备销量统计表
     */
    @GET("/goodsStatistical")
    Observable<BaseResponse<Object>> getGoodsStatistical(@QueryMap Map<String, String> map);

    /**
     * 获取软件版本号
     */
    @GET("/softVersion")
    Observable<BaseResponse<Object>> getSoftVersion(@Query("versionNumber") String versionNumber);

    /**
     * 冲泡配方列表
     */
    //    @GET("/formula")
    //    Observable<BaseResponse<ArrayList<PeiFang>>> getFormulaList();

    /**
     * 查询订单支付状态
     */
    @GET("/pay/order/tradestatus")
    Observable<BaseResponse<Object>> getOrderStatus(@QueryMap Map<String, Long> map);

    /**
     * 提交故障信息
     */
    @FormUrlEncoded
    @POST("/malfunctionLog")
    Observable<BaseResponse<Object>> addMalfunctionLog(@FieldMap Map<String, String> map);

    /**
     * 广告投放
     */
    @GET("/commercialAdvertiseLog/list")
    Observable<BaseResponse<Object>> getCommercialAdvertiseLog(@QueryMap Map<String, Integer> map);
}
