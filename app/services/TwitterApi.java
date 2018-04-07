package services;

import Models.Profile;
import Models.Tweet;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents TwitterApi.
 * @author Prasanth, Vaishnavi, Anil, Simarpreet
 *
 * @version 1.0
 * @since 1.0
 */
public class TwitterApi {

	private Twitter twitterInstance;

	/**
	 * Instantiates the twitter4js Configuration builder with the available twitter
	 * account information Creates the twitter instance to issue the Rest API
	 */
	public TwitterApi() {
		ConfBuilder confBuilder = new ConfBuilder();
		ConfigurationBuilder cb = confBuilder.getConf();
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitterInstance = tf.getInstance();
	}

	/**
	 * Sets the initialised instance of the twitter instance
	 *
	 * @param twitter An instance
	 *            of the Twitter class
	 */
	public void SetTwitterInstance(Twitter twitter) {
		this.twitterInstance = twitter;
	}

	/**
	 * Gets the initialised instance of the twitter instance
	 *
	 * @return An instance of the Twitter class
	 */
	public Twitter getTwitterInstance() {
		return this.twitterInstance;
	}

	/**
	 * Gets the list of ten latest tweets queried based on the given search key
	 *
	 * @param searchKey Key based on which tweets should be fetched 
	 *            
	 *
	 * @return ArrayList<Tweet>, array list containing instances of the Tweet class
	 * @link Tweet
	 * 
	 * @throws     whenever
	 *             the twitter instance is not instantiated with the given Twitter
	 *             account information
	 */
	public String getTweets(String searchKey) {
		try {
			Query query = new Query(searchKey);
			QueryResult result = getTwitterInstance().search(query);
			List<Status> tweets = result.getTweets();
			tweets = tweets.parallelStream().limit(10).collect(Collectors.toList());
			return convertToTweets(tweets);
		}catch(Exception e) {
			return "";
		}
	}

	/**
	 * Converts the list of tweets of Status class type to Tweet class
	 * 
	 * @param tweets  list containing instances of twitter4j Status class
	 * 
	 * @return ArrayList<Tweet>, array list containing instances of Tweet class
	 * @link Tweet
	 * @throws     whenever
	 *             the twitter instance is not instantiated with the given Twitter
	 *             account information
	 */
	public String convertToTweets(List<Status> tweets)throws Exception {
			ArrayList<Tweet> tweetStatus = new ArrayList<>();
			for (Status tweet : tweets) {
				Status status = getTwitterInstance().showStatus(tweet.getId());
	
				int favorites = (status.isRetweet()) ? status.getRetweetedStatus().getFavoriteCount()
						: status.getFavoriteCount();
				String text = (status.isRetweet()) ? status.getRetweetedStatus().getText() : tweet.getText();
	
				Tweet t = new Tweet(tweet.getUser().getName(), tweet.getUser().getScreenName(), text,
						tweet.getRetweetCount(), favorites, tweet.getCreatedAt().toString(),
						tweet.getUser().getBiggerProfileImageURL());
				tweetStatus.add(t);
			}
			String output = " ";
			for(Tweet tweet : tweetStatus){
				output = output + "|||||||||||||" + tweet.toString();
			}
			return output;
	}

	/**
	 * Gets the instance of profile based on the provided username
	 * 
	 * @param userName  name of the user
	 *            
	 * @return Profile an instance of the Profile class
	 * @link Profile
	 * @throws 	   whenever
	 *             the twitter instance is not instantiated with the given Twitter
	 *             account information
	 */
	public Profile getProfile(String userName){
		try {
			User userDetail = null;
	
			userDetail = getTwitterInstance().showUser(userName);
			Profile profile = new Profile(userDetail.getName(), userDetail.getScreenName(), userDetail.getFollowersCount(),
					userDetail.getFriendsCount(), userDetail.getLocation(), userDetail.getStatusesCount(),
					userDetail.getOriginalProfileImageURL());
			return profile;
		}catch(Exception e) {
			return new Profile();
		}
	}

	/**
	 * Gets the latest ten tweets of a twitter user with the provided username
	 * 
	 * @param userName  name of the user
	 *            
	 * @return ArrayList<Tweet>, list containing instances of the Tweet class
	 * @link Tweet
	 * @throws     whenever
	 *             the twitter instance is not instantiated with the given Twitter
	 *             account information
	 */
	public String getUserTweets(String userName)  {
		try {
			List<Status> tweets = getTwitterInstance().getUserTimeline(userName);
			tweets = tweets.stream().limit(10).collect(Collectors.toList());
			return convertToTweets(tweets);
		}catch(Exception e) {
			return "";
		}
	}
}
