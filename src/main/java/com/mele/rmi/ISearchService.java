package com.mele.rmi;

import java.util.List;

import com.mele.usercore.index.model.UserCoreInfo;
import com.mele.usercore.search.model.UserCoreInfoQuery;

public interface ISearchService {

	List<UserCoreInfo> getUserInfos(UserCoreInfoQuery userCoreInfoQuery);

	List<Object> getSuggestArea(String expectArea);
}
