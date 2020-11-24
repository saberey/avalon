package com.avalon.ms.multithread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月15日 下午5:54:32
 *@version
 */
public class TrackingExecutor extends AbstractExecutorService {
	
	private final ExecutorService exec;
	private final Set<Runnable> tasksCancelledAtShutdown =
			Collections.synchronizedSet(new HashSet<>());
	
	public TrackingExecutor(ExecutorService exec){
		this.exec = exec;
	}
	
	public List<Runnable> getCancelledTasks(){
		if(!exec.isTerminated())
			throw new IllegalStateException("");
		return new ArrayList<Runnable>(tasksCancelledAtShutdown);
	}
	
	
	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		exec.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		// TODO Auto-generated method stub
		return exec.shutdownNow();
	}

	@Override
	public boolean isShutdown() {
		// TODO Auto-generated method stub
		return exec.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return exec.isTerminated();
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return exec.awaitTermination(timeout, unit);
	}

	@Override
	public void execute(Runnable command) {
		// TODO Auto-generated method stub
		exec.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					command.run();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					if(isShutdown()&&Thread.currentThread().isInterrupted())
						tasksCancelledAtShutdown.add(command);
				}
			}
		});
	}
}
