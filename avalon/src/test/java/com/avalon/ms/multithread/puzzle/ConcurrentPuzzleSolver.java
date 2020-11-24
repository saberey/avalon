package com.avalon.ms.multithread.puzzle;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月17日 下午5:13:07
 *@version
 */
public class ConcurrentPuzzleSolver<P,M> {

	private final Puzzle<P,M> puzzle;
	private final ExecutorService exec;
	private final ConcurrentMap<P,Boolean> seen;
	final ValueLatch<Node<P,M>> solution = new ValueLatch<>();
	
	public ConcurrentPuzzleSolver(Puzzle<P,M> puzzle,ExecutorService exec,ConcurrentHashMap<P,Boolean> concurrentHashMap){
		 this.puzzle = puzzle;
		 this.exec = exec;
		 this.seen = concurrentHashMap;
	}
	
	public List<M> solve() throws InterruptedException{
		try {
			P p = puzzle.initialPosition();
			exec.execute(newTask(p, null, null));
			Node<P,M> solnNode = solution.getValue();
			return solnNode == null ? null : solnNode.asMoveList();
		}  finally{
			exec.shutdown();
		}
	}
	
	protected Runnable newTask(P p,M m,Node<P,M> n){
		return new SolverTask(p,m,n);
	}
	
	class SolverTask extends Node<P,M> implements Runnable{
		
		SolverTask(P pos, M move, Node<P, M> prev) {
			super(pos, move, prev);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(solution.isSet()||seen.putIfAbsent(pos, true)!=null)
				return;
			if(puzzle.isGoal(pos))
				solution.setValue(this);
			else
				for(M m:puzzle.legalMoves(pos))
					exec.execute(newTask(puzzle.move(pos, m),m,this));
		}
	}
	
	public static void main(String[] args) {
		Date t = new Date(1510998717149l);
		System.out.println(t);
	}
}
