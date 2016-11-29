package com.hgsoft.cxf.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by Bruce.Zhan on 2014/8/4.
 *
 * @description
 *  接口：
 *     1、创建虚拟账户所需要的数据信息
 *     2、更新虚拟账户余额所需要的数据信息
 *     3、圈存写卡确认结果数据信息
 */

@WebService(name = "IVirtualAccount4CxfServer", targetNamespace = "http://server.cxf.hgsoft.com")
public interface IVirtualAccount4CxfServer {

    /**
     * @param accountInfo 字符串的JSON格式数据例：
     *                    {
     *                      "IMsgHeader" : {
     *                          "SourceSystemId" : 101,
     *                          "UserId"         : 1,
     *                          "UserName"       : "zhansan",
     *                          "Password"       : "b594510740d2ac4261c1b2fe87850d08"
     *                      },
     *                      "IMsgBody"  : {
     *                          "AccountNum"     : "hgsoft@gmail.com",
     *                          "Password"       : "b8cb30cb6c2547c20405778c1ab9d0d5",
     *                          "Type"           : 1
     *                          ......
     *                      }
     *                    }
     *                    创建虚拟账户所需要的数据信息
     * @return String 字符串的JSON格式数据例：
     *                    {
     *                      "OMsgHeader" : {
     *                          "ErrorFlag"    : "Y",
     *                          "ErrorMessage" : ""
     *                      },
     *                      "OMsgBody"  : {
     *                          "AccountNum"   : "hgsoft@gmail.com",
     *                          "IsFlag"       : 1,
     *                          ......
     *                      }
     *                    }
     *                返回创建虚拟账户成功或者失败所以及异常信息
     * @description 创建虚拟账户信息
     * @author Bruce.Zhan
     */
    @WebMethod
    String createdVirtualAccount(@WebParam(name = "accountInfo") final String accountInfo);

    /**
     * @param rechargeInfo 字符串的JSON格式数据例：
     *                     {
     *                       "IMsgHeader" : {
     *                           "SourceSystemId" : 101,
     *                           "UserId"         : 1,
     *                           "UserName"       : "zhansan",
     *                           "Password"       : "b594510740d2ac4261c1b2fe87850d08"
     *                       },
     *                       "IMsgBody"  : {
     *                           "AccountNum"     : "hgsoft@gmail.com",
     *                           "Password"       : "b8cb30cb6c2547c20405778c1ab9d0d5",
     *                           "RechargeAmt"    :    //元
     *                           "FaceCardNum"    : "1023498538274950"
     *                           "PhyCardNum"     :
     *                           "RechargeTime"   : "yyyyMMddHHmmss"
     *                           "Flag"           : 1            //1:充值  2:退款
     *                           ......
     *                       }
     *                    }
     *                    更新虚拟账户余额所需要的数据信息
     * @return String 字符串的JSON格式数据例：
     *                   {
     *                      "OMsgHeader" : {
     *                          "ErrorFlag"    : "Y",
     *                          "ErrorMessage" : ""
     *                      },
     *                      "OMsgBody"  : {
     *                          "AccountNum"   : "hgsoft@gmail.com",
     *                          "IsFlag"       : 1,
     *                          ......
     *                      }
     *                    }
     *                返回更新虚拟账户余额成功或者失败以及异常信息
     * @description 更新虚拟账户余额信息
     * @author Bruce.Zhan
     */
    @WebMethod
    String updatedVirtualAccount(@WebParam(name = "rechargeInfo") final String rechargeInfo);

    /**
     * @param rechargeInfo 字符串的JSON格式数据例：
     *                     {
     *                        "IMsgHeader" : {
     *                            "SourceSystemId" : 101,
     *                            "UserId"         : 1,
     *                            "UserName"       : "zhansan",
     *                            "Password"       : "b594510740d2ac4261c1b2fe87850d08"
     *                        },
     *                        "IMsgBody"  : {
     *                            "AccountNum"     : "hgsoft@gmail.com",
     *                            "Password"       : "b8cb30cb6c2547c20405778c1ab9d0d5",
     *                            "FaceCardNum"    : "1023498538274950"
     *                            ......
     *                        }
     *                     }
     *                     圈存写卡确认结果数据信息
     * @return String 字符串的JSON格式数据例：
     *                    {
     *                      "OMsgHeader" : {
     *                          "ErrorFlag"    : "Y",
     *                          "ErrorMessage" : ""
     *                      },
     *                      "OMsgBody"  : {
     *                          "AccountNum"   : "hgsoft@gmail.com",
     *                          "IsFlag"       : 1,
     *                          ......
     *                      }
     *                    }
     *                    返回圈存写卡确认结果成功或者失败以及异常信息
     * @description 圈存写卡确认结果
     * @author Bruce.Zhan
     */
    @WebMethod
    String writeCardResult(@WebParam(name = "rechargeInfo") final String rechargeInfo);
}
