package com.demo.dao;

import com.demo.bean.ModifyRecord;
import com.demo.common.dao.Dao;

/**
 * Created by adon on 2016/3/4 0004.
 */
public interface ModifyRecordDao extends Dao<ModifyRecord> {

	void saveAll(ModifyRecord... modifyRecord);
}
