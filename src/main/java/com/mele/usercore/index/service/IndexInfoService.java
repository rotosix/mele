package com.mele.usercore.index.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mele.common.Constants;
import com.mele.rmi.IUserIndexInfoService;
import com.mele.usercore.index.model.UserCoreInfo;
import com.mele.usercore.index.redis.dao.UserIdInfoRedisDao;
import com.mele.usercore.index.worker.IndexWorker;

@Service
public class IndexInfoService implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(IndexInfoService.class);
	private IndexWorker[] indexInfoWorkers;
	@Autowired
	private UserIdInfoRedisDao userIdInfoRedisDao;
	@Autowired
	private IUserIndexInfoService userIndexInfoService;
	@Autowired
	private IndexService indexService;

	public IndexInfoService() {
		// 暂时注释掉工作线程，因为需要对方系统进行配合
		//Thread t = new Thread(this);
		//t.start();
	}

	@Override
	public void run() {

		try {
			Thread.sleep(10000);
			createIndexWorker();
		} catch (Exception e) {
			log.error("run exception:", e);
		}
	}

	public void updateUserCoreInfo(int queueId) {

		long startTime = System.currentTimeMillis();

		List<String> userIds = userIdInfoRedisDao.getUpdateUserIdInfo(queueId);
		if ((userIds == null) || (userIds.size() <= 0)) {
			return ;
		}

		long currentTime1 = System.currentTimeMillis();
		log.debug("UserIdInfoRedisDao:getUpdateUserIdInfo time:" + (currentTime1 - startTime) + "ms");

		List<UserCoreInfo> userCoreInfos = userIndexInfoService.getUserCoreInfo(userIds);
		if ((userCoreInfos == null) || (userCoreInfos.size() <= 0)) {
			return ;
		}

		long currentTime2 = System.currentTimeMillis();
		log.debug("UserIndexInfoService:getUserCoreInfo time:" + (currentTime2 - currentTime1) + "ms");

		indexService.updateUsers(userCoreInfos);

		long currentTime3 = System.currentTimeMillis();
		log.debug("IndexInfoService:updateUserCoreInfo time:" + (currentTime3 - currentTime1) + "ms");

		return ;
	}

	private void createIndexWorker() {

		indexInfoWorkers = new IndexWorker[Constants.USER_INDEX_QUEUE_CONSUMER_NUM];
		for (int i = 0; i < Constants.USER_INDEX_QUEUE_CONSUMER_NUM; i++) {
			IndexWorker worker = new IndexWorker(this);
			indexInfoWorkers[i] = worker;

			Thread workerThread = new Thread(worker);
			workerThread.start();
		}
	}
}
