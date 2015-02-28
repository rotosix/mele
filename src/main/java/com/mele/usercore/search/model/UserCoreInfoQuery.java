package com.mele.usercore.search.model;

import java.io.Serializable;

import com.mele.utils.ItvJsonUtil;

public class UserCoreInfoQuery implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * 结果分页，默认：1
	 */
	private Integer pageNo;
	/*
	 * 每页结果数，默认：10，最大不超过100，可以通过配置进行调整
	 */
	private Integer pageSize;
	/*
	 * 用户标识
	 */
	private String uid;
	/*
	 * 昵称
	 */
	private String username;
	/*
	 * 类型，1：密星；2：粉丝
	 */
	private Integer type;
	/*
	 * 级别
	 */
	private Integer startLevel;
	private Integer endLevel;
	/*
	 * 性别，1：男；2：女；0：未知
	 */
	private Integer gender;
	/*
	 * 标签
	 */
	private String[] label;
	/*
	 * 生日
	 */
	private Integer startBirthday;
	private Integer endBirthday;
	/*
	 * 注册时间
	 */
	private Integer startRegisterTime;
	private Integer endRegisterTime;
	/*
	 * 最后更新在线状态的时间
	 */
	private Integer startLastTime;
	private Integer endLastTime;
	/*
	 * 维度
	 */
	private Double geolatitude;
	/*
	 * 经度
	 */
	private Double geolongitude;
	/*
	 * 搜索半径，默认10万公里
	 */
	private Double radius;
	/*
	 * JSON格式位置信息
	 */
	private String location;
	/*
	 * 解析后的国家/省/市位置信息
	 */
	private String[] multilocation;
	/*
	 * 排序方案，多个字段排序时以分号;分隔，默认按距离倒序排序（由近到远）
	 */
	private String orderBy;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStartLevel() {
		return startLevel;
	}

	public void setStartLevel(Integer startLevel) {
		this.startLevel = startLevel;
	}

	public Integer getEndLevel() {
		return endLevel;
	}

	public void setEndLevel(Integer endLevel) {
		this.endLevel = endLevel;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String[] getLabel() {
		return label;
	}

	public void setLabel(String[] label) {
		this.label = label;
	}

	public Integer getStartBirthday() {
		return startBirthday;
	}

	public void setStartBirthday(Integer startBirthday) {
		this.startBirthday = startBirthday;
	}

	public Integer getEndBirthday() {
		return endBirthday;
	}

	public void setEndBirthday(Integer endBirthday) {
		this.endBirthday = endBirthday;
	}

	public Integer getStartRegisterTime() {
		return startRegisterTime;
	}

	public void setStartRegisterTime(Integer startRegisterTime) {
		this.startRegisterTime = startRegisterTime;
	}

	public Integer getEndRegisterTime() {
		return endRegisterTime;
	}

	public void setEndRegisterTime(Integer endRegisterTime) {
		this.endRegisterTime = endRegisterTime;
	}

	public Integer getStartLastTime() {
		return startLastTime;
	}

	public void setStartLastTime(Integer startLastTime) {
		this.startLastTime = startLastTime;
	}

	public Integer getEndLastTime() {
		return endLastTime;
	}

	public void setEndLastTime(Integer endLastTime) {
		this.endLastTime = endLastTime;
	}

	public Double getGeolatitude() {
		return geolatitude;
	}

	public void setGeolatitude(Double geolatitude) {
		this.geolatitude = geolatitude;
	}

	public Double getGeolongitude() {
		return geolongitude;
	}

	public void setGeolongitude(Double geolongitude) {
		this.geolongitude = geolongitude;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String[] getMultilocation() {
		return multilocation;
	}

	public void setMultilocation(String[] multilocation) {
		this.multilocation = multilocation;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return ItvJsonUtil.writeValue(this);
	}
}
