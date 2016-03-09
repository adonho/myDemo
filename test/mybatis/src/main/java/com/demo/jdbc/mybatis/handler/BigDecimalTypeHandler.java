package com.demo.jdbc.mybatis.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by adon on 2016/2/1 0001.
 */
@MappedJdbcTypes(JdbcType.DECIMAL)
public class BigDecimalTypeHandler extends BaseTypeHandler<BigDecimal> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, BigDecimal parameter, JdbcType jdbcType) throws SQLException {
		ps.setBigDecimal(i, parameter);
	}

	@Override
	public BigDecimal getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getBigDecimal(columnName);

	}

	@Override
	public BigDecimal getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getBigDecimal(columnIndex);

	}

	@Override
	public BigDecimal getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getBigDecimal(columnIndex);
	}
}
