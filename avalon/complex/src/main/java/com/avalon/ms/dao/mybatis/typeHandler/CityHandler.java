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

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年10月31日 下午3:09:48
 *@version
 */
@MappedTypes({CityAliasEnums.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class CityHandler implements TypeHandler<CityAliasEnums> {

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
		System.out.println(1);
		String code = rs.getString(columnName);
		return CityAliasEnums.getCityAlias(code);
	}

	@Override
	public CityAliasEnums getResult(ResultSet rs, int columnIndex)
			throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(1);
		String code = rs.getString(columnIndex);
		return CityAliasEnums.getCityAlias(code);
	}

	@Override
	public CityAliasEnums getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(1);
		String code = cs.getString(columnIndex);
		return CityAliasEnums.getCityAlias(code);
	}

}
