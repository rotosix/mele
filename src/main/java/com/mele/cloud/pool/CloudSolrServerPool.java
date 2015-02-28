package com.mele.cloud.pool;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.SolrPingResponse;

public class CloudSolrServerPool extends SolrPool<CloudSolrServer> {

	public CloudSolrServerPool(final GenericObjectPool.Config poolConfig,
			CloudSolrZookeeperInfo cloudSolrZookeeperInfo) {
		super(poolConfig, new CloudSolrServerFactory(cloudSolrZookeeperInfo));
	}

	private static class CloudSolrServerFactory extends BasePoolableObjectFactory {
		private CloudSolrZookeeperInfo cloudSolrZookeeperInfo;

		public CloudSolrServerFactory(CloudSolrZookeeperInfo cloudSolrZookeeperInfo) {
			this.cloudSolrZookeeperInfo = cloudSolrZookeeperInfo;
		}

		@Override
		public Object makeObject() throws Exception {
			CloudSolrServer cloudSolrServer = new CloudSolrServer(cloudSolrZookeeperInfo.getZkHosts());

			cloudSolrServer.setDefaultCollection(cloudSolrZookeeperInfo.getZkDefaultCollection());
			cloudSolrServer.setZkClientTimeout(cloudSolrZookeeperInfo.getZkClientExpire());
			cloudSolrServer.setZkConnectTimeout(cloudSolrZookeeperInfo.getZkConnectExpire());
			cloudSolrServer.connect();

			return cloudSolrServer;
		}

        public void destroyObject(final Object obj) throws Exception {
            if ((obj != null) && (obj instanceof CloudSolrServer)) {
            	CloudSolrServer cloudSolrServer = (CloudSolrServer) obj;

            	try {
            		cloudSolrServer.shutdown();
                } catch (Exception e) {

                }
            }
        }

        public boolean validateObject(final Object obj) {
        	try {
        		CloudSolrServer cloudSolrServer = (CloudSolrServer) obj;
        		SolrPingResponse pingResp = cloudSolrServer.ping();
        		if ((pingResp != null) && (pingResp.getStatus() == 0)) {
        			return true;
        		}
                return false;
            } catch (Exception ex) {
                return false;
            }
        }
	}
}
