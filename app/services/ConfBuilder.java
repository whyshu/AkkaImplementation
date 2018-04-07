package services;

import twitter4j.conf.ConfigurationBuilder;

/**
 * Represents ConfBuilder.
 * @author Vaishnavi
 * @version 1.0
 * @since 1.0
 */
public class ConfBuilder {

	private ConfigurationBuilder cb;

	/**
	 * Instantiates the twitter4j Configuration Builder class and sets extended tweet mode
	 */
	public ConfBuilder() {
		cb = new ConfigurationBuilder();
		cb.setTweetModeExtended(true); 
	}

	/**
	 * Gets the initialised ConfigurationBuilder instance with the provided Twitter account details 
	 * @return ConfigurationBuilder an instance of the twitter4j ConfigurationBuilder class
	 */
	public ConfigurationBuilder getConf() {
		cb.setDebugEnabled(true).setOAuthConsumerKey(AccountInformation.CONSUMER_KEY)
				.setOAuthConsumerSecret(AccountInformation.CONSUMER_KEY_SECRET)
				.setOAuthAccessToken(AccountInformation.ACCESS_TOKEN)
				.setOAuthAccessTokenSecret(AccountInformation.ACCESS_TOKEN_SECRET)
				.setTweetModeExtended(true); 
		return cb;
	}
}
