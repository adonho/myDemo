package com.demo.bean;

import com.demo.common.bean.BaseEntity;

import java.util.Date;

/**
 * Created by adon on 2016/3/3 0003.
 */
public class MemberSafeQA extends BaseEntity {

	/**
	 * ID
	 */
	private Long id;
	/**
	 * 外键
	 */
	private MemberSafe memberSafe;
	/**
	 * 问题
	 */
	private String question;
	/**
	 * 回答
	 */
	private String answer;
	/**
	 * 状态：0，无效；1，有效
	 */
	private short state = STATE_Y;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MemberSafe getMemberSafe() {
		return memberSafe;
	}

	public void setMemberSafe(MemberSafe memberSafe) {
		this.memberSafe = memberSafe;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}
}
