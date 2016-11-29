package com.hgsoft.security.util;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;


/**
 * Created by linlin on 2015/06/26/0026.
 */

public class PermissionsInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    private SAAJInInterceptor saaIn = new SAAJInInterceptor();

    private String namespaceURI = "http://test.com/auth";

    private String localPart = "MyAuthHeader";

    public PermissionsInterceptor() {
//在哪个阶段被拦截
        super(Phase.PRE_PROTOCOL);
        getAfter().add(SAAJInInterceptor.class.getName());
    }

    public void handleMessage(SoapMessage message) throws Fault {

    }
}
