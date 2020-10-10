package com.avalon.ms.common.table;

import com.avalon.ms.common.annotation.table.Column;
import com.avalon.ms.common.annotation.table.Id;
import com.avalon.ms.common.annotation.table.Table;
import com.avalon.ms.common.support.ColumnTypeEnum;

import java.io.Serializable;

@Table(tableName="people")
public class People implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(columnName="name",length="16",type=ColumnTypeEnum.VARCHAR)
	private String name;
	
	@Column(columnName="age",length="3",type=ColumnTypeEnum.INT)
	private Integer age;
	
	@Column(columnName="weight",length="5",type=ColumnTypeEnum.FLOAT)
	private float weight;
	
	@Id(increment=true,idName="id")
	private Integer id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
	//	return super.toString();
		return "People [name="+name+",age="+age+",weight="+weight+",id="+id+"]";
	}
}
