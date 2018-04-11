package com.avalon.ms.java8;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *@description:TODO
 *@author saber
 *@date 2017年9月25日 下午2:57:03
 *@version
 */
public class StreamTest {

	private enum Status{
		OPEN,CLOSED;
	}
	
	private static final class Task{
		private final Status status;
		private final Integer points;
		
		Task(final Status status,final Integer points){
			this.status = status;
			this.points = points;
		}
		
		public Status getStatus(){
			return status;
		}
		
		public Integer getPoints(){
			return points;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			//return super.toString();
			return String.format("[%s,%d]", status,points);
		}
	}
	
	public static void main(String[] args) {
		final Collection<Task>  tasks  = Arrays.asList(
				new Task(Status.OPEN,5),
				new Task(Status.OPEN,13),
				new Task(Status.CLOSED,10));
		final long totalPointsOfOpenTasks = tasks.stream()
				.filter( task -> task.getStatus() == Status.OPEN)
				.mapToInt( Task :: getPoints)
				.sum();
		System.out.println("total points : "+totalPointsOfOpenTasks);
		
		System.out.println(tasks);
		final Map<Status,List<Task>> map = tasks.stream()
				.collect(Collectors.groupingBy( Task :: getStatus));
		System.out.println(map);
		final double totalPoints = tasks.stream()
				.parallel()
				.map(task -> task.getPoints())
				.reduce(0, Integer::sum);
		System.out.println("Total points (all tasks):"+totalPoints);
		
		final Collection<String> results = tasks.stream()
				.mapToInt( Task::getPoints)
				.asLongStream()
				.mapToDouble( points -> points/totalPoints)
				.boxed()
				.mapToLong( weigh -> (long)(weigh*100))
				.mapToObj( percentage -> percentage +"%")
				.collect(Collectors.toList());
		System.out.println(results);
	}
}
