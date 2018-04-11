package com.avalon.ms.multithread;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *@description:TODO
 *@author saber
 *@date 2017年11月15日 下午7:51:32
 *@version
 */
public abstract class WebCrawler {

	private volatile TrackingExecutor exec;
	private final Set<URL> urlsToCrawl = new HashSet<>();
	private static final int TIMEOUT = 1000;
	private static final TimeUnit UNIT = TimeUnit.SECONDS;
	
	public synchronized void start(){
		exec = new TrackingExecutor(Executors.newCachedThreadPool());
		for(URL url:urlsToCrawl) submitCrawTask(url);
		urlsToCrawl.clear();
	}
	
	public synchronized void stop(){
		try {
			saveUncrawled(exec.shutdownNow());
			if(exec.awaitTermination(TIMEOUT, UNIT))
				saveUncrawled(exec.getCancelledTasks());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			exec = null;
		}
	}
	
	protected abstract List<URL> processPage(URL url);
	
	private void saveUncrawled(List<Runnable> cancelledTasks) {
		// TODO Auto-generated method stub
		for(Runnable task:cancelledTasks)
			urlsToCrawl.add(((CrawlTask) task).getPage());
	}

	private void submitCrawTask(URL url) {
		// TODO Auto-generated method stub
		exec.execute(new CrawlTask(url));
	}
	
	private class CrawlTask implements Runnable{
		
		private final URL url;
		public CrawlTask(URL url){
			this.url = url;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(URL link:processPage(url)){
				if(Thread.currentThread().isInterrupted())
					return;
				submitCrawTask(link);
			}
		}
		
		public URL getPage(){
			return url;
		}
	}
}
