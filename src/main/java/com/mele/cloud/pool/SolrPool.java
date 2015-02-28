package com.mele.cloud.pool;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.solr.client.solrj.SolrServerException;

public abstract class SolrPool<T> {
    private final GenericObjectPool internalPool;

    public SolrPool(final GenericObjectPool.Config poolConfig,
            PoolableObjectFactory factory) {
        this.internalPool = new GenericObjectPool(factory, poolConfig);
    }

    @SuppressWarnings("unchecked")
	public T getResource() throws SolrServerException {
        try {
            return (T) internalPool.borrowObject();
        } catch (Exception e) {
        	throw new SolrServerException(
                    "Could not get a resource from the pool", e);
        }
    }

    public void returnResourceObject(final Object resource) throws SolrServerException {
        try {
            internalPool.returnObject(resource);
        } catch (Exception e) {
            throw new SolrServerException(
                    "Could not return the resource to the pool", e);
        }
    }

    public void returnResource(final T resource) throws SolrServerException {
    	returnResourceObject(resource);
    }

    public void destroy() throws SolrServerException {
        try {
            internalPool.close();
        } catch (Exception e) {
            throw new SolrServerException("Could not destroy the pool", e);
        }
    }  
}
