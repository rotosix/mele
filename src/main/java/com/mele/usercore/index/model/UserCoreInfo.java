package com.mele.usercore.index.model;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

import com.mele.utils.ItvJsonUtil;

public class UserCoreInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * 用户标识
	 */
	@Field
	private String uid;
	/*
	 * 昵称
	 */
	@Field
	private String username;
	/*
	 * 头像
	 */
	@Field
	private String avatar;
	/*
	 * 类型，1：密星；2：粉丝
	 */
	@Field
	private Integer type;
	/*
	 * 级别
	 */
	@Field
	private Integer level;
	/*
	 * 性别，1：男；2：女；0：未知
	 */
	@Field
	private Integer gender;
	/*
	 * 生日
	 */
	@Field
	private Integer birthday;
	/*
	 * 标签
	 */
	@Field
	private String[] label;
	/*
	 * 注册时间
	 */
	@Field
	private Integer registertime;
	/*
	 * 最后更新在线状态的时间
	 */
	@Field
	private Integer lasttime;
	/*
	 * 维度,经度
	 */
	@Field
	private String geospatial;
	/*
	 * JSON格式位置信息
	 */
	@Field
	private String location;
	/*
	 * 解析后的国家/省/市位置信息
	 */
	@Field
	private String[] multilocation;
	/*
	 * 距离（在搜索时实时计算并返回）
	 */
	@Field
	private Double distance;

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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getBirthday() {
		return birthday;
	}

	public void setBirthday(Integer birthday) {
		this.birthday = birthday;
	}

	public String[] getLabel() {
		return label;
	}

	public void setLabel(String[] label) {
		this.label = label;
	}

	public Integer getRegistertime() {
		return registertime;
	}

	public void setRegistertime(Integer registertime) {
		this.registertime = registertime;
	}

	public Integer getLasttime() {
		return lasttime;
	}

	public void setLasttime(Integer lasttime) {
		this.lasttime = lasttime;
	}

	public String getGeospatial() {
		return geospatial;
	}

	public void setGeospatial(String geospatial) {
		this.geospatial = geospatial;
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

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return ItvJsonUtil.writeValue(this);
	}
}
