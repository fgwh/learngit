package com.hgsoft.test;

import com.hgsoft.cxf.entity.Message;
import com.hgsoft.cxf.server.CallMessage;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by linlin on 2015/07/02/0002.
 */
public class testWS {
    public static void main(String[] args) throws JAXBException {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        Message message = new Message();//消息体
        factory.setAddress("http://192.168.1.36:9090/ws/hgsoft");//IP从数据库取
        //添加接口类
        factory.setServiceClass(CallMessage.class);
        CallMessage service = (CallMessage) factory.create();
        /**随便做了一些消息*****/
        HashMap szmap = new HashMap();
        szmap.put("1","2");
        List a = new ArrayList();
        a.add(1);
        a.add("2");
        /*****/
        message.setSendList(a);
        message.setSendMap(szmap);
        System.out.print(service.callWS(message));

    }
}
