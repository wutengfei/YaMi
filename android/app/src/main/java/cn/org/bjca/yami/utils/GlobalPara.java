package cn.org.bjca.yami.utils;

/**
 * Created by 吴腾飞 on 2018/6/27.
 * 全局参数
 */

public class GlobalPara {
    public static final String SERVER_URL = "http://192.168.0.108:8080/bjca";//根地址
    public static final String PREDICTION_URL =
            SERVER_URL + "/prediction/prediction.json";// 预告页的url
    public static final String ORDER_URL =
            SERVER_URL + "/order/order.json";// 订餐页套餐的url
//    public static final String ADDMATERIAL_URL =
//            SERVER_URL + "/order/addMaterial.json";// 订餐页加料的url

    //TODO
    public static final String SURE_URL =
            SERVER_URL + " ";//确定页url

}
