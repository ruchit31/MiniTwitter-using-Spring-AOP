package edu.sjsu.cmpe275.aop.aspect;

import java.awt.List;
import java.io.IOException;
import java.util.*;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import edu.sjsu.cmpe275.aop.TweetService;

@Aspect
public class RetryAspect {
	static ApplicationContext context1;
	public static int errorCount=0,errorCount1=0;
	String user="";
	String message="" ;
	
	

	/**
	 * This advice gets called if any error has occured.
	 @param  j  for getting details about a point in the execution of program.
	 * @throws Throwable 
	
	*/
	 @Around("execution (public void tweet(..))")
	    public void myAroundTweet(ProceedingJoinPoint j) throws Throwable { 
	      
		 try {
			 j.proceed();
		 } catch(IllegalArgumentException e1) {
			 System.out.println(e1);
		 } catch(IOException e2) {
			try {
				j.proceed();
			} catch(IOException e3) {
			try {
				j.proceed();
			} catch(IOException e4) {
					System.out.println(e4);
				}
			}
			 
		 }
	 }
		 
//		    TweetService t=(TweetService) j.getThis();
//			
//		   Object args [] =  (Object[]) ((JoinPoint) j).getArgs();
//		    user=(String) args[0];
//		    message=(String) args[1];
//		   // System.out.println(e.getClass()+"kaunsa exception");
//		    if(e.getClass().getName().equals("java.lang.IllegalArgumentException"))
//		    {
//		        System.out.print("Tweet lenght is more than 140 character");
//		        return;
//		    }
//
//		    
//		    else{
//		    	   
//		    	    if(errorCount<=2){
//					errorCount++;
//					 System.out.print("retrying count "+errorCount);
//// Retrying tweets. Will again call public void tweet() method  
//					 try{
//					t.tweet(user, message);
//					 }
//					catch(Exception e2){
//						
//					}
//		    	    }
//					 
//		    	    else
//		    	    {
//// Retrying tweets counter exceeded so it won't try anymore. 		    	    	
//		    	    	System.out.println("exceeded for tweet"+message);
//		    	    	errorCount=0;
//		    	    }
//		      
//		    }
//	    }


	 /**
	      *  This advice is being called when public void follow() throws either IOEXxception or IllegalArgumentException error.
		 * This function handles retry's if error has occured
		 @param  j  for getting details about a point in the execution of program. It gives details about target.
	 * @throws Throwable 
		*/
	 @Around("execution (public void follow (..))")
	    public void myAroundFollow(ProceedingJoinPoint j) throws Throwable { 
			
		 try {
			 j.proceed();
		 } catch(IllegalArgumentException e1) {
			 System.out.println(e1);
		 } catch(IOException e2) {
			try {
				j.proceed();
			} catch(IOException e3) {
			try {
				j.proceed();
			} catch(IOException e4) {
					System.out.println(e4);
				}
			}
			 
		 }
}
	 }