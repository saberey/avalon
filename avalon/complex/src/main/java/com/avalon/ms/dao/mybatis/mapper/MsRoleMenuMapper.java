package com.avalon.ms.dao.mybatis.mapper;

import com.avalon.ms.dao.entity.MsRoleMenu;
import java.util.List;

public interface MsRoleMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ms_role_menu
     *
     * @mbg.generated Tue Aug 29 14:00:23 CST 2017
     */
    int insert(MsRoleMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ms_role_menu
     *
     * @mbg.generated Tue Aug 29 14:00:23 CST 2017
     */
    List<MsRoleMenu> selectAll();
}