package com.mele.cloud.pool;

public class CloudSolrZookeeperInfo {

	private String zkHosts;
	private String zkDefaultCollection;
	private Integer zkClientExpire = 60000;
	private Integer zkConnectExpire = 10000;

	public CloudSolrZookeeperInfo(String zkHosts, String zkDefaultCollection) {
		this.zkHosts = zkHosts;
		this.zkDefaultCollection = zkDefaultCollection;
	}

	public CloudSolrZookeeperInfo(String zkHosts, String zkDefaultCollection,
			Integer zkClientExpire, Integer zkConnectExpire) {
		this.zkHosts = zkHosts;
		this.zkDefaultCollection = zkDefaultCollection;
		this.zkClientExpire = zkClientExpire;
		this.zkConnectExpire = zkConnectExpire;
	}

	public String getZkHosts() {
		return zkHosts;
	}

	public void setZkHosts(String zkHosts) {
		this.zkHosts = zkHosts;
	}

	public String getZkDefaultCollection() {
		return zkDefaultCollection;
	}

	public void setZkDefaultCollection(String zkDefaultCollection) {
		this.zkDefaultCollection = zkDefaultCollection;
	}

	public Integer getZkClientExpire() {
		return zkClientExpire;
	}

	public void setZkClientExpire(Integer zkClientExpire) {
		this.zkClientExpire = zkClientExpire;
	}

	public Integer getZkConnectExpire() {
		return zkConnectExpire;
	}

	public void setZkConnectExpire(Integer zkConnectExpire) {
		this.zkConnectExpire = zkConnectExpire;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{");
		sb.append("\"zkHosts\":\"").append(zkHosts).append("\",");
		sb.append("\"zkDefaultCollection\":\"").append(zkDefaultCollection).append("\",");
		sb.append("\"zkClientExpire\":").append(zkClientExpire).append(",");
		sb.append("\"zkConnectExpire\":\"").append(zkConnectExpire);
		sb.append("}");

		return sb.toString();
	}
}
