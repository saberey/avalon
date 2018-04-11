package com.avalon.ms.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

import com.avalon.ms.common.annotation.table.Column;
import com.avalon.ms.common.annotation.table.Id;
import com.avalon.ms.common.annotation.table.Table;
import com.avalon.ms.common.support.ColumnTypeEnum;
import com.avalon.ms.common.table.People;

/**
 * create table by annotation
 * @description:TODO
 * @author: saber
 * @time: 2017年7月25日 下午5:27:35
 * @version 1.0
 */
public class TableUtil {
	
	
	
	public <T> String getTableName(Class<T> c){
		String tableName="";
		if(c.isAnnotationPresent(Table.class)){
			tableName = c.getAnnotation(Table.class).tableName();
		}
		return tableName;
	}
	
	
	public <T> ArrayList<Field> getColumnList(Class<T> c){
		
		ArrayList<Field> al = new ArrayList<Field>();
		Field[] fields = c.getDeclaredFields();
		for(Field f : fields){
			Column column = f.getAnnotation(Column.class);
			if(column!=null){
				al.add(f);
			}
		}
		return al;
	}
	
	/**
     * 开始创建建表语句
     */
    protected <T> String createSql(Class<T> c) {
        // 表名
        String tableName = getTableName(c);
        // 创建建表语句
        StringBuilder sql = new StringBuilder("CREATE TABLE " + tableName
                + " (");
        // 字段集合
        ArrayList<String> columns = new ArrayList<String>();
        // 带注释的Field集合
        ArrayList<Field> list = getColumnList(c);
        try {
            for (Field mField : list) {
                // 字段名
                String name = null;
                // 类型
                String type = null;
                Annotation[] mAnnotations = mField.getDeclaredAnnotations();
                if (mAnnotations.length == 0) {
                    continue;
                } else {
                    // 获取注解信息
                    Column mColumn = (Column) mAnnotations[0];
                    // 获得sql需要的别名
                    String nameC = mColumn.columnName();
                    // 获取字段的类型
                    //String typeC = mField.getType().getName();
                    ColumnTypeEnum typeC = mColumn.type();
                    // 获取注解里的长度
                    String Clength = mColumn.length();
                    // 是否是TEXT格式
                    boolean isBig = mColumn.isBigString();
                    if (nameC.length() == 0) {
                        // 获得默认的字段名
                        name = mField.getName();
                    } else {
                        name = nameC;
                    }
                    
                    //else {
                        //type = Types.getType(typeC, isBig);
                   // }
                    // 没有设置长度 则获取默认的长度
                    if (Clength.length() <=0) {
                        Clength = String.valueOf(typeC.getValue());
                    }
                 
                    // 生成字段类型和长度sql语句
                    type = getTypeSql(typeC.getName(), Clength);
                    // 拼接成sql语句格式 加入集合
                    columns.add(name + " " + type);
                }
            }
            // 加入ID
            sql.append(getIdSql(c));
            // 遍历集合 生成sql语句
            for (String str : columns) {
                sql.append(" " + str + " ,");
            }
            // 最终的创建表语句
            String finalSql = sql.substring(0, sql.length() - 1) + " );";
            return finalSql;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    	}
    public String getTypeSql(String type,String length){
    	return type+"("+length+")";
    }
    	
    
     public <T> String getIdSql(Class<T> c){
    	 String idSql="";
    	 Field[] fields = c.getDeclaredFields();
    	 for(Field field:fields){
    		Id id =  field.getAnnotation(Id.class);
    		if(id!=null){
    			boolean isIncrement = id.increment();
    			String name = id.idName();
    			if(isIncrement){
    				idSql = name+" int primary key auto_increment,";
    			}else{
    				idSql = name+" int ,";
    			}
    		}
    	 }
    	 return idSql;
     }
     
     public static void main(String[] args) {
		TableUtil tableUtil = new TableUtil();
		System.out.println(tableUtil.createSql(People.class));
	}
}
