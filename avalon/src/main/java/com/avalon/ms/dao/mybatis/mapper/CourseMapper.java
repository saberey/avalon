package com.avalon.ms.dao.mybatis.mapper;

import com.avalon.ms.dao.entity.Course;
import java.util.List;

public interface CourseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course
     *
     * @mbg.generated Mon Oct 09 11:14:09 CST 2017
     */
    int insert(Course record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course
     *
     * @mbg.generated Mon Oct 09 11:14:09 CST 2017
     */
    List<Course> selectAll();
}