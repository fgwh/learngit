package com.hgsoft.main.liushuiManage;

import java.util.List;
import java.util.Map;



/**
 * 
 * @author liyuyun
 * date:2015-7-16
 */
public abstract class ManagerData {
	private DataConnection dataConnection;

	public List<Object[]> getAllData(Object... obj) throws Exception {
		return dataConnection.getAllData(obj);
	}

	public int queryLiuShuiDataSize(Object... obj) {
		return dataConnection.queryLiuShuiDataSize(obj);
	}

	public List<Object> queryLiuShuiList(Object... obj) {
		return dataConnection.queryLiuShuiList(obj);
	}

	public Map<String, String> getLiuShuiInfo(Object... obj) throws Exception {
		return dataConnection.getLiuShuiInfo(obj);
	}

	public void setDataConnection(DataConnection dataConnection) {
		this.dataConnection = dataConnection;
	}
}
