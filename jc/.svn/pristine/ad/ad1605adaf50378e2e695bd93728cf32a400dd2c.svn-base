package com.hgsoft.cxf.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by linlin on 2015/07/02/0002.
 */
@XmlRootElement(name = "Message")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "sendList", "sendMap" })
public class Message {
    public List sendList = new ArrayList();
    public HashMap sendMap = new HashMap();
    
    public List getSendList() {
        return sendList;
    }
    public void setSendList(List sendList) {
        this.sendList = sendList;
    }
    public HashMap getSendMap() {
        return sendMap;
    }
    public void setSendMap(HashMap sendMap) {
        this.sendMap = sendMap;
    }

}
