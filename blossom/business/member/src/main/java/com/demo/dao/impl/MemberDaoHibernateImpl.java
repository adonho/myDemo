package com.demo.dao.impl;

import com.demo.bean.Member;
import com.demo.common.dao.BaseDaoHibernate;
import com.demo.common.util.hibernate.MSqlQuery;
import com.demo.dao.MemberDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adon on 2016/3/3 0003.
 */
@Repository(value = "memberDao")
public class MemberDaoHibernateImpl extends BaseDaoHibernate<Member> implements MemberDao {

	@Override
	public Member getMember(String target, String password) {

		List<Member> result = (List<Member>) hibernateTemplate
				.find("FROM Member m WHERE m.mobile=? AND m.password=?", target, password);
		if (result.isEmpty()) {
			result = (List<Member>) hibernateTemplate
					.find("FROM Member m WHERE m.nickname=? AND m.password=?", target, password);
		}

		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public Member getMemberByMobile(String mobile) {

		Map con = new HashMap();
		con.put("mobile", mobile);
		List<Member> list = queryMember(con);

		return list.isEmpty()? null : list.get(0);
	}

	@Override
	public Member getMemberByNickname(String nickname) {

		Map con = new HashMap();
		con.put("nickname", nickname);
		List<Member> list = queryMember(con);

		return list.isEmpty()? null : list.get(0);
	}

	public List<Member> queryMember(Map con) {

		if (con.isEmpty()) {
			return new ArrayList<Member>();
		}

		List params = new ArrayList();

		StringBuilder sb = new StringBuilder("FROM Member m WHERE 1=1");

		if (con.containsKey("mobile")) {
			sb.append(" AND m.mobile=?");
			params.add(con.get("mobile"));
		}

		if (con.containsKey("nickname")) {
			sb.append(" AND m.nickname=?");
			params.add(con.get("nickname"));
		}

		if(params.isEmpty()){
			return new ArrayList<Member>();
		}

		hibernateTemplate.setMaxResults(100);
		List<Member> list = (List<Member>) hibernateTemplate.find(sb.toString(), params.toArray());
		hibernateTemplate.setMaxResults(0);

		return list;
	}

	public List queryMember(int dataType, Map con, int page, int rowsPerPage){

		if(con == null){
			con = new HashMap();
		}

		MSqlQuery query = new MSqlQuery("SELECT", page, rowsPerPage);

		// select
		if(dataType==1){
			String[] scalars = {"id", "mobile", "nickname"};
			query.append(" m.id AS id");
			query.append(", m.mobile AS mobile");
			query.append(", m.nickname AS nickname");
			query.addAllScalars(scalars);
		} else {
			String[] scalars = {"id", "mobile", "nickname"};
			query.append(" m.id AS id");
			query.append(", m.mobile AS mobile");
			query.append(", m.nickname AS nickname");
			query.addAllScalars(scalars);
		}

		// from
		query.append(" FROM tb_member m WHERE 1=1");

		// condition
		if (con.containsKey("mobile")) {
			query.append(" AND m.mobile=?");
			query.addParam(con.get("mobile"));
		}

		if (con.containsKey("nickname")) {
			query.append(" AND m.nickname=?");
			query.addParam(con.get("nickname"));
		}

		// order by
		if(con.containsKey("orderBy")){
			query.append(" ORDER BY ").append((String) con.get("orderBy"));
		}

		return hibernateTemplate.executeSQL(query);

	}

}
