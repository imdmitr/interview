package com.interview.others;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorThreadExample {

	public static void main(String[] args) {
		try {
			ExecutorThreadExample example = new ExecutorThreadExample();
			example.doTest1();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	private void doTest2() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newWorkStealingPool();

		List<Callable<String>> callables = Arrays.asList(
		    callable("task1", 2),
		    callable("task2", 1),
		    callable("task3", 3));

		List<Future<String>> result = executor.invokeAll(callables);
		
//		String result = executor.invokeAny(callables);
//		System.out.println(result);
		
	}
	
	Callable<String> callable(String result, long sleepSeconds) {
	    return () -> {
	        TimeUnit.SECONDS.sleep(sleepSeconds);
	        return result;
	    };
	}	

	private void doTest1() throws InterruptedException, ExecutionException {

		Callable<Long> task = () -> {
		    try {
		    	System.out.println(Thread.currentThread().getName());
		        TimeUnit.SECONDS.sleep(1);
		        return System.currentTimeMillis();
		    }
		    catch (InterruptedException e) {
		        throw new IllegalStateException("task interrupted", e);
		    }
		};		
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		Future<Long> future = executor.submit(task);
		Future<Long> future2 = executor.submit(task);

		System.out.println("future done? " + future.isDone());

		Long result = future.get();

		System.out.println("future done? " + future.isDone());
		System.out.println("result: " + result);
		System.out.println("result: " + future2.get());
		
	}
}
