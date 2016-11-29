package com.hgsoft.flow.service;

public interface FlowInterface {
	/***
	 * 根据业务的id，对其进行发送下一步，并更新上一步的数据，或者结束审批等
	 * @param id	业务ID
	 * @param nextStepType	发送下一步；退回上一步；结束审批
	 * @param operateType   上报审批请求通过或者上报审批请求删除
	 * @return
	 */
	public void sendNextStep(String id, String nextStepType, String operateType); 
	
	/***
	 * 根据业务的id，获取它的pid
	 * @param id
	 * @return
	 */
	public String findBizPidByBizId(String id);
	
}
