package com.demo.dao.impl;

import com.demo.bean.MemberLoginLog;
import com.demo.common.dao.BaseDaoHibernate;
import com.demo.dao.MemberLoginLogDao;
import org.springframework.stereotype.Repository;

/**
 * Created by adon on 2016/3/3 0003.
 */
@Repository(value = "memberLoginLogDao")
public class MemberLoginLogDaoHibernateImpl extends BaseDaoHibernate<MemberLoginLog> implements MemberLoginLogDao {
}
