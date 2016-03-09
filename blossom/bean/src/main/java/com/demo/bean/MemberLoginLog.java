package com.demo.bean;

import com.demo.common.bean.BaseEntity;

import java.util.Date;

/**
 * Created by adon on 2016/3/3 0003.
 */
public class MemberLoginLog extends BaseEntity {

	/**
	 * ID
	 */
	private Long id;
	/**
	 * 登录人
	 */
	private Member member;
	/**
	 * 登录人 昵称\手机号
	 */
	private String loginTarget;
	/**
	 * 登录IP
	 */
	private String loginIP;

	/**
	 * 登录客户端
	 */
	private Integer loginClient;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getLoginTarget() {
		return loginTarget;
	}

	public void setLoginTarget(String loginTarget) {
		this.loginTarget = loginTarget;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public Integer getLoginClient() {
		return loginClient;
	}

	public void setLoginClient(Integer loginClient) {
		this.loginClient = loginClient;
	}
}
