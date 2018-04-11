package com.avalon.ms.enums;

public enum CalcOperator {
	
	PLUS("+",'+'),
	SUBTRACT("-",'-'),
	MULTIPLY("*",'*'),
	DIVIDE("/",'/');
	
	private String oper;
	private char operator;
	
	private CalcOperator(String oper,char operator){
		this.oper = oper;
		this.operator = operator;
	}
	
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public char getOperator() {
		return operator;
	}
	public void setOperator(char operator) {
		this.operator = operator;
	}
}
