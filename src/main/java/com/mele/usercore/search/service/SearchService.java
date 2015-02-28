package com.mele.usercore.search.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mele.common.Constants;
import com.mele.rmi.ISearchService;
import com.mele.usercore.index.model.UserCoreInfo;
import com.mele.usercore.search.model.UserCoreInfoQuery;
import com.mele.usercore.solr.dao.SolrDao;

@Service("SearchService")
public class SearchService implements ISearchService {

	private Logger log = LoggerFactory.getLogger(SearchService.class);
	@Autowired
	private SolrDao solrDao;

	@Override
	public List<UserCoreInfo> getUserInfos(UserCoreInfoQuery userCoreInfoQuery) {
		List<UserCoreInfo> userCoreInfos = null;

        try {
			long startTime = System.currentTimeMillis();

        	SolrQuery solrQuery = getSolrQuery(userCoreInfoQuery);
        	log.debug("getUserInfos solrQuery:"+solrQuery);

        	long currentTime1 = System.currentTimeMillis();
			log.debug("SearchService:getSolrQuery time:" + (currentTime1 - startTime) + "ms");

			int retryCnt = 0;
			while (true) {
				retryCnt++;

				try {
					userCoreInfos = solrDao.getUserInfos(solrQuery);
					break;
				} catch (Exception e) {

					if (retryCnt > Constants.SOLR_CLOUD_RETRY_NUM_MAX) {
						throw e;
					} else {
						Thread.sleep(100);
					}
				}
			}

			long currentTime2 = System.currentTimeMillis();
			log.debug("SolrDao:getUserInfos time:" + (currentTime2 - currentTime1) + "ms");

			log.debug("SearchService:getUserInfos time:" + (currentTime2 - startTime) + "ms");
        } catch (Exception e) {
        	log.error("userCoreInfoQuery:"+userCoreInfoQuery+" exception:", e);
        }

        if (userCoreInfos == null) {
        	userCoreInfos = new ArrayList<UserCoreInfo>(0);
        }

        return userCoreInfos;
	}

	@Override
	public List<Object> getSuggestArea(String expectArea) {
		List<Object> areaSuggestions = null;

		try {
	        SolrQuery solrQuery = new SolrQuery();

	        solrQuery.setQuery("multilocation:"+getFuzzyMatching(expectArea));
	        solrQuery.setStart(0);
	        solrQuery.setRows(Constants.SOLR_CLOUD_AREA_NUM_DEFAULT);
	        solrQuery.add("fl","location");

	        areaSuggestions = solrDao.getSuggestArea(expectArea, solrQuery);
		} catch (Exception e) {
        	log.error("getSuggestArea:"+expectArea+" exception:", e);
		}

        if (areaSuggestions == null) {
        	areaSuggestions = new ArrayList<Object>(0);
        }

		return areaSuggestions;
	}

	public void optimizeIndex() {
		solrDao.optimizeIndex();
	}

	private SolrQuery getSolrQuery(UserCoreInfoQuery userCoreInfoQuery) {

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.add("q.op", "AND");

        Integer pageNo = userCoreInfoQuery.getPageNo();
        if ((pageNo == null) ||(pageNo <= 0)) {
        	pageNo = 1;
        }

        Integer pageSize = userCoreInfoQuery.getPageSize();
        if ((pageSize == null) || (pageSize <= 0)) {
        	pageSize = Constants.SOLR_CLOUD_RESULT_NUM_DEFAULT;
        } else if (pageSize > Constants.SOLR_CLOUD_RESULT_NUM_MAX) {
        	pageSize = Constants.SOLR_CLOUD_RESULT_NUM_MAX;
        }

        Integer start = (pageNo - 1) * pageSize;
        String fl = "*";

    	StringBuilder q = new StringBuilder();

    	String uid = userCoreInfoQuery.getUid();
        if (!StringUtils.isEmpty(uid)) {
        	q.append("uid:").append(getFuzzyMatching(uid)).append(" ");
        }

    	String username = userCoreInfoQuery.getUsername();
        if (!StringUtils.isEmpty(username)) {
        	q.append("username:").append(getFuzzyMatching(username)).append(" ");
        }

    	Integer type = userCoreInfoQuery.getType();
        if (type != null) {
        	q.append("type:").append(type).append(" ");
        }

    	Integer gender = userCoreInfoQuery.getGender();
        if (gender != null) {
        	q.append("gender:").append(gender).append(" ");
        }

    	String[] labels = userCoreInfoQuery.getLabel();        
        if ((labels != null) && (labels.length > 0)) {

        	q.append("label:(").append(labels[0]);
    		for (int i = 1; i < labels.length; i++) {
    			q.append(" AND ").append(labels[i]);
    		}
    		q.append(") ");
        }

    	String location = userCoreInfoQuery.getLocation();        
        if (!StringUtils.isEmpty(location)) {
        	q.append("location:").append(location).append(" ");
        }

    	String[] multilocation = userCoreInfoQuery.getMultilocation();        
        if ((multilocation != null) && (multilocation.length > 0)) {

        	q.append("multilocation:(").append(getFuzzyMatching(multilocation[0]));
    		for (int i = 1; i < multilocation.length; i++) {
    			q.append(" AND ").append(getFuzzyMatching(multilocation[i]));
    		}
    		q.append(") ");
        }

    	StringBuilder fq = new StringBuilder();        

    	Integer startLevel = userCoreInfoQuery.getStartLevel();
    	Integer endLevel = userCoreInfoQuery.getEndLevel();
        if ((startLevel != null) || (endLevel != null)) {
        	fq.append("level:[");

        	if (startLevel == null) {
        		fq.append("*").append(" TO ");
        	} else {
        		fq.append(startLevel).append(" TO ");
        	}

        	if (endLevel == null) {
        		fq.append("*");
        	} else {
        		fq.append(endLevel);
        	}

        	fq.append("] ");
        }

        //age:[20 TO 30]"
    	Integer startBirthday = userCoreInfoQuery.getStartBirthday();
    	Integer endBirthday = userCoreInfoQuery.getEndBirthday();
        if ((startBirthday != null) || (endBirthday != null)) {
        	fq.append("birthday:[");

        	if (startBirthday == null) {
        		fq.append("*").append(" TO ");
        	} else {
        		fq.append(startBirthday).append(" TO ");
        	}

        	if (endBirthday == null) {
        		fq.append("*");
        	} else {
        		fq.append(endBirthday);
        	}

        	fq.append("] ");
        }

        //registertime:[20 TO 30]"
    	Integer startRegisterTime = userCoreInfoQuery.getStartRegisterTime();
    	Integer endRegisterTime = userCoreInfoQuery.getEndRegisterTime();
        if ((startRegisterTime != null) || (endRegisterTime != null)) {
        	fq.append("registertime:[");

        	if (startRegisterTime == null) {
        		fq.append("*").append(" TO ");
        	} else {
        		fq.append(startRegisterTime).append(" TO ");
        	}

        	if (endRegisterTime == null) {
        		fq.append("*");
        	} else {
        		fq.append(endRegisterTime);
        	}

        	fq.append("] ");
        }

    	Integer startLastTime = userCoreInfoQuery.getStartLastTime();
    	Integer endLastTime = userCoreInfoQuery.getEndLastTime();
        if ((startLastTime != null) || (endLastTime != null)) {
        	fq.append("logintime:[");

        	if (startLastTime == null) {
        		fq.append("*").append(" TO ");
        	} else {
        		fq.append(startLastTime).append(" TO ");
        	}

        	if (endLastTime == null) {
        		fq.append("*");
        	} else {
        		fq.append(endLastTime);
        	}

        	fq.append("] ");
        }

    	Double geolongitude = userCoreInfoQuery.getGeolongitude();
    	Double geolatitude = userCoreInfoQuery.getGeolatitude();
    	Double radius = userCoreInfoQuery.getRadius();
        if ((geolongitude != null) && (geolatitude != null)) {
        	if (radius == null) {
        		radius = Constants.USER_SPATIAL_DISTANCE_DEFAULT;
        	}

        	fq.append("{!geofilt} ");

        	solrQuery.add("sfield", "geospatial");
        	solrQuery.add("pt", geolatitude+","+geolongitude); // 纬度,经度
        	solrQuery.add("d", radius.toString());

        	String orderBy = userCoreInfoQuery.getOrderBy();
        	if (StringUtils.isEmpty(orderBy)) {
        		orderBy = "geodist():asc";
        	}

        	fl += ",distance:geodist()";
        	userCoreInfoQuery.setOrderBy(orderBy);
        }

        solrQuery.add("fl", fl);
        solrQuery.setStart(start);
        solrQuery.setRows(pageSize);

        if (q.length() > 0) {
        	solrQuery.setQuery(q.toString());
        } else {
        	solrQuery.setQuery("*");
        }

        if (fq.length() > 0) {
        	solrQuery.setFilterQueries(fq.toString());
        }

        String orderBy = userCoreInfoQuery.getOrderBy();
        if (!StringUtils.isEmpty(orderBy)) {
        	List<SortClause> values = new ArrayList<SortClause>();

        	String[] sortClauses = orderBy.split(";");
        	for (String sortClause : sortClauses) {
        		if (sortClause.length() <= 3) {
        			continue ;
        		}

        		String[] clauses = sortClause.split(":");
        		if (clauses.length != 2) {
        			continue ;
        		}

        		SortClause sortValue = new SortClause(clauses[0], SolrQuery.ORDER.valueOf(clauses[1]));
        		values.add(sortValue);
        	}

        	if (values.size() > 0) {
        		solrQuery.setSorts(values);
        	}
        }

        return solrQuery;
	}

	private String getFuzzyMatching(String match) {

		int count = StringUtils.countMatches(match, "*");
		if (count > 2) {
			match = match.replace("*", " ");
		} else if (count > 0) {
			//
		} else {
			match = "*" + match + "*";
		}

		return match;
	}
}
