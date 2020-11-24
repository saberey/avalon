package com.avalon.ms.multithread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.avalon.ms.multithread.synchronizer.BoundedBuffer;

import static org.junit.Assert.*;
/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月14日 下午3:02:56
 *@version
 */
public class PutTakeTest {

	private static final ExecutorService pool = Executors.newCachedThreadPool();
	private final AtomicInteger putSum = new AtomicInteger(0);
	private final AtomicInteger takeSum = new AtomicInteger(0);
	private final CyclicBarrier barrier;
	private final BoundedBuffer<Integer> bBuffer;
	private final int nTrials,nPairs;
	private final BarrierTimer barrierTimer = new BarrierTimer();
	
	public PutTakeTest(int capacity,int nPairs,int nTrials){
		this.bBuffer = new BoundedBuffer<Integer>(capacity);
		this.nTrials = nTrials;
		this.nPairs = nPairs;
		this.barrier = new CyclicBarrier(nPairs*2+1,barrierTimer);
	}
	//CyclicBarrier 增加时间统计
	public class BarrierTimer implements Runnable{
		private boolean started;
		private long startTime,endTime;
		
		@Override
		public synchronized void run() {
			// TODO Auto-generated method stub
			long t = System.nanoTime();
			if(!started){
				started = true;
				startTime = t;
			}else{
				endTime = t;
			}
		}
		
		public synchronized void clear(){
			started = false;
		}
		
		public synchronized long getTime(){
			return endTime - startTime;
		}
	}
	
	void test(){
		try {
			barrierTimer.clear();
			for (int i = 0; i < nPairs; i++) {
				pool.execute(new Producer());
				pool.execute(new Consumer());
			}
			barrier.wait();
			barrier.wait();
			long nsPerItem = barrierTimer.getTime()/(nPairs * (int)nTrials);
			System.out.println("Throughput: "+nsPerItem+" ns/item");
			assertEquals(putSum.get(),takeSum.get());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	static synchronized int xorShift(int y){
		y ^= y<<6;
		y ^= y>>>21;
		y ^= y<<7;
		return y;
	}
	
	public static void main(String[] args) {
		 new PutTakeTest(10, 10, 10).test();
		 pool.shutdown();
	}
	class Producer implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				int seed = (int) (this.hashCode() ^ System.nanoTime());
				int sum = 0;
				barrier.await();
				for (int i = nTrials; i >0; --i) {
					bBuffer.put(seed);
					sum +=seed;
					seed = xorShift(seed);
				}
				System.out.println(Thread.currentThread().getName()+" get previous "+putSum.getAndAdd(sum));
				barrier.await();
			} catch (Exception e) {
				// TODO: handle exception
				throw new RuntimeException(e);
			}
		}
	}
	
	class Consumer implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				barrier.await();
				int sum = 0;
				for (int i = nTrials; i >0; --i) {
					sum+=bBuffer.take();
				}
				System.out.println(Thread.currentThread().getName()+" get previous "+takeSum.getAndAdd(sum));
				barrier.await();
			} catch (Exception e) {
				// TODO: handle exception
				throw new RuntimeException(e);
			}
		}
	}
}
