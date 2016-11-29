package com.hgsoft.flow.constant;

public class FlowConstant {
	/**审批状态-未上报*/
	public static final int FLOW_STATUS_NOREPORT=0;
	/**审批状态-已审批结束（通过）*/
	public static final int FLOW_STATUS_REPORTED=999;
	/**审批状态-已审批结束（删除）*/
	public static final int FLOW_STATUS_DELETEED=-999;
	
	/**上报动作-请求审批通过*/
	public static final String OPERATE_TYPE_REPORT_PASS="reportForPass";
	/**上报动作-请求审批删除此条记录*/
	public static final String OPERATE_TYPE_REPORT_DELETE="reportForDelete";
	
	/**发送下一步*/
	public static final String FLOW_STEP_NEXT="next";
	/**退回上一步*/
	public static final String FLOW_STEP_UP="up";
	/**结束流程审批*/
	public static final String FLOW_STEP_FINISH="finish";
	
}
