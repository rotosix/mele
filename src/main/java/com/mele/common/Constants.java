package com.mele.common;

import com.mele.utils.PropertyManager;

public interface Constants {

	public static final Integer SOLR_CLOUD_RESULT_NUM_DEFAULT = PropertyManager.getInt("solrcloud.result.num.default");
    public static final Integer SOLR_CLOUD_RESULT_NUM_MAX = PropertyManager.getInt("solrcloud.result.num.max");
    public static final Integer SOLR_CLOUD_RETRY_NUM_MAX = PropertyManager.getInt("solrcloud.retry.num.max");

	public static final Integer SOLR_CLOUD_AREA_NUM_DEFAULT = PropertyManager.getInt("solrcloud.area.num.default");
    public static final Integer SOLR_CLOUD_AREA_NUM_MAX = PropertyManager.getInt("solrcloud.area.num.max");
    public static final String SOLR_CLOUD_AREA_HOT = PropertyManager.getString("solrcloud.area.hot");

    public static final Integer USER_INDEX_QUEUE_NUM = PropertyManager.getInt("user.index.queue.num");
    public static final Integer USER_INDEX_QUEUE_CONSUMER_NUM = PropertyManager.getInt("user.index.queue.consumer.num");
    public static final String USER_INDEX_QUEUE_PRE = PropertyManager.getString("user.index.queue.pre");
    public static final Integer USER_INDEX_QUEUE_FETCH_NUM = PropertyManager.getInt("user.index.queue.fetch.num");

    public static final Integer USER_CORE_LEVEL_MIN = PropertyManager.getInt("user.core.level.min");
    public static final Integer USER_CORE_LEVEL_MAX = PropertyManager.getInt("user.core.level.max");

    public static final Double USER_SPATIAL_DISTANCE_DEFAULT = PropertyManager.getDouble("user.spatial.distance.default");

	public static final int MEMCACHED_EXPIRED_HOUR = 3600;
	public static final String SPECIAL_KEY = "\\p{Cntrl}|\\p{Space}|,";

}
