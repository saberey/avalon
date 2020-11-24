package com.avalon.ms.multithread;

import java.math.BigDecimal;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月22日 下午5:37:29
 *@version
 */
public class Account {

	Double balance;
	String accountNo;
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	public void debit(DollarAmount daAmount){
		BigDecimal org = new BigDecimal(balance);
		BigDecimal subtract = new BigDecimal(daAmount.getBalance());
		this.setBalance(org.subtract(subtract).doubleValue());
	}
	
	public void credit(DollarAmount daAmount){
		BigDecimal org = new BigDecimal(balance);
		BigDecimal addtions = new BigDecimal(daAmount.getBalance());
		this.setBalance(org.add(addtions).doubleValue());
	}
}
