package com.hgsoft.cxf.server.impl;

/**
 * Created by linlin on 2015/06/25/0025.
 */

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.hgsoft.cxf.entity.Message;
import com.hgsoft.cxf.server.CallMessage;
import com.hgsoft.cxf.service.CallMessageOperateService.AdjustOSTime;
import com.hgsoft.cxf.service.CallMessageOperateService.DealBlackList;
import com.hgsoft.cxf.service.CallMessageOperateService.DealGoodsInspection;
import com.hgsoft.cxf.service.CallMessageOperateService.DealMessage;
import com.hgsoft.cxf.service.CallMessageOperateService.DealOrga;
import com.hgsoft.cxf.service.CallMessageOperateService.DealSquad;
import com.hgsoft.cxf.service.CallMessageOperateService.DealUser;
import com.hgsoft.cxf.service.CallMessageOperateService.GetLaneExListData;
import com.hgsoft.cxf.service.CallMessageOperateService.SaveBlackList;
import com.hgsoft.cxf.service.CallMessageOperateService.UpdateOSTime;
import com.hgsoft.security.service.BaseService;

@WebService(endpointInterface= "com.hgsoft.cxf.server.CallMessage",serviceName="hgsoft")
@Service
@SuppressWarnings("unchecked")
public class CallMessageImpl extends BaseService implements CallMessage {
	
	/** 操作key:operate */
	public static final String OPERATE_KEY = "operate";
	/** 数据key:data */
	public static final String OPERATE_DATA_KEY = "data";
	/** 操作结果key:result */
	public static final String OPERATE_RESULT_KEY = "result";
	
	public final static Map<String,DealMessage> operateTypeMap = new HashMap<String,DealMessage>(){{//操作类型
		put("getblack",new DealBlackList());//获取黑名单信息
		put("saveblack",new SaveBlackList());//保存黑名单信息
		put("getLaneExList",new GetLaneExListData());//获取指定日期流水
		put("goodsInspection", new DealGoodsInspection());//更新货物检测信息表
		put("tb_squad", new DealSquad());//更新工班信息
		put("tb_orga", new DealOrga());//更新机构信息
		put("user", new DealUser());//更新用户信息
		put("updateOSTime",new UpdateOSTime());//同步系统时间
		put("adjustOSTime",new AdjustOSTime());//校准系统时间
	}};
	
    @WebMethod
    public Message callWS(Message msg){
        
    	Message message = new Message();
    	message.getSendMap().put(OPERATE_RESULT_KEY, "fail");//失败时返回
    	
        DealMessage dealMessage = (DealMessage)operateTypeMap.get(msg.getSendMap().get(OPERATE_KEY)); 
        dealMessage.setMessage(msg);
        if(dealMessage.parseMessage()) {
        	message = dealMessage.settlementOperate();
        }
        
        return message;
        
    };
    
    @WebMethod
	public Message listHandle(Message msg) {
    	Message message = new Message();
    	
        DealMessage dealMessage = (DealMessage)operateTypeMap.get(msg.getSendList().get(0)); 
        dealMessage.setMessage(msg);
        if(dealMessage.parseMessage()) {
        	message = dealMessage.settlementOperate();
        }
        
        return message;
	}
}