package com.mele.cloud;

import org.apache.solr.client.solrj.impl.CloudSolrServer;

public class CloudSolrServerManager {

	private static final ThreadLocal<CloudSolrServer> cloudSolrServerHolder = new ThreadLocal<CloudSolrServer>();

	public static void setCloudSolrServer(CloudSolrServer key) {
		cloudSolrServerHolder.set(key);
	}

	public static CloudSolrServer getCloudSolrServer() {
		return cloudSolrServerHolder.get();
	}

	public static void clearCloudSolrServer() {
		cloudSolrServerHolder.remove();
	}
}
