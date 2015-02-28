package com.mele.usercore.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mele.common.Constants;
import com.mele.usercore.index.model.UserCoreInfo;
import com.mele.usercore.search.model.UserCoreInfoQuery;
import com.mele.usercore.search.service.SearchService;
import com.mele.utils.ItvJsonUtil;

@Service
public class UserCoreService {

	private final Logger log = LoggerFactory.getLogger(UserCoreService.class);
	@Autowired
	private SearchService searchService;

	public List<Object> getUserCoreBriefInfo(String queryJson) {
		List<Object> userCoreInfoList = new ArrayList<Object>();

		try {
			UserCoreInfoQuery userCoreInfoQuery = ItvJsonUtil.readValue(queryJson, UserCoreInfoQuery.class);
			if (userCoreInfoQuery == null) {
				return userCoreInfoList;
			}

			List<UserCoreInfo> userCoreInfos = searchService.getUserInfos(userCoreInfoQuery);
			if ((userCoreInfos == null) || (userCoreInfos.size() <= 0)) {
				return userCoreInfoList;
			}

			for (UserCoreInfo userCoreInfo : userCoreInfos) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("uid", userCoreInfo.getUid());
				
				String username = userCoreInfo.getUsername();
				if (!StringUtils.isEmpty(username)) {
					map.put("username", username);
				} else {
					map.put("username", "");
				}

				String avatar = userCoreInfo.getAvatar();
				if (!StringUtils.isEmpty(avatar)) {
					map.put("avatar", avatar);
				} else {
					map.put("avatar", "");
				}

				userCoreInfoList.add(map);
			}
		} catch (Exception e) {
			log.error("queryJson:"+queryJson+" exception:", e);
		}

		return userCoreInfoList;		
	}

	public List<Object> getUserCoreDetailInfo(String queryJson) {
		List<Object> userCoreInfoList = new ArrayList<Object>();

		try {
			UserCoreInfoQuery userCoreInfoQuery = ItvJsonUtil.readValue(queryJson, UserCoreInfoQuery.class);
			if (userCoreInfoQuery == null) {
				return userCoreInfoList;
			}

			List<UserCoreInfo> userCoreInfos = searchService.getUserInfos(userCoreInfoQuery);
			for (UserCoreInfo userCoreInfo : userCoreInfos) {
				Map<String, Object> coreInfo = new HashMap<String, Object>();
				
				String uid = userCoreInfo.getUid();
				if (StringUtils.isEmpty(uid)) {
					continue ;
				} else {
					coreInfo.put("uid", uid);
				}

				String username = userCoreInfo.getUsername();
				if (!StringUtils.isEmpty(username)) {
					coreInfo.put("username", username);
				}

				String avatar = userCoreInfo.getAvatar();
				if (!StringUtils.isEmpty(avatar)) {
					coreInfo.put("avatar", avatar);
				}

				Integer level = userCoreInfo.getLevel();
				if (level != null) {
					coreInfo.put("level", level);
				}

				Integer gender = userCoreInfo.getGender();
				if (gender != null) {
					coreInfo.put("gender", gender);
				}

				Integer birthday = userCoreInfo.getBirthday();
				if (birthday != null) {
					coreInfo.put("birthday", birthday);
				}

				Integer logintime = userCoreInfo.getLasttime();
				if (logintime != null) {
					coreInfo.put("logintime", logintime);
				}

				Integer registertime = userCoreInfo.getRegistertime();
				if (logintime != null) {
					coreInfo.put("registertime", registertime);
				}

				String location = userCoreInfo.getLocation();
				if (!StringUtils.isEmpty(location)) {
					try {
						JSONObject obj = JSONObject.fromObject(location);
						if (obj != null) {
							coreInfo.put("location", obj);
						}
					} catch (Exception e) {
						log.error("getUserCoreDetailInfo error location queryJson:"+queryJson
								+" userCoreInfo:"+userCoreInfo+" exception:", e);
					}
				}

				Double distance = userCoreInfo.getDistance();
				if (distance != null) {
					coreInfo.put("distance", distance);
				}

				userCoreInfoList.add(coreInfo);
			}
		} catch (Exception e) {
			log.error("queryJson:"+queryJson+" exception:", e);
		}

		return userCoreInfoList;
	}

	public void optimizeIndex() {
		searchService.optimizeIndex();
	}

	public Object getSuggestAreaInfo(String suggestJson) {
		JSONObject suggest = JSONObject.fromObject(suggestJson);
		String expectArea = suggest.getString("expect");

		return searchService.getSuggestArea(expectArea);
	}

	public List<String> getHotAreaInfo(String areaHotJson) {
		List<String> areas = new ArrayList<String>();

		String[] hotAreas = Constants.SOLR_CLOUD_AREA_HOT.split(";");
		for (String area : hotAreas) {
			if (!StringUtils.isEmpty(area)) {
				areas.add(area);
			}
		}

		return areas;
	}
}
