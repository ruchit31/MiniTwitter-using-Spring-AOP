package edu.sjsu.cmpe275.aop;

public class TweetStatsImpl implements TweetStats {
	
	public static int lengthOfLongestTweet=0;
	public static String mostActiveFollower=null;
	public static String mostProductiveUser=null;
	
	
	

	

/**
 * Clears all data stored in hashmap.
*/
	public void resetStats() {
		// TODO Auto-generated method stub
		
	}

/**
 * Calculates length of longest tweet
 * @return returns lengthOfLongestTweet
*/
	public int getLengthOfLongestTweet() {
		
		// TODO Auto-generated method stub
		return lengthOfLongestTweet ;
	}

	/**
	 * Calculates mostActiveFollower
	 * @return returns string mostActiveFollower
	*/

	public String getMostActiveFollower() {
		// TODO Auto-generated method stub
		return mostActiveFollower;
	}


	/**
	 * Calculates getMostProductiveUser
	 * @return returns string getMostProductiveUser
	*/
	public String getMostProductiveUser() {
		// TODO Auto-generated method stub
		return mostProductiveUser;
	}

}
