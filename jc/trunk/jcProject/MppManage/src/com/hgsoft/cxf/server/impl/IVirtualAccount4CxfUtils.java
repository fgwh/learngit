package com.hgsoft.cxf.server.impl;

import com.hgsoft.cxf.common.ConstantUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/**
 * Created by Bruce.Zhan on 2014/8/6.
 */
public class IVirtualAccount4CxfUtils {

    // 日志打印
    private final static Logger log = Logger.getLogger(IVirtualAccount4CxfUtils.class);

    /**
     * Created by Bruce.Zhan on 2014/8/6.
     * 把JSONObject数据解析出来组装成Object[]数据
     *
     * @param json {
     *             "SourceSystemId" : 101,
     *             "UserId"         : 1,
     *             "UserName"       : "zhansan",
     *             "Password"       : "b594510740d2ac4261c1b2fe87850d08"
     *             }
     * @return Object[]
     */
    public static Object[] toHeadObjArray(JSONObject json) {

        if (log.isInfoEnabled()) {
            log.info("正在将头信息，JSONObject数据转换成Object[]数据");
        }

        /****start************************以下验证参数头信息是否存在，不存在则返回信息通知客户端****************************/

        // 验证源系统编号是否存在
        if (json.containsKey(ConstantUtils.HEAD_SOURCESYSTEMID) &&
                json.containsKey(ConstantUtils.HEAD_USERID) &&
                json.containsKey(ConstantUtils.HEAD_USERNAME) &&
                json.containsKey(ConstantUtils.HEAD_PASSWORD)) {

            // 验证通过获取信息并且组装返回
            Integer sourceSystemId = json.getInt(ConstantUtils.HEAD_SOURCESYSTEMID);
            Integer userId = json.getInt(ConstantUtils.HEAD_USERID);
            String userName = json.getString(ConstantUtils.HEAD_USERNAME);
            String password = json.getString(ConstantUtils.HEAD_PASSWORD);

            // 组装返回
            return new Object[]{sourceSystemId, userId, userName, password};

        }

        /****end**************************************************************************************************/

        return new Object[]{ConstantUtils.HEAD_CHECK_FLAG01_MESSAGE};
    }

    /**
     * Created by Bruce.Zhan on 2014/8/6.
     * 把JSONObject数据解析出来组装成Object[]数据
     *
     * @param json {
     *             "SourceSystemId" : 101,
     *             "UserId"         : 1,
     *             "UserName"       : "zhansan",
     *             "Password"       : "b594510740d2ac4261c1b2fe87850d08"
     *             }
     * @return Object[]
     */
    public static int checkCreatedBody(JSONObject json) {

        if (log.isInfoEnabled()) {
            log.info("正在验证实体信息");
        }

        /****start************************以下验证参数实体信息是否存在，不存在则返回信息通知客户端****************************/

        // 验证源系统编号是否存在
        if (json.containsKey(ConstantUtils.BODY_ACCOUNTNUM) &&
                json.containsKey(ConstantUtils.BODY_TYPE) &&
                json.containsKey(ConstantUtils.BODY_PASSWORD)) {

            // 组装返回
            return ConstantUtils.BODY_CHECK_FLAG03;
        }

        /****end**************************************************************************************************/

        return ConstantUtils.BODY_CHECK_FLAG02;
    }

    /**
     * Created by Bruce.Zhan on 2014/8/6.
     * 把JSONObject数据解析出来组装成Object[]数据
     *
     * @param json {
     *             "SourceSystemId" : 101,
     *             "UserId"         : 1,
     *             "UserName"       : "zhansan",
     *             "Password"       : "b594510740d2ac4261c1b2fe87850d08"
     *             }
     * @return Object[]
     */
    public static int checkUpdatedBody(JSONObject json) {

        if (log.isInfoEnabled()) {
            log.info("正在验证实体信息");
        }

        /****start************************以下验证参数实体信息是否存在，不存在则返回信息通知客户端****************************/

        // 验证源系统编号是否存在
        if (json.containsKey(ConstantUtils.BODY_ACCOUNTNUM) &&
                json.containsKey(ConstantUtils.BODY_PASSWORD) &&
                json.containsKey(ConstantUtils.BODY_RECHARGEAMT) &&
                json.containsKey(ConstantUtils.BODY_FACECARDNUM) &&
                json.containsKey(ConstantUtils.BODY_RECHARGETIME) &&
                json.containsKey(ConstantUtils.BODY_MODEL) &&
                json.containsKey(ConstantUtils.BODY_ORDERNO)) {

            // 组装返回
            return ConstantUtils.BODY_CHECK_FLAG05;
        }

        /****end**************************************************************************************************/

        return ConstantUtils.BODY_CHECK_FLAG04;
    }

}
