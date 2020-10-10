package com.avalon.ms.advice;

import java.time.LocalDateTime;


import com.avalon.ms.common.util.TimeOperatorUtil;

/**
 *@description:TODO
 *@author saber
 *@date 2018年1月8日 上午11:52:58
 *@version
 */
public class StackInfoAdvice {

	 private LocalDateTime start;
	 private LocalDateTime end;
	
     public void doBefore(){
    	 start = TimeOperatorUtil.getLocalDateTimeNow();
    	 System.out.println("do action before ,time:"+start);
    	 printStack();
     }
     
     public void doAfter(){
    	 end = TimeOperatorUtil.getLocalDateTimeNow();
    	 System.out.println(java.text.MessageFormat.format("starttime:{0}|endtime:{1}|elaspedtime:{2}", start,end,TimeOperatorUtil.getDuration(start, end)));
    	 printStack();
     }
     
     public void printStack(){
    	 StackTraceElement[] sTraceElements = Thread.currentThread().getStackTrace();
    	 if(sTraceElements.length<=0) {
			 return;
		 }
    	 StringBuffer stringBuffer = new StringBuffer();
    	 for(StackTraceElement sTraceElement:sTraceElements){
    		 stringBuffer.append(" < - ");
    		 stringBuffer.append(System.getProperty("line.separator"));
    		 stringBuffer.append(java.text.MessageFormat.format("{0}.{1} {2}", 
    				 sTraceElement.getClassName(),sTraceElement.getMethodName(),sTraceElement.getLineNumber()));
    	 }
    	 System.out.println(stringBuffer.toString());
     }
}
