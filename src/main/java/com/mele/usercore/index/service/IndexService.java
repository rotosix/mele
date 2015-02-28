package com.mele.usercore.index.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mele.pinyin.service.PinyinService;
import com.mele.rmi.IIndexService;
import com.mele.usercore.index.model.UserCoreInfo;
import com.mele.usercore.solr.dao.SolrDao;
import com.mele.utils.ItvJsonUtil;

@Service("IndexService")
public class IndexService implements IIndexService {

	private Logger log = LoggerFactory.getLogger(IndexService.class);
	@Autowired
	private SolrDao solrDao;
	@Autowired
	private PinyinService pinyinService;

	@Override
	public void updateUsers(List<UserCoreInfo> userCoreInfos) {

		try {
			long startTime = System.currentTimeMillis();

			userCoreInfos = updateMultiLocation(userCoreInfos);
			solrDao.updateUsers(userCoreInfos);

			long currentTime1 = System.currentTimeMillis();
			log.debug("SolrDao:updateUsers time:" + (currentTime1 - startTime) + "ms");

			log.debug("IndexService:updateUsers time:" + (currentTime1 - startTime) + "ms");
		} catch (Exception e) {
			log.error("userCoreInfos:"+ItvJsonUtil.writeValue(userCoreInfos)+" exception:", e);
		}
	}

	@Override
	public void delUser(String uid) {

		try {
			long startTime = System.currentTimeMillis();

			solrDao.delUser(uid);

			long currentTime1 = System.currentTimeMillis();
			log.debug("SolrDao:delUser time:" + (currentTime1 - startTime) + "ms");

			log.debug("IndexService:delUser time:" + (currentTime1 - startTime) + "ms");
		} catch (Exception e) {
			log.error("delUser uid:"+uid+" exception:", e);
		}
	}

	private List<UserCoreInfo> updateMultiLocation(List<UserCoreInfo> userCoreInfos) {
		List<UserCoreInfo> mlUserCoreInfos = new ArrayList<UserCoreInfo>();

		for (UserCoreInfo userCoreInfo : userCoreInfos) {

			try {
				userCoreInfo.setMultilocation(null);

				String locationJson = userCoreInfo.getLocation();
				if (!StringUtils.isEmpty(locationJson)) {
					JSONObject loc = JSONObject.fromObject(locationJson);
					String country = loc.optString("country");
					String province = loc.optString("province");
					String city = loc.optString("city");
					String district = loc.optString("district");

					List<String> multilocation = new ArrayList<String>();

					if (!StringUtils.isEmpty(country)) {
						multilocation.add(country.replace(" ", "-"));
					}

					if (!StringUtils.isEmpty(province)) {
						multilocation.add(province.replace(" ", "-"));
					}

					if (!StringUtils.isEmpty(city) && !city.equals(province)) {
						multilocation.add(city.replace(" ", "-"));
					}

					if (!StringUtils.isEmpty(district)) {
						multilocation.add(district.replace(" ", "-"));
					}

					if (multilocation.size() > 0) {
						List<String> pinyin = pinyinService.getPinyin(multilocation);
						if ((pinyin != null) && (pinyin.size() > 0)) {
							multilocation.addAll(pinyin);
						}

						userCoreInfo.setMultilocation(multilocation.toArray(new String[multilocation.size()]));
					}
				}
			} catch (Exception e) {
				userCoreInfo.setLocation(null);
				log.error("genMultiLocation userCoreInfo:"+userCoreInfo+" exception:", e);
			}

			mlUserCoreInfos.add(userCoreInfo);
		}

		return mlUserCoreInfos;
	}
}
