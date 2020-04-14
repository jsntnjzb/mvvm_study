package com.example.mvvm_study.http.service;


public interface HttpInterface {
    /**
     * @param deviceCode 设备code
     * @param password   登陆密码
     */
    public void Login(String deviceCode, String password);

//    /**
//     * 获取服务器设备配置的商品信息
//     */
//    public void getDeviceSettingProducts();
//
//    public void getDevice(String deviceCode);
//
//    /**
//     * 上传设备位置信息
//     * */
//    public void updateDeviceLocation(DeviceInfo deviceInfo);
//
//    /**
//     * 更新设备状态
//     * */
//    public void updateDeviceStatus(DeviceInfo deviceInfo);
//
//    /**
//     * 获取该设备上商品信息列表
//     */
//    public void getDeviceProducts(CallBack callBack);
//
//    /**
//     * 配置设备信息
//     */
//    public void saveDeviceSetting(SavedSettings savedSettings);
//
//    /**
//     * 新增订单
//     */
//    public void createOrder(GoodOrder goodOrder);
//
//    public void createOrder(String url, GoodOrder goodOrder);
//
//    /**
//     * 修改订单
//     * */
//    public void updateOrder(GoodOrder goodOrder);
//
//    /**
//     * 修改订单状态
//     * */
//    public void updateOrderStatus(GoodOrder goodOrder);
//
//    /**
//     * 设备销量统计表
//     */
//    public void getGoodsStatistical(String deviceId, String startTime, String endTime);
//
//    /**
//     * 获取软件版本
//     */
//    public void getSoftVersion(String versionNumber);
//
//    /**
//     * 获取支付宝支付二维码图片
//     */
//    public void downLoadAliPaymentQRCode(String orderId);
//
//    /**
//     * 获取微信支付二维码图片
//     */
//    public void downLoadWxPaymentQRCode(String orderId);
//
//    /**
//     * 查询冲泡配方
//     * */
//    public void queryPeiFang(SavedSettings savedSettings);
//
//    /**
//     * 查询订单支付状态
//     * */
//    public void getOrderStatus(long orderId);
//
//    /**
//     * 上报故障日志
//     * */
//    public void addMalfunctionLog(MalfunctionLog malfunctionLog);
//
//    /**
//     * 获取广告位
//     * */
//    public void getCommercialAdvertiseLog(int pagNum, int pageSize);
//
//    /**
//     * App心跳
//     * */
//    public void getHeartBeat(String deviceCode);
}
