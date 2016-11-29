package com.hgsoft.cxf.server;

/**
 * Created by linlin on 2015/06/25/0025.
 */

import javax.jws.WebService;

import com.hgsoft.cxf.entity.Message;

@WebService
public interface CallMessage {
    public Message callWS(Message msg);
    
    public Message listHandle(Message msg);
    
}
