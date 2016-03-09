package com.demo.dao;

import com.demo.bean.Member;
import com.demo.common.dao.Dao;

import java.util.List;
import java.util.Map;

/**
 * Created by adon on 2016/3/3 0003.
 */
public interface MemberDao extends Dao<Member> {

	public final static int DATA_TYPE_BASE = 1;

	Member getMember(String target, String password);

	Member getMemberByMobile(String mobile);

	Member getMemberByNickname(String nickname);

	List queryMember(int dataType, Map con, int page, int rowsPerPage);
}
