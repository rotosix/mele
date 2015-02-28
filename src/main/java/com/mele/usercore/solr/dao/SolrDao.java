package com.mele.usercore.solr.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mele.cloud.CloudSolrServerManager;
import com.mele.common.Constants;
import com.mele.pinyin.service.PinyinService;
import com.mele.usercore.index.model.UserCoreInfo;
import com.mele.utils.ItvJsonUtil;

@Repository
public class SolrDao {
	private Logger log = LoggerFactory.getLogger(SolrDao.class);
	@Autowired
	private PinyinService pinyinService;

	public void updateUsers(List<UserCoreInfo> userCoreInfos) throws Exception {
		SolrServer solrServer = CloudSolrServerManager.getCloudSolrServer();

		UpdateResponse resp = solrServer.addBeans(userCoreInfos);
		log.debug("updateUsers userCoreInfos:"+ItvJsonUtil.writeValue(userCoreInfos)+ " response:"+resp.toString());

		solrServer.commit(true, true);
	}

	public void delUser(String uid) throws Exception {
		SolrServer solrServer = CloudSolrServerManager.getCloudSolrServer();

		UpdateResponse resp = solrServer.deleteById(uid);
		log.debug("delUser uid:"+uid+" response:"+resp.toString());

		solrServer.commit(true, true);
	}

	public List<UserCoreInfo> getUserInfos(SolrQuery solrQuery) throws Exception {
		SolrServer solrServer = CloudSolrServerManager.getCloudSolrServer();

		QueryResponse resp = solrServer.query(solrQuery);
        log.debug("solrQuery:"+solrQuery+" solrServer:"+solrServer+" response:"+resp.toString());

        List<UserCoreInfo> userCoreInfos = resp.getBeans(UserCoreInfo.class);
        log.debug("userCoreInfos:"+ItvJsonUtil.writeValue(userCoreInfos));

        return userCoreInfos;
	}

	public List<Object> getSuggestArea(String expectArea, SolrQuery solrQuery) throws Exception {
		List<Object> areaSuggestions = new ArrayList<Object>();

		SolrServer solrServer = CloudSolrServerManager.getCloudSolrServer();

		QueryResponse resp = solrServer.query(solrQuery);
        log.debug("solrQuery:"+solrQuery+" solrServer:"+solrServer+" response:"+resp.toString());
        log.debug("resp.getResponse():"+resp.getResponse());

		SolrDocumentList docs = resp.getResults();
		if ((docs == null) || (docs.size() <= 0)) {
			return areaSuggestions;
		}

		Set<String> locs = new HashSet<String>();
		StringBuilder sb = new StringBuilder();
		boolean isContained = false;

		String needArea = expectArea.replace("*", "");
		String pinyinNeedArea = needArea.toLowerCase();

		for (SolrDocument doc : docs) {
			sb.setLength(0);
			isContained = false;

			try {
				JSONObject loc = JSONObject.fromObject(doc.getFieldValue("location"));
				String country = loc.optString("country");
				String province = loc.optString("province");
				String city = loc.optString("city");
				String district = loc.optString("district");

				String pinyinCountry = pinyinService.getPinyin(country);
				String pinyinProvince = pinyinService.getPinyin(province);
				String pinyinCity = pinyinService.getPinyin(city);
				String pinyinDistrict = pinyinService.getPinyin(district);				

				if (!StringUtils.isEmpty(country)) {
					sb.append(country.replace(" ", "-"));

					if (country.contains(needArea) || pinyinCountry.contains(pinyinNeedArea)) {
						locs.add(sb.toString());
						isContained = true;
					}					

					sb.append(" ");
				}

				if (!StringUtils.isEmpty(province)) {
					sb.append(province.replace(" ", "-"));

					if (isContained || province.contains(needArea) || pinyinProvince.contains(pinyinNeedArea)) {
						locs.add(sb.toString());
						isContained = true;
					}

					sb.append(" ");
				}

				if (!StringUtils.isEmpty(city) && !city.equals(province)) {
					sb.append(city.replace(" ", "-"));

					if (isContained || city.contains(needArea) || pinyinCity.contains(pinyinNeedArea)) {
						locs.add(sb.toString());
					}

					sb.append(" ");
				} else {
					if (!StringUtils.isEmpty(district)) {
						sb.append(district.replace(" ", "-"));

						if (isContained || district.contains(needArea) || pinyinDistrict.contains(pinyinNeedArea)) {
							locs.add(sb.toString());
						}

						sb.append(" ");
					}
				}
			} catch (Exception e) {
				log.error("getSuggestArea solrQuery:"+solrQuery+" doc:"+doc+" exception:"+e);
				e.printStackTrace();
			}
		}

		List<String> areas = new ArrayList<String>();
		areas.addAll(locs);

		if (areas.size() > 1) {
			Collections.sort(areas, new Comparator<String>() {
				@Override
				public int compare(String area1, String area2) {
					return area1.length() - area2.length();
				}
			});
		}

		if (areas.size() > Constants.SOLR_CLOUD_AREA_NUM_MAX) {
			areas = areas.subList(0, Constants.SOLR_CLOUD_AREA_NUM_MAX);
		}

		for (String area : areas) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("area", area);

			String pinyinArea = pinyinService.getPinyin(area);
			if (!StringUtils.isEmpty(pinyinArea) && (!pinyinArea.equals(area))) {
				map.put("pinyin", pinyinArea);
			}

			areaSuggestions.add(map);
		}

        log.debug("areaSuggestions:"+ItvJsonUtil.writeValue(areaSuggestions));
        return areaSuggestions;
	}

	public void optimizeIndex() {
		SolrServer solrServer = CloudSolrServerManager.getCloudSolrServer();

		try {
			solrServer.optimize();
		} catch (Exception e) {
			log.error("optimizeIndex exception:"+e);
			e.printStackTrace();
		}
	}
}
