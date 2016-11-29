package com.hgsoft.parse;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ConsumptionEntry implements Serializable {
	private String returnCode;
	private String returnMsg;
	private Object result;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public ConsumptionEntry(String returnCode, String returnMsg, Object result) {
		super();
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.result = result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result
				+ ((returnCode == null) ? 0 : returnCode.hashCode());
		result = prime * result
				+ ((returnMsg == null) ? 0 : returnMsg.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsumptionEntry other = (ConsumptionEntry) obj;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (returnCode == null) {
			if (other.returnCode != null)
				return false;
		} else if (!returnCode.equals(other.returnCode))
			return false;
		if (returnMsg == null) {
			if (other.returnMsg != null)
				return false;
		} else if (!returnMsg.equals(other.returnMsg))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConsumptionEntry [returnCode=" + returnCode + ", returnMsg="
				+ returnMsg + ", result=" + result + "]";
	}

}
