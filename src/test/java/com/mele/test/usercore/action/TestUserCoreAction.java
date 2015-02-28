package com.mele.test.usercore.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;



//import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mele.base.action.BaseAction;
import com.mele.common.RedisConstants;
import com.mele.usercore.index.model.UserCoreInfo;
import com.mele.usercore.index.service.IndexService;
import com.mele.utils.ItvJsonUtil;

public class TestUserCoreAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final Logger log = LoggerFactory.getLogger(TestUserCoreAction.class);
	@Autowired
	private IndexService indexService;

	public void adduser() {
		String code = RedisConstants.SC_UNDEFINED;
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			String addUserJson = super.getInputStream(super.getRequest());
			UserCoreInfo userCoreInfo = ItvJsonUtil.readValue(addUserJson, UserCoreInfo.class);
			List<UserCoreInfo> userCoreInfos = new ArrayList<UserCoreInfo>();
			//List<UserCoreInfo> userCoreInfos = ItvJsonUtil.readValue(addUserJson, new TypeReference<List<UserCoreInfo>>(){});

			userCoreInfos.add(userCoreInfo);
			indexService.updateUsers(userCoreInfos);
			resultMap.put("msg", "add user info success");
		} catch (Exception e) {
			log.error("add user info exception:", e);

			code = RedisConstants.SC_PARAMETER_INVALID;
			resultMap.put("msg", "paramter error");
		}

		super.sendTextAjax(ItvJsonUtil.RESPONSE(code, resultMap));
	}

	public void deluser() {
		String code = RedisConstants.SC_UNDEFINED;
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			String deluserJson = super.getInputStream(super.getRequest());
		    JSONObject data = JSONObject.fromObject(deluserJson);
	        String uid = data.optString("uid");

			indexService.delUser(uid);
			resultMap.put("msg", "del user info success");
		} catch (Exception e) {
			log.error("del user info exception:", e);

			code = RedisConstants.SC_PARAMETER_INVALID;
			resultMap.put("msg", "paramter error");
		}

		super.sendTextAjax(ItvJsonUtil.RESPONSE(code, resultMap));
	}
}
