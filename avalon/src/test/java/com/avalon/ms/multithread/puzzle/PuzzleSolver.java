package com.avalon.ms.multithread.puzzle;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *@description:TODO
 *@author saber
 *@date 2017年11月20日 下午4:59:44
 *@version
 */
public class PuzzleSolver<P,M> extends ConcurrentPuzzleSolver<P, M> {

	private final AtomicInteger taskCount = new AtomicInteger();
	
	public PuzzleSolver(Puzzle<P, M> puzzle, ExecutorService exec,
			ConcurrentHashMap<P, Boolean> concurrentHashMap) {
		super(puzzle, exec, concurrentHashMap);
		// TODO Auto-generated constructor stub
	}

	class CountingSolverTask extends SolverTask{

		CountingSolverTask(P pos, M move, Node<P, M> prev) {
			super(pos, move, prev);
			taskCount.incrementAndGet();
			// TODO Auto-generated constructor stub
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				super.run();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if(taskCount.decrementAndGet()==0)
					solution.setValue(null);
			}
		}
	}
}
