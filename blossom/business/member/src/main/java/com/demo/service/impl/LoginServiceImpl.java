package com.demo.service.impl;

import com.demo.bean.Member;
import com.demo.bean.MemberLoginLog;
import com.demo.bean.ModifyRecord;
import com.demo.common.service.BaseService;
import com.demo.common.util.ServiceLocator;
import com.demo.dao.ModifyRecordDao;
import com.demo.service.LoginService;
import com.demo.dao.MemberDao;
import com.demo.dao.MemberLoginLogDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * Created by adon on 2016/3/3 0003.
 */
@Repository(value = "loginServiceLocal")
@Transactional(readOnly=true)
public class LoginServiceImpl extends BaseService implements LoginService {

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private MemberLoginLogDao memberLoginLogDao;

	@Transactional(readOnly=false)
	public Member login(String target, String password, Long sessionId, String loginIP, int clientType) {

		Member member = memberDao.getMember(target, password);
		if(member == null)
			return null;

//		XMemcachedClient client = (XMemcachedClient) ServiceLocator.getBean("memCachedClient");

		MemberLoginLog loginLog = new MemberLoginLog();
		loginLog.setMember(member);
		loginLog.setLoginTarget(target);
		loginLog.setLoginIP(loginIP);
		loginLog.setLoginClient(clientType);
		loginLog.setCreateDate(new Date());
		memberLoginLogDao.save(loginLog);

		return member;
	}

	@Transactional(readOnly=false)
	public Member register(String mobile, String nickname, String password, Map params) {

		if(StringUtils.isBlank(mobile)){
			throw new IllegalArgumentException("Empty_mobile");
		} else if(!StringUtils.isNumeric(mobile)){
			throw new IllegalArgumentException("Error_mobile");
		} else if(StringUtils.isBlank(nickname)){
			throw new IllegalArgumentException("Empty_nickname");
		} else if(nickname.length()<3 || nickname.length()>20){
			throw new IllegalArgumentException("Error_nickname");
		} else if(StringUtils.isBlank(password)){
			throw new IllegalArgumentException("Empty_password");
		} else if(password.length()!=32){
			throw new IllegalArgumentException("Error_password");
		}

		Member member = memberDao.getMemberByMobile(mobile);
		if(member!=null){
			throw new IllegalArgumentException("Exists_mobile");
		}

		member = memberDao.getMemberByNickname(nickname);
		if(member!=null){
			throw new IllegalArgumentException("Exists_nickname");
		}

		member = new Member();
		member.setMobile(mobile);
		member.setNickname(nickname);
		member.setPassword(password);
		member.setCreateDate(new Date());
		if(params!=null){
			if(params.containsKey("recommend")){
				Member recommend = (Member) params.get("recommend");
				member.setRecommend(recommend);
			}
		}
		memberDao.save(member);

		return memberDao.get(member.getId());
	}

	@Override
	@Transactional(readOnly=false)
	public void modifyPsw(String mobile, String newPsw) {
		Member member = memberDao.getMemberByMobile(mobile);
		if(null==member){
			throw new IllegalArgumentException("No_exists_member");
		}
		modifyPsw(member, newPsw);
	}

	@Override
	@Transactional(readOnly=false)
	public void modifyPsw(String mobile, String oldPsw, String newPsw) {
		Member member = memberDao.getMemberByMobile(mobile);
		if(null==member){
			throw new IllegalArgumentException("No_exists_member");
		} else if(null!=oldPsw && !oldPsw.equals(member.getPassword())){
			throw new IllegalArgumentException("Wrong_old_password");
		}
		modifyPsw(member, newPsw);
	}

	private void modifyPsw(Member member, String newPsw){
		if (StringUtils.isBlank(newPsw) || newPsw.length()!=32){
			throw new IllegalArgumentException("Illegal_new_password");
		}
		ModifyRecord mr = new ModifyRecord(member.getId(), Member.class.getName(), Member.class.getSimpleName(),
				String.class.getName(), "password", member.getPassword(), newPsw, new Date());
		member.setPassword(newPsw);
		ModifyRecordDao modifyRecordDao = (ModifyRecordDao) ServiceLocator.getBean("modifyRecordDao");
		memberDao.saveOrUpdate(member);
		modifyRecordDao.save(mr);
	}
}
