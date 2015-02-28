package com.mele.usercore.action;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mele.base.action.BaseAction;
import com.mele.common.RedisConstants;
import com.mele.usercore.service.UserCoreService;
import com.mele.utils.ItvJsonUtil;

public class UserCoreAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final Logger log = LoggerFactory.getLogger(UserCoreAction.class);
	@Autowired
	private UserCoreService userCoreService;

	public void brief() {
		String code = RedisConstants.SC_UNDEFINED;
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			String briefJson = super.getInputStream(super.getRequest());

			resultMap.put("usercore", userCoreService.getUserCoreBriefInfo(briefJson));
			resultMap.put("msg", "user brief info success");
		} catch (Exception e) {
			log.info("get brief exception {}", e);

			code = RedisConstants.SC_PARAMETER_INVALID;
			resultMap.put("msg", "paramter error");
		}

		super.sendTextAjax(ItvJsonUtil.RESPONSE(code, resultMap));
	}

	public void detail() {
		String code = RedisConstants.SC_UNDEFINED;
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			String detailJson = super.getInputStream(super.getRequest());

			resultMap.put("usercore", userCoreService.getUserCoreDetailInfo(detailJson));
			resultMap.put("msg", "get user detail info success");
		} catch (Exception e) {
			log.info("get detail exception {}", e);

			code = RedisConstants.SC_PARAMETER_INVALID;
			resultMap.put("msg", "paramter error");
		}

		super.sendTextAjax(ItvJsonUtil.RESPONSE(code, resultMap));
	}

	public void optimize() {
		String code = RedisConstants.SC_UNDEFINED;
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			userCoreService.optimizeIndex();
			resultMap.put("msg", "get optimize index success");
		} catch (Exception e) {
			log.info("get optimize index exception {}", e);

			code = RedisConstants.SC_PARAMETER_INVALID;
			resultMap.put("msg", "paramter error");
		}

		super.sendTextAjax(ItvJsonUtil.RESPONSE(code, resultMap));
	}

	public void suggest() {
		String code = RedisConstants.SC_UNDEFINED;
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			String suggestJson = super.getInputStream(super.getRequest());

			resultMap.put("suggest", userCoreService.getSuggestAreaInfo(suggestJson));
			resultMap.put("msg", "get suggest area info success");
		} catch (Exception e) {
			log.info("get suggest area exception {}", e);

			code = RedisConstants.SC_PARAMETER_INVALID;
			resultMap.put("msg", "paramter error");
		}

		super.sendTextAjax(ItvJsonUtil.RESPONSE(code, resultMap));
	}

	public void hot() {
		String code = RedisConstants.SC_UNDEFINED;
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			String hotAreaJson = super.getInputStream(super.getRequest());

			resultMap.put("areas", userCoreService.getHotAreaInfo(hotAreaJson));
			resultMap.put("msg", "get hot area info success");
		} catch (Exception e) {
			log.info("get hot area exception {}", e);

			code = RedisConstants.SC_PARAMETER_INVALID;
			resultMap.put("msg", "paramter error");
		}

		super.sendTextAjax(ItvJsonUtil.RESPONSE(code, resultMap));
	}
}
