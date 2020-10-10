package com.avalon.ms.dao.mybatis.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import com.avalon.ms.dao.mybatis.enums.CityAliasEnums;

@MappedTypes({String.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class CityTypeHandler implements TypeHandler<CityAliasEnums> {

	@Override
	public void setParameter(PreparedStatement ps, int i,
			CityAliasEnums parameter, JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		ps.setString(i, parameter.getCode());
	}

	@Override
	public CityAliasEnums getResult(ResultSet rs, String columnName)
			throws SQLException {
		// TODO Auto-generated method stub
		String code = rs.getString(columnName);
		System.out.println("code:"+code);
		return CityAliasEnums.getCityAlias(code);
	}

	@Override
	public CityAliasEnums getResult(ResultSet rs, int columnIndex)
			throws SQLException {
		// TODO Auto-generated method stub
		String code = rs.getString(columnIndex);
		System.out.println("code:"+code);
		return CityAliasEnums.getCityAlias(code);
	}

	@Override
	public CityAliasEnums getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		// TODO Auto-generated method stub
		String code = cs.getString(columnIndex);
		System.out.println("code:"+code);
		return CityAliasEnums.getCityAlias(code);
	}
}
