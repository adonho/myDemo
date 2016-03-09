package com.demo.dao.impl;

import com.demo.bean.ModifyRecord;
import com.demo.common.dao.BaseDaoHibernate;
import com.demo.dao.ModifyRecordDao;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * Created by adon on 2016/3/4 0004.
 */
@Repository(value = "modifyRecordDao")
public class ModifyRecordDaoHibernateImpl extends BaseDaoHibernate<ModifyRecord> implements ModifyRecordDao {

	@Override
	public void saveAll(ModifyRecord... modifyRecord) {
		hibernateTemplate.saveOrUpdateAll(modifyRecord);
	}
}
