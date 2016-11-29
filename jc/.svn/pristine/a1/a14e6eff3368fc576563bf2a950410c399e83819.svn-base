package com.hgsoft.main.im.service;

import com.hgsoft.main.im.action.ChatEndpoint;
import com.hgsoft.main.im.entity.ChatMessage;
import com.hgsoft.util.MobileMD5;
import org.apache.cxf.common.util.StringUtils;

/**
 * Created by linlin on 2015/03/04/0004.
 */
public class ChatUtility {
    private final static double EARTH_RADIUS = 6378137;
    private static double radLat1;
    private static double radLat2;
    public final static String SECKEY = "a78!@&^%$#za1";

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     */
    public static double GetDistance(double lng1, double lat1, double lng2,
                                     double lat2) {
        if ((lng1 == 0 && lat1 == 0) || (lng2 == 0 && lat2 == 0)) {
            return 0;
        }
        radLat1 = rad(lat1);
        radLat2 = rad(lat2);
        double results = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin((radLat1 - radLat2) / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin((rad(lng1) - rad(lng2)) / 2), 2)));
        results = Math.round(results * EARTH_RADIUS * 10000) / 10000;
        return results;
    }

    public static boolean checkMessage(ChatMessage chatMessage){
        try{
            return ChatEndpoint.messageType.containsKey(chatMessage.getMessageType()) &&
                    !StringUtils.isEmpty(chatMessage.getSendId()) &&
                    !StringUtils.isEmpty(chatMessage.getMessageType())&&
                    !StringUtils.isEmpty(chatMessage.getRecipientId())&&
                    !StringUtils.isEmpty(chatMessage.getMessageContent())&&
                    (ChatEndpoint.SINGLE.equals(chatMessage.getSingleOrGroup()) || ChatEndpoint.GROUP.equals(chatMessage.getSingleOrGroup()) )&&
                    chatMessage.getMessageId().equals(MobileMD5.encodeByMD5(SECKEY + chatMessage.getSendId().toString()+chatMessage.getTime()));
        }catch (Exception e){
            return false;
        }
    }
   /*
    public static String formatToTwo(double d) {

        DecimalFormat a = new DecimalFormat("#,##0.00");

        String frmStr = a.format(d);

        return frmStr;

    }*/

}
