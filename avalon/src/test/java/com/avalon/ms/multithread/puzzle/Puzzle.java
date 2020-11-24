package com.avalon.ms.multithread.puzzle;

import java.util.Set;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月17日 上午10:51:30
 *@version
 */
public interface Puzzle<P,M> {

	P initialPosition();
	boolean isGoal(P position);
	Set<M> legalMoves(P position);
	P move(P position,M move);
}
