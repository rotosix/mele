package com.mele.usercore.index.redis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedisPipeline;

import com.mele.base.redis.ContextHolder;
import com.mele.common.Constants;

@Repository
public class UserIdInfoRedisDao {

    @SuppressWarnings("unchecked")
	public List<String> getUpdateUserIdInfo(int queueId) { 
    	String key = Constants.USER_INDEX_QUEUE_PRE + queueId;

    	ShardedJedisPipeline pipe = ContextHolder.getShardedJedis().pipelined();
    	pipe.zrange(key, 0, Constants.USER_INDEX_QUEUE_FETCH_NUM);
    	pipe.zremrangeByRank(key, 0, Constants.USER_INDEX_QUEUE_FETCH_NUM);
 
    	List<String> userIds = null;
    	List<Object> retList = pipe.syncAndReturnAll();
    	if ((retList != null) && (retList.size() > 0)) {
    		userIds = new ArrayList<String>();
    		userIds.addAll((Set<String>)retList.get(0));
    	}

        return userIds;
    }
}
