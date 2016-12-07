package edu.sjsu.cmpe275.aop;

import java.io.IOException;

public interface TweetService {

public void tweet(String user, String message) throws IllegalArgumentException, IOException;

public void follow(String follower, String followee) throws IOException;


}
