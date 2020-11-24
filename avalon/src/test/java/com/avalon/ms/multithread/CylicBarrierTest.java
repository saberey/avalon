package com.avalon.ms.multithread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月13日 下午5:18:30
 *@version
 */
public class CylicBarrierTest {

	private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
	
	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					cyclicBarrier.await();
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					System.out.println(1);
				}
			}
		}).start();
		
		try{
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			System.out.println(2);
		}
	}
}
