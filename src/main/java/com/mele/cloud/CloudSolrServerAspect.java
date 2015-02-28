package com.mele.cloud;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mele.cloud.pool.CloudSolrServerPool;

@Aspect
@Component
public class CloudSolrServerAspect {

	@Autowired
	@Qualifier("cloudSolrServerPool")
	private CloudSolrServerPool pool;

	@Pointcut("execution(* com.mele.usercore.solr.dao.SolrDao.*(..))")   
	private void cutMethod(){} 

	@Before("cutMethod()")
	public void configCloudSolrServer(JoinPoint jp) throws Throwable {
		CloudSolrServerManager.setCloudSolrServer(pool.getResource());
	}

	@After("cutMethod()")
	public void releaseCloudSolrServer() throws Throwable {
		pool.returnResource(CloudSolrServerManager.getCloudSolrServer());
		CloudSolrServerManager.clearCloudSolrServer();
	}
}
