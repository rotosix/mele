package com.mele.usercore.index.worker;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mele.common.Constants;
import com.mele.usercore.index.service.IndexInfoService;

public class IndexWorker implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(IndexWorker.class);
	private IndexInfoService indexInfoService;
	private Random queueId = new Random();

	public IndexWorker(IndexInfoService indexInfoService) {
		this.indexInfoService = indexInfoService;
	}

	@Override
	public void run() {

		while (true) {

			try {
				Thread.sleep(1000 * Constants.USER_INDEX_QUEUE_CONSUMER_NUM);

				indexInfoService.updateUserCoreInfo(queueId.nextInt(Constants.USER_INDEX_QUEUE_NUM));
			} catch (Exception e) {
				log.error("runnging exception:"+e);
				e.printStackTrace();
			}
		}
	}

}
