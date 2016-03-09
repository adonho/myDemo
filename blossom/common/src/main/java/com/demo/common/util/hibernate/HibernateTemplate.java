package com.demo.common.util.hibernate;

import com.demo.common.util.PageList;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.util.Assert;

import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by adon on 2016/2/21 0021.
 */
public class HibernateTemplate extends org.springframework.orm.hibernate3.HibernateTemplate {

	private final static Logger log = Logger.getLogger(HibernateTemplate.class);

	private final static Pattern PATTERN_ORDER_BY = Pattern.compile("\\s[O|o][R|r][D|d][E|e][R|r]\\s*[B|b][Y|y]\\s");

	public void saveOrUpdateAll(final Object... objects) throws DataAccessException {
		Assert.notEmpty(objects);
		executeWithNativeSession(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				for (int i = 0; i < objects.length; i++) {
					session.saveOrUpdate(objects[i]);
					if ((i & 31) == 0 && i > 0) {
						session.flush();
						session.clear();
					}
				}
				return null;
			}
		});
	}

	public List executeSQL(MSqlQuery query) {
		return executeSQL(query.getSql(), query.getScalars(), query.getParams(),
				query.getPage(), query.getRowsPerPage(), query.isCountAllRow());
	}

	/**
	 * 执行普通SQL查询，带分页
	 *
	 * @param SQL
	 * @param scalarList  scalarList别名list，执行复杂sql可能造成column not found
	 *                    scalarList与selectFields对应, 不为空时候返回List<Map>
	 * @param params
	 * @param currentPage
	 * @param rowsPerPage
	 * @param autoCount   是否需要统计总数
	 *                    是否自动计数 （出于性能的考虑，某些查询记录条数的SQL需要优化时，将其设为false）
	 * @return
	 */
	public List executeSQL(final String SQL
			, final List<String> scalarList, final List<Object> params
			, final Integer currentPage, final Integer rowsPerPage
			, final boolean autoCount) {

		return (List) execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException {
				try {
					// 需要分页处理
					boolean needPage = null != rowsPerPage && null != currentPage && rowsPerPage > 0 && currentPage > 0;
					// sql有带条件参数
					boolean hasParams = null != params && params.size() > 0;
					// 列别名，根据别名封装map
					boolean hasScalars = null != scalarList && scalarList.size() > 0;
					int page = needPage ? currentPage : 0;
					int rowCount = 0;
					int pageCount = 0;
					// 分页信息
					if (needPage && autoCount) {
						String countSql = SQL;
						// count不需要排序
						Matcher m = PATTERN_ORDER_BY.matcher(countSql);
						int oPos = 0;
						while (m.find()) {
							oPos = m.start();
						}
						if (oPos > 0) {
							countSql = countSql.substring(0, oPos);
						}
						countSql = "SELECT COUNT(0) FROM (" + countSql + ") AS temp_count";
						// log.debug("countSQL:"+countSql);
						SQLQuery countQuery = session.createSQLQuery(countSql);
						if (hasParams) {
							int i = 0;
							for (Object param : params) {
								countQuery.setParameter(i++, param);
							}
						}
						List countList = countQuery.list();
						if (CollectionUtils.isNotEmpty(countList)) {
							rowCount = ((Number) countList.get(0)).intValue();
							pageCount = (rowCount + rowsPerPage - 1) / rowsPerPage;
							page = page > pageCount ? pageCount : ((rowCount == 0) ? 0 : page);
						}
					}

					SQLQuery query = session.createSQLQuery(SQL);
					// 设置条件参数
					if (hasParams) {
						int i = 0;
						for (Object param : params) {
							query.setParameter(i++, param);
						}
					}
					// 设置查询field别名
					if (hasScalars) {
						for (String scalar : scalarList) {
							query.addScalar(scalar);
						}
					}
					// 分页
					if (needPage) {
						query.setFirstResult((currentPage - 1) * rowsPerPage);
						query.setMaxResults(rowsPerPage);
					}
					// 查询数据
					List list = query.list();
					// 查询数据转PageList
					PageList pageList = new PageList();
					boolean packageMap = false;
					if (hasScalars && CollectionUtils.isNotEmpty(list)) {
						// 查询结果封装List<Map>
						if (list.get(0).getClass().getName().indexOf('[') > -1
								&& scalarList.size() == ((Object[]) list.get(0)).length) {
							// 查询多列，list类型List<Object[]>，查询列数和scalarList一直则封装map返回
							List<Map> mapList = new ArrayList<Map>();
							for (Object[] objs : (List<Object[]>) list) {
								Map map = new HashMap();
								int i = 0;
								for (String scalar : scalarList) {
									map.put(scalar, objs[i++]);
								}
								mapList.add(map);
							}
							pageList.addAll(mapList);
							packageMap = true;
						} else if (scalarList.size() == 1) {
							// 查询单列，list类型List<Object>
							List<Map> mapList = new ArrayList<Map>();
							for (int i = 0; i < list.size(); i++) {
								Map map = new HashMap();
								map.put(scalarList.get(0), list.get(i));
								mapList.add(map);
							}
							pageList.addAll(mapList);
							packageMap = true;
						}
					}
					// 查询结果不封装，返回List<Object[]>或者List<Object>
					if (!packageMap) {
						pageList.addAll(list);
					}
					// 设置分页信息
					if (needPage) {
						pageList.setCurrentPage(page);
						pageList.setPageCount(pageCount);
						pageList.setTotalRowCount(rowCount);
					}
					return pageList;
				} catch (Exception e) {
					log.error(e, e);
				}
				return new PageList();
			}
		});
	}


}
