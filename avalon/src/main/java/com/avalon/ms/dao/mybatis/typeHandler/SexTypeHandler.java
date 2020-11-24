package com.avalon.ms.dao.mybatis.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import com.avalon.ms.dao.mybatis.enums.SexEnums;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年10月9日 下午1:17:49
 *@version
 */
@MappedTypes({SexEnums.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class SexTypeHandler implements TypeHandler<SexEnums>{

	@Override
	public void setParameter(PreparedStatement ps, int i, SexEnums parameter,
			JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		ps.setString(i, String.valueOf(parameter.getType()));
	}

	@Override
	public SexEnums getResult(ResultSet rs, String columnName)
			throws SQLException {
		// TODO Auto-generated method stub
		String type = rs.getString(columnName);
		return SexEnums.getSexEnums(Integer.parseInt(type));
	}

	@Override
	public SexEnums getResult(ResultSet rs, int columnIndex)
			throws SQLException {
		// TODO Auto-generated method stub
		String type = rs.getString(columnIndex);
		return SexEnums.getSexEnums(Integer.parseInt(type));
	}

	@Override
	public SexEnums getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		// TODO Auto-generated method stub
		String type = cs.getString(columnIndex);
		return SexEnums.getSexEnums(Integer.parseInt(type));
	}
}
