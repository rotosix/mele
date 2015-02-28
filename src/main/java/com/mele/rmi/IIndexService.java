package com.mele.rmi;

import java.util.List;

import com.mele.usercore.index.model.UserCoreInfo;

public interface IIndexService {

	void updateUsers(List<UserCoreInfo> userCoreInfos);
	void delUser(String uid);
}
