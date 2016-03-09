package com.demo.service;

import com.demo.bean.Member;

import java.util.List;
import java.util.Map;

/**
 * Created by adon on 2016/3/3 0003.
 */
public interface MemberService {

	public void saveOrUpdate(Member member);

	public Member get(Long memberId);

	public List queryMember(int page, int rowsPerPage);

}
