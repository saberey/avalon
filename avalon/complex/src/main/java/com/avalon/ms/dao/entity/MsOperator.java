package com.avalon.ms.dao.entity;

import java.sql.Timestamp;
import java.util.Date;

public class MsOperator extends BaseEntity{
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ms_operator.id
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ms_operator.operatorcode
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    private String operatorcode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ms_operator.operatorname
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    private String operatorname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ms_operator.operatorpwd
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    private String operatorpwd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ms_operator.departmentid
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    private Integer departmentid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ms_operator.groupid
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    private Integer groupid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ms_operator.isactive
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    private String isactive;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ms_operator.createtime
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    private Timestamp createtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ms_operator.creatorcode
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    private String creatorcode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ms_operator.id
     *
     * @return the value of ms_operator.id
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ms_operator.id
     *
     * @param id the value for ms_operator.id
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ms_operator.operatorcode
     *
     * @return the value of ms_operator.operatorcode
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public String getOperatorcode() {
        return operatorcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ms_operator.operatorcode
     *
     * @param operatorcode the value for ms_operator.operatorcode
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public void setOperatorcode(String operatorcode) {
        this.operatorcode = operatorcode == null ? null : operatorcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ms_operator.operatorname
     *
     * @return the value of ms_operator.operatorname
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public String getOperatorname() {
        return operatorname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ms_operator.operatorname
     *
     * @param operatorname the value for ms_operator.operatorname
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public void setOperatorname(String operatorname) {
        this.operatorname = operatorname == null ? null : operatorname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ms_operator.operatorpwd
     *
     * @return the value of ms_operator.operatorpwd
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public String getOperatorpwd() {
        return operatorpwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ms_operator.operatorpwd
     *
     * @param operatorpwd the value for ms_operator.operatorpwd
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public void setOperatorpwd(String operatorpwd) {
        this.operatorpwd = operatorpwd == null ? null : operatorpwd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ms_operator.departmentid
     *
     * @return the value of ms_operator.departmentid
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public Integer getDepartmentid() {
        return departmentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ms_operator.departmentid
     *
     * @param departmentid the value for ms_operator.departmentid
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ms_operator.groupid
     *
     * @return the value of ms_operator.groupid
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public Integer getGroupid() {
        return groupid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ms_operator.groupid
     *
     * @param groupid the value for ms_operator.groupid
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ms_operator.isactive
     *
     * @return the value of ms_operator.isactive
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public String getIsactive() {
        return isactive;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ms_operator.isactive
     *
     * @param isactive the value for ms_operator.isactive
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public void setIsactive(String isactive) {
        this.isactive = isactive == null ? null : isactive.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ms_operator.createtime
     *
     * @return the value of ms_operator.createtime
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public Timestamp getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ms_operator.createtime
     *
     * @param createtime the value for ms_operator.createtime
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ms_operator.creatorcode
     *
     * @return the value of ms_operator.creatorcode
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public String getCreatorcode() {
        return creatorcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ms_operator.creatorcode
     *
     * @param creatorcode the value for ms_operator.creatorcode
     *
     * @mbg.generated Tue Aug 29 13:39:43 CST 2017
     */
    public void setCreatorcode(String creatorcode) {
        this.creatorcode = creatorcode == null ? null : creatorcode.trim();
    }
    
}