package com.hgsoft.cxf.common;

/**
 * Created by Bruce.Zhan on 2014/8/6.
 * 常量定义
 */
public final class ConstantUtils {

    /**
     * 头信息
     */
    public final static String HEAD_IMSGHEADER = "IMsgHeader";
    /**
     * 实体信息
     */
    public final static String BODY_IMSGBODY = "IMsgBody";
    /**
     * 头信息源系统编号
     */
    public final static String HEAD_SOURCESYSTEMID = "SourceSystemId";
    /**
     * 头信息用户ID
     */
    public final static String HEAD_USERID = "UserId";
    /**
     * 头信息用户名
     */
    public final static String HEAD_USERNAME = "UserName";
    /**
     * 头信息密码
     */
    public final static String HEAD_PASSWORD = "Password";
    /**
     * 参数[参数不存在]，标识
     */
    public final static int PARAMETER_CHECK_FLAG00 = 0;
    /**
     * 参数[参数不存在]，消息
     */
    public final static String PARAMETER_CHECK_FLAG00_MESSAGE = "参数[参数不存在]";
    /**
     * 参数[参数格式错误]，标识
     */
    public final static int PARAMETER_CHECK_FORMAT_ERROR_FLAG = 0;
    /**
     * 参数[参数格式错误]，消息
     */
    public final static String PARAMETER_CHECK_FORMAT_ERROR_FLAG_MESSAGE = "参数[参数格式错误]";
    /**
     * 头信息[头信息不存在]，标识
     */
    public final static int HEAD_CHECK_FLAG01 = 1;
    /**
     * 头信息[头信息不存在]，消息
     */
    public final static String HEAD_CHECK_FLAG01_MESSAGE = "头信息[头信息不存在]";
    /**
     * 头信息[源系统编号,用户ID,用户名,密码不存在]，标识
     */
    public final static int HEAD_CHECK_FLAG02 = 2;
    /**
     * 头信息[源系统编号,用户ID,用户名,密码不存在]，消息
     */
    public final static String HEAD_CHECK_FLAG02_MESSAGE = "头信息[源系统编号、用户ID、用户名或者密码不存在]";
    /**
     * 头信息[接口调用验证成功]，标识
     */
    public final static int HEAD_CHECK_FLAG03 = 3;
    /**
     * 头信息[接口调用验证成功]，消息
     */
    public final static String HEAD_CHECK_FLAG03_MESSAGE = "头信息[接口调用验证成功]";
    /**
     * 头信息[接口调用验证失败：源系统编号、用户ID、用户名或者密码不对]，标识
     */
    public final static int HEAD_CHECK_FLAG04 = 4;
    /**
     * 头信息[接口调用验证失败：源系统编号、用户ID、用户名或者密码不对]，消息
     */
    public final static String HEAD_CHECK_FLAG04_MESSAGE = "头信息[接口调用验证失败：源系统编号、用户ID、用户名或者密码不对]";
    /**
     * 头信息[其它验证失败]，标识
     */
    public final static int HEAD_CHECK_FLAG05 = 5;
    /**
     * 头信息[其它验证失败]，消息
     */
    public final static String HEAD_CHECK_FLAG05_MESSAGE = "头信息[其它验证失败]";
    /**
     * 虚拟账户
     */
    public final static String BODY_ACCOUNTNUM = "AccountNum";
    /**
     * 虚拟账户类型
     */
    public final static String BODY_TYPE = "Type";
    /**
     * 虚拟账户密码
     */
    public final static String BODY_PASSWORD = "Password";
    /**
     * 虚拟账户充值金额
     */
    public final static String BODY_RECHARGEAMT = "RechargeAmt";
    /**
     * 卡片表面编号
     */
    public final static String BODY_FACECARDNUM = "FaceCardNum";
    /**
     * 充值订单号
     */
    public final static String BODY_ORDERNO = "OrderNo";
    /**
     * 充值时间
     */
    public final static String BODY_RECHARGETIME = "RechargeTime";
    /**
     * 更新虚拟账户类型
     */
    public final static String BODY_MODEL = "Model";
    /**
     * 更新标识，1、充值 2、退款
     */
    public final static String BODY_FLAG = "Flag";
    /**
     * 实体信息[实体信息不存在]，标识
     */
    public final static int BODY_CHECK_FLAG01 = 1;
    /**
     * 实体信息[实体信息不存在]，消息
     */
    public final static String BODY_CHECK_FLAG01_MESSAGE = "实体信息[实体信息不存在]";
    /**
     * 实体信息[虚拟账户、类型或者密码不存在]，标识
     */
    public final static int BODY_CHECK_FLAG02 = 2;
    /**
     * 实体信息[虚拟账户、类型或者密码不存在]，消息
     */
    public final static String BODY_CHECK_FLAG02_MESSAGE = "实体信息[虚拟账户、类型或者密码不存在]";
    /**
     * 实体信息[实体信息存在]，标识
     */
    public final static int BODY_CHECK_FLAG03 = 3;
    /**
     * 实体信息[实体信息存在]，消息
     */
    public final static String BODY_CHECK_FLAG03_MESSAGE = "实体信息[实体信息存在]";
    /**
     * 实体信息[虚拟账户、密码、充值金额、卡片表面编号、充值时间或者更新标识不存在]，标识
     */
    public final static int BODY_CHECK_FLAG04 = 4;
    /**
     * 实体信息[虚拟账户、密码、金额、卡片表面编号、时间、更新类型或者订单号不存在]，消息
     */
    public final static String BODY_CHECK_FLAG04_MESSAGE = "实体信息[虚拟账户、密码、金额、卡片表面编号、时间、更新类型或者订单号不存在]";
    /**
     * 实体信息[实体信息存在]，标识
     */
    public final static int BODY_CHECK_FLAG05 = 5;
    /**
     * 实体信息[实体信息存在]，消息
     */
    public final static String BODY_CHECK_FLAG05_MESSAGE = "实体信息[实体信息存在]";
    /**
     * 创建虚拟账户信息[创建成功]，标识
     */
    public final static int CREATED_CHECK_FLAG01 = 1;
    /**
     * 创建虚拟账户信息[创建成功]，消息
     */
    public final static String CREATED_CHECK_FLAG01_MESSAGE = "创建虚拟账户信息[创建成功]";
    /**
     * 创建虚拟账户信息[虚拟账户已经存在，创建失败]，标识
     */
    public final static int CREATED_CHECK_FLAG02 = 2;
    /**
     * 创建虚拟账户信息[虚拟账户已经存在，创建失败]，消息
     */
    public final static String CREATED_CHECK_FLAG02_MESSAGE = "创建虚拟账户信息[虚拟账户已经存在，创建失败]";
    /**
     * 创建虚拟账户信息[其它，创建失败]，标识
     */
    public final static int CREATED_CHECK_FLAG03 = 3;
    /**
     * 创建虚拟账户信息[其它，创建失败]，消息
     */
    public final static String CREATED_CHECK_FLAG03_MESSAGE = "创建虚拟账户信息[其它，创建失败]";

    /**
     * 更新虚拟账户信息[充值时间转换出错，请检查时间格式]，消息
     */
    public final static String UPDATED_CHECK_FORMAT_MESSAGE = "更新虚拟账户信息[充值时间转换出错，请检查时间格式]";
    /**
     * 充值信息[充值时间转换出错，请检查时间格式]，消息
     */
    public final static String UPDATED_RECHARGE_FORMAT_MESSAGE = "充值信息[充值时间转换出错，请检查时间格式]";
    /**
     * 退款信息[退款时间转换出错，请检查时间格式]，消息
     */
    public final static String UPDATED_REFUND_FORMAT_MESSAGE = "退款信息[退款时间转换出错，请检查时间格式]";
    /**
     * 圈存信息[圈存时间转换出错，请检查时间格式]，消息
     */
    public final static String UPDATED_TRANSFER_FORMAT_MESSAGE = "圈存信息[圈存时间转换出错，请检查时间格式]";
    /**
     * 充值信息[充值成功]，消息
     */
    public final static String UPDATED_RECHARGE_FLAG01_MESSAGE = "充值信息[充值成功]";
    /**
     * 退款信息[退款成功]，消息
     */
    public final static String UPDATED_REFUND_FLAG01_MESSAGE = "退款信息[退款成功]";
    /**
     * 圈存信息[圈存成功]，消息
     */
    public final static String UPDATED_TRANSFER_FLAG01_MESSAGE = "圈存信息[圈存成功]";
    /**
     * 充值信息[虚拟账户不存在或者源系统编号、虚拟账户、密码错误，更新失败]，消息
     */
    public final static String UPDATED_RECHARGE_FLAG02_MESSAGE = "充值信息[虚拟账户不存在或者源系统编号、虚拟账户、密码错误，充值失败]";
    /**
     * 充值信息[虚拟账户已注销或者冻结，充值失败]，消息
     */
    public final static String UPDATED_RECHARGE_FLAG04_MESSAGE = "充值信息[虚拟账户已注销或者冻结，充值失败]";
    /**
     * 退款信息[虚拟账户已注销或者冻结，退款失败]，消息
     */
    public final static String UPDATED_REFUND_FLAG08_MESSAGE = "退款信息[虚拟账户已注销或者冻结，退款失败]";
    /**
     * 圈存信息[虚拟账户已注销或者冻结，圈存失败]，消息
     */
    public final static String UPDATED_TRANSFER_FLAG07_MESSAGE = "圈存信息[虚拟账户已注销或者冻结，圈存失败]";
    /**
     * 退款信息[虚拟账户不存在或者源系统编号、虚拟账户、密码错误，退款失败]，消息
     */
    public final static String UPDATED_REFUND_FLAG02_MESSAGE = "退款信息[虚拟账户不存在或者源系统编号、虚拟账户、密码错误，退款失败]";
    /**
     * 圈存信息[订单信息错误或者订单信息不存在，圈存失败]，消息
     */
    public final static String UPDATED_TRANSFER_FLAG03_MESSAGE = "圈存信息[订单信息错误或者订单信息不存在，圈存失败]";
    /**
     * 退款信息[订单信息错误或者订单信息不存在，退款失败]，消息
     */
    public final static String UPDATED_REFUND_FLAG03_MESSAGE = "退款信息[订单信息错误或者订单信息不存在，退款失败]";
    /**
     * 退款信息[此订单号已经退款，退款失败]，消息
     */
    public final static String UPDATED_REFUND_FLAG04_MESSAGE = "退款信息[此订单号已经退款，退款失败]";
    /**
     * 圈存信息[此订单号已经圈存，圈存失败]，消息
     */
    public final static String UPDATED_TRANSFER_FLAG04_MESSAGE = "圈存信息[此订单号已经圈存，圈存失败]";
    /**
     * 退款信息[您退款的订单金额与虚拟账户中的订单金额不一致，退款失败]，消息
     */
    public final static String UPDATED_REFUND_FLAG05_MESSAGE = "退款信息[您退款的订单金额与虚拟账户中的订单金额不一致，退款失败]";
    /**
     * 圈存信息[您圈存的订单金额与虚拟账户中的订单金额不一致，圈存失败]，消息
     */
    public final static String UPDATED_TRANSFER_FLAG05_MESSAGE = "圈存信息[您圈存的订单金额与虚拟账户中的订单金额不一致，圈存失败]";
    /**
     * 退款信息[虚拟账户余额不足，退款失败]，消息
     */
    public final static String UPDATED_REFUND_FLAG06_MESSAGE = "退款信息[虚拟账户余额不足，退款失败]";
    /**
     * 圈存信息[虚拟账户不存在或者源系统编号、虚拟账户、密码错误，圈存失败]，消息
     */
    public final static String UPDATED_TRANSFER_FLAG02_MESSAGE = "圈存信息[虚拟账户不存在或者源系统编号、虚拟账户、密码错误，圈存失败]";
    /**
     * 充值信息[充值失败]，消息
     */
    public final static String UPDATED_RECHARGE_FLAG03_MESSAGE = "充值信息[充值失败]";
    /**
     * 退款信息[退款失败]，消息
     */
    public final static String UPDATED_REFUND_FLAG07_MESSAGE = "退款信息[退款失败]";
    /**
     * 圈存信息[圈存失败]，消息
     */
    public final static String UPDATED_TRANSFER_FLAG06_MESSAGE = "圈存信息[圈存失败]";
    /*************************************************************************************************************************/

    /**
     * 返回成功
     */
    public final static String ERRORFLAG_Y = "Y";
    /**
     * 返回失败
     */
    public final static String ERRORFLAG_N = "N";

    /**
     * 0、验证前
     */
    public final static int STATUS_00 = 0;
    /**
     * 1、头信息验证成功
     */
    public final static int STATUS_01 = 1;
    /**
     * 2、头信息验证失败
     */
    public final static int STATUS_02 = 2;
    /**
     * 3、实体信息验证成功
     */
    public final static int STATUS_03 = 3;
    /**
     * 4、实体信息验证失败
     */
    public final static int STATUS_04 = 4;
    /**
     * 5、创建成功
     */
    public final static int STATUS_05 = 5;
    /**
     * 6、创建失败
     */
    public final static int STATUS_06 = 6;
    /**
     * 7、充值成功
     */
    public final static int STATUS_07 = 7;
    /**
     * 8、充值失败
     */
    public final static int STATUS_08 = 8;
    /**
     * 9、退款成功
     */
    public final static int STATUS_09 = 9;
    /**
     * 10、退款失败
     */
    public final static int STATUS_10 = 10;
    /**
     * 11、圈存成功
     */
    public final static int STATUS_11 = 11;
    /**
     * 12、圈存失败
     */
    public final static int STATUS_12 = 12;
    /**
     * 13、其它
     */
    public final static int STATUS_13 = 13;

}
