package edu.sjsu.cmpe275.aop.aspect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import edu.sjsu.cmpe275.aop.TweetStatsImpl;

@Aspect 
public class StatsAspect {
	
	public static int longestTweet=0;
	Scanner sc=new Scanner (System.in);
	
static TreeMap <String, ArrayList> tm=new TreeMap <String, ArrayList>();
static TreeMap <String, ArrayList> tm1=new TreeMap <String, ArrayList>();

/**
 * Saves data for user's tweet, If there is not any error while posting tweets.
 @param  jointpoint  for getting details about a point in the execution of program. It gives details about target.
*/
@AfterReturning("execution (public void tweet(..))")
public void afterTweet (JoinPoint jointpoint){
	RetryAspect.errorCount=0;
	System.out.println("In aftewr return");
	   Object args [] =  (Object[]) jointpoint.getArgs();
	   String user=(String) args[0];
	   String message=(String) args[1];
	   
	//System.out.println("before tweet"+user+"haha "+message);
	if(tm.containsKey(user)){
    tm.get(user).add(message);
	}
	else{
		
	    ArrayList<String> row = new ArrayList<String>(1);	
	    row.add(message);
	    tm.put(user,row);
	}
	
   }


/**
 * Computes longest tweet's length.
 @param  joinpoint  for getting details about a point in the execution of program. It gives details about target.
*/
@Before("execution (public int getLengthOfLongestTweet())")
public void longestTweet(JoinPoint joinpoint){
	
	    int max2=0,longestTweet=0;
	   for (Entry<String, ArrayList> entry : tm.entrySet()) {
		    String key = entry.getKey();
		    ArrayList<String> words = entry.getValue();
		    for(String tweet:words)
		    if(tweet.length()>max2){
		    	max2=tweet.length();
		    	longestTweet=max2;
		    }
		    
	   }
	   
	   TweetStatsImpl.lengthOfLongestTweet=longestTweet;
	
}



/**
 * Calculates the most Productive user
*/ 
@Before("execution (public String getMostProductiveUser(..) )")
public void getMostProductiveUser() {
	   // Get a set of the entries
//    Set set = tm.entrySet();
//    Iterator i = set.iterator();

   int max1=0,sum=0;
	String productiveUser=null;
	for (Entry<String, ArrayList> entry : tm.entrySet()) {
	    String key = entry.getKey();
	    ArrayList<String> value = entry.getValue();
	    for(String s : value){
	       sum+=s.length();
	    }
	    if(sum>max1){
	    	productiveUser=key;
	    	max1=sum;
	    
	    	
	    }
		sum=0;
	}
	TweetStatsImpl.mostProductiveUser=productiveUser;
//	System.out.println("in advice of productiveUser"+	TweetStatsImpl.mostProductiveUser);
	
}


/**
 * calculates the most Active Follower
*/
@Before("execution (public String getMostActiveFollower(..) )")
public void getMostActiveFollower() {

   int max1=0;
	String activefollower=null;
	
	for (Entry<String, ArrayList> entry : tm1.entrySet()) {
	    String key = entry.getKey();
	    ArrayList<String> value = entry.getValue();
	   
	    if(value.size()>max1)
	      {
	    	activefollower=key;
	    	max1=value.size();
	      }
		
	}
	TweetStatsImpl.mostActiveFollower=activefollower;
	//System.out.println("in advice of productiveUser"+	TweetStatsImpl.mostActiveFollower);
	
}







/**
 * Saves data for follower and followee
 @param  joinpoint  for getting details about a point in the execution of program. It gives details about target.
*/
@Before("execution (public void follow(..))")
public void beforeFollow(JoinPoint joinpoint){
      
	//System.out.println("saving follow");
	  Object args [] =  (Object[]) joinpoint.getArgs();
	   String follower=(String) args[0];
	   String followee=(String) args[1];
	   
		if(tm1.containsKey(follower)){
      if(!tm1.get(follower).contains(followee)){ 
    	  //checks whether a followee is duplicate or not
//    	  System.out.print(followee+" tp");
    	  tm1.get(follower).add(followee);
    	  
   	    }
			}
			else{
			    ArrayList<String> row = new ArrayList<String>(1);	
			    row.add(followee);
			    tm1.put(follower,row);
			}
		
			   
			 
}
/**
 * Clears all data .
 @param  joinpoint  for getting details about a point in the execution of program. It gives details about target.
*/
@After("execution (public void resetStats()) ")
public void clearData(JoinPoint joinpoint){
tm.clear();
tm1.clear();
TweetStatsImpl.lengthOfLongestTweet=0;
TweetStatsImpl.mostActiveFollower=null;
TweetStatsImpl.mostProductiveUser=null;
}
}