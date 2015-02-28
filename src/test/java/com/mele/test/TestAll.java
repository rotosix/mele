package com.mele.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mele.usercore.index.model.UserCoreInfo;
import com.mele.usercore.index.service.IndexService;
import com.mele.usercore.search.service.SearchService;
import com.mele.utils.ItvJsonUtil;

public class TestAll extends BaseJunit4Test {

    @Autowired
    private IndexService indexService;
    @Autowired
    private SearchService searchService;

    @Test
    public void testIndexAndQuery() {

    	List<UserCoreInfo> users = new ArrayList<>();

    	String json = "{\"uid\":\"505851\",\"username\":\"fayerline\",\"avatar\":null,\"level\":0,\"mobilephone\":null,\"gender\":2,\"birthday\":757378813,\"horoscope\":1,\"bloodtype\":\"O\",\"school\":null,\"hometown\":null,\"online\":1,\"logintime\":1408107896,\"geospatial\":\"39.904392,116.452713\",\"location\":\"{\\\"country\\\":\\\"中国\\\",\\\"street\\\":\\\"东三环中路 39号院\\\",\\\"district\\\":\\\"朝阳区\\\",\\\"name\\\":\\\"建外SOHO西区15号楼\\\",\\\"province\\\":\\\"北京市\\\"}\"}";
    	UserCoreInfo user0 = ItvJsonUtil.readValue(json, UserCoreInfo.class); 
    	users.add(user0);

    	UserCoreInfo user1 = new UserCoreInfo();
    	user1.setUid("105320");
    	user1.setUsername("james");
    	user1.setLevel(10);
    	user1.setBirthday(100);
    	user1.setGeospatial("39.904393,116.452713"); // 纬度,经度
    	user1.setLocation("{\"country\":\"中国\",\"street\":\"东三环中路 39号院\",\"district\":\"东城区\",\"name\":\"建外SOHO西区15号楼\",\"province\":\"北京市\"}");
    	users.add(user1);

    	UserCoreInfo user2 = new UserCoreInfo();
    	user2.setUid("105321");
    	user2.setUsername("ghost");
    	user2.setLevel(20);
    	user2.setBirthday(200);
    	user2.setGender(1);
    	user2.setGeospatial("39.904392,116.452714"); // 纬度,经度
    	user2.setLocation("{\"country\":\"日本\",\"street\":\"东三环中路 39号院\",\"district\":\"北区\",\"name\":\"建外SOHO西区15号楼\",\"province\":\"东京\"}");
    	users.add(user2);

    	indexService.updateUsers(users);

/*
    	UserCoreInfoQuery userCoreInfoQuery = new UserCoreInfoQuery();
    	userCoreInfoQuery.setUid("105*");
    	userCoreInfoQuery.setGender(1);
    	userCoreInfoQuery.setStartLevel(10);
    	userCoreInfoQuery.setStartBirthday(200);
    	userCoreInfoQuery.setGeolatitude(39.904);
    	userCoreInfoQuery.setGeolongitude(116.452);
    	userCoreInfoQuery.setRadius(100.0);
    	System.out.println(searchService.getUserInfos(userCoreInfoQuery));

    	userCoreInfoQuery = new UserCoreInfoQuery();
    	userCoreInfoQuery.setUid("105");
    	System.out.println(searchService.getUserInfos(userCoreInfoQuery));

    	searchService.getSuggestArea("中");
    	System.out.println(searchService.getSuggestArea("chaoyang"));
    	searchService.getSuggestArea("西");
    	System.out.println(searchService.getSuggestArea("北京"));
    	searchService.getSuggestArea("东城区");
    	searchService.getSuggestArea("中国 北京");
    	searchService.getSuggestArea("中国北京市朝阳区");
*/

    	//indexService.delUser("105321");
    	//System.out.println(searchService.getUserInfos(userCoreInfoQuery));
    }
}
