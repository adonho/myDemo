package com.demo.dao.impl;

import com.demo.bean.MemberSafe;
import com.demo.common.dao.BaseDaoHibernate;
import com.demo.dao.MemberSafeDao;
import org.springframework.stereotype.Repository;

/**
 * Created by adon on 2016/3/3 0003.
 */
@Repository(value = "memberSafeDao")
public class MemberSafeDaoHibernateImpl extends BaseDaoHibernate<MemberSafe> implements MemberSafeDao {
}
