package com.avalon.ms.dao.mybatis.mapper;

import com.avalon.ms.dao.entity.Coursegrade;
import java.util.List;

public interface CoursegradeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coursegrade
     *
     * @mbg.generated Mon Oct 09 11:15:09 CST 2017
     */
    int insert(Coursegrade record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coursegrade
     *
     * @mbg.generated Mon Oct 09 11:15:09 CST 2017
     */
    List<Coursegrade> selectAll();
    
    
    List<Coursegrade> selectByStudentNo(String student_no);
}