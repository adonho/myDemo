package com.demo.bean;

import com.demo.common.bean.BaseEntity;

import java.util.Date;

/**
 * Created by adon on 2016/3/3 0003.
 */
public class Member extends BaseEntity {

	/**
	 * ID
	 */
	private Long id;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 密码MD5
	 */
	private transient String password;
	/**
	 * 手机，唯一绑定
	 */
	private String mobile;
	/**
	 * 推荐人
	 */
	private Member recommend;
	/**
	 * 状态
	 */
	private short state = STATE_Y;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Member getRecommend() {
		return recommend;
	}

	public void setRecommend(Member recommend) {
		this.recommend = recommend;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

}
