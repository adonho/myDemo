package com.demo.service;

import com.demo.bean.Member;
import com.demo.common.service.BaseService;

import java.util.Map;

/**
 * Created by adon on 2016/3/3 0003.
 */
public interface LoginService{

	public static final int CLIENT_TYPE_WEB = 1;

	public Member login(String target, String password, Long sessionId, String loginIP, int clientType);

	public Member register(String mobile, String nickname, String password, Map params);

	public void modifyPsw(String mobile, String newPsw);

	public void modifyPsw(String mobile, String oldPsw, String newPsw);

}
