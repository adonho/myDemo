package com.demo.bean;

import com.demo.common.bean.BaseEntity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by adon on 2016/3/3 0003.
 */
public class MemberSafe extends BaseEntity {

	public final static short TYPE_MOBILE = 0;
	public final static short TYPE_MAIL = 1;

	/**
	 * ID
	 */
	private Long id;
	/**
	 * 会员
	 */
	private Member member;
	/**
	 * 绑定 邮箱、手机号
	 */
	private String bindTarget;
	/**
	 * 绑定类型：0，邮箱；1，手机
	 */
	private short bindType = TYPE_MOBILE;
	/**
	 * 推荐人
	 */
	private Set<MemberSafeQA> questions;

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

	public String getBindTarget() {
		return bindTarget;
	}

	public void setBindTarget(String bindTarget) {
		this.bindTarget = bindTarget;
	}

	public short getBindType() {
		return bindType;
	}

	public void setBindType(short bindType) {
		this.bindType = bindType;
	}

	public Set<MemberSafeQA> getQuestions() {
		if (questions == null) {
			questions = new HashSet<MemberSafeQA>();
		}
		return questions;
	}

	public void setQuestions(Set<MemberSafeQA> questions) {
		this.questions = questions;
	}
}
