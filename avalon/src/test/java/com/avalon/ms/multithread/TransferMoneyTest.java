package com.avalon.ms.multithread;

import javax.naming.InsufficientResourcesException;

/**
 *@description:TODO
 *@author saber
 *@date 2017年11月22日 下午5:35:43
 *@version
 */
public class TransferMoneyTest {

	private static final Object tieLock = new Object();
	
	public void transferMoney(final Account fromAcct,final Account toAcct,final DollarAmount amount)
		throws InsufficientResourcesException{
		
		class Hellper{
			public void transfer() throws InsufficientResourcesException{
				if(fromAcct.getBalance().compareTo(amount.getBalance())<0){
					throw new InsufficientResourcesException();
				}else{
					fromAcct.debit(amount);
					toAcct.credit(amount);
				}
			}
		}
		int fromHash = System.identityHashCode(fromAcct);
		int toHash = System.identityHashCode(toAcct);
		
		if(fromHash<toHash){
			synchronized (fromAcct) {
				synchronized (toAcct) {
					new Hellper().transfer();
				}
			}
		}else if(fromHash > toHash){
			synchronized (toAcct) {
				synchronized (fromAcct) {
					new Hellper().transfer();
				}
			}
		}else{
			synchronized (tieLock) {
				synchronized (fromAcct) {
					synchronized (toAcct) {
						new Hellper().transfer();
					}
				}
			}
		}
	}
}
