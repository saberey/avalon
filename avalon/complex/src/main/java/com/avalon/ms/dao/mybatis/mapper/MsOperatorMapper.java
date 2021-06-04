package com.avalon.ms.dao.mybatis.mapper;

import com.avalon.ms.dao.entity.MsOperator;
import java.util.List;

public interface MsOperatorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ms_operator
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    int insert(MsOperator record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ms_operator
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    List<MsOperator> selectAll();
    
    MsOperator selectByUser(MsOperator msOperator);
    
    void deleteUser(int id);
    
    void Update(MsOperator msOperator);
}