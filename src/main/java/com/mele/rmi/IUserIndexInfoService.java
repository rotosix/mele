package com.mele.rmi;

import java.util.List;

import com.mele.usercore.index.model.UserCoreInfo;

public interface IUserIndexInfoService {

	List<UserCoreInfo> getUserCoreInfo(List<String> userIds);

}
