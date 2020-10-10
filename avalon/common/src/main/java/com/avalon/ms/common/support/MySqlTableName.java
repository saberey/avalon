package com.avalon.ms.common.support;

public enum MySqlTableName {

	MSOPERATOR("ms_operator","用户表"),
	MSOPERATORROLE("ms_operator_role","用户角色"),
	MSROLE("ms_role","角色"),
	MSROLEMENU("ms_role_menu","角色菜单"),
	MSMENU("ms_menu","菜单");
	
	private String tableName;
	private String tableDesc;
	
	private MySqlTableName(String tableName,String tableDesc){
		this.tableName = tableName;
		this.tableDesc = tableDesc;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableDesc() {
		return tableDesc;
	}

	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}
}
