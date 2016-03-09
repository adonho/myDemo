package com.demo.service.impl;

import com.demo.bean.Member;
import com.demo.common.service.BaseService;
import com.demo.service.MemberService;
import com.demo.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by adon on 2016/3/3 0003.
 */
@Repository(value = "memberServiceLocal")
public class MemberServiceImpl extends BaseService implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Override
	public void saveOrUpdate(Member member) {
		memberDao.saveOrUpdate(member);
	}

	@Override
	public Member get(Long memberId) {
		return memberDao.get(memberId);
	}

	@Override
	public List queryMember(int page, int rowsPerPage) {

		return memberDao.queryMember(MemberDao.DATA_TYPE_BASE, null, 1, 10);
	}
}
