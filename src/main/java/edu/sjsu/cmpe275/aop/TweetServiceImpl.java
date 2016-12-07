package edu.sjsu.cmpe275.aop;

import java.io.IOException;
import java.util.Random;

import edu.sjsu.cmpe275.aop.aspect.RetryAspect;

public class TweetServiceImpl implements TweetService {
	
	

	public void tweet(String user, String message) throws IllegalArgumentException, IOException {
		// TODO Auto-generated method stub
		//System.out.println(user+message+"ruchit");
		
		if (message.length()>140)
			throw new IllegalArgumentException();
		
		
//		Random rand = new Random();
//
//		int  n = rand.nextInt(50) + 1;
//		System.out.println("value of n for tweet is"+n);
//		if(n>30)
	//    throw new IOException();
////		else
//			return;
		
		
			
		
		
	}

	public void follow(String follower, String followee) throws IOException {
		// TODO Auto-generated method stub
		Random rand = new Random();

		int  n = rand.nextInt(50) + 1;
		System.out.println("value of n  for follow is"+n);
//		if(n>30)
//	    throw new IOException();
//		
	}

}
