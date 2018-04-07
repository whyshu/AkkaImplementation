package Models;

/**
 * @author Prasanth, Vaishnavi
 *
 * Tweet Model Object to hold different properties of a Twitter Tweet
 */

public class Tweet {
    public String Username;
    public String screenName;
    public String Text;
    public String tweetTime;
    public int Retweets;
    public int favoriteCount;
    private String userImageUrl;

    /**
     * Tweet Model Constructor
     *
     * @param uName        user name information of the Tweet.
     * @param sName        screen name information of the Tweet.
     * @param tText        Tweet text.
     * @param rCount       Number of times a Tweet has been retweeted.
     * @param fCount       number of times a Tweet marked as favourite.
     * @param tweetTime    Tweet time
     * @param userImageUrl Url of the user profile who tweeted the Tweet.
     */
    public Tweet(String uName, String sName, String tText, int rCount, int fCount, String tweetTime, String userImageUrl) {
        this.Username = uName;
        this.screenName = sName;
        this.Text = tText;
        this.Retweets = rCount;
        this.favoriteCount = fCount;
        this.tweetTime = tweetTime;
        this.userImageUrl = userImageUrl;
    }

    /**
     * Gets the userName of the user who tweeted
     *
     * @return the name of the user
     */
    public String getUserName() {
        return this.Username;
    }

    /**
     * Gets the screen name of the user who tweeted
     *
     * @return the screen name of the user
     */
    public String getScreenName() {
        return this.screenName;
    }

    /**
     * Gets the Tweet text
     *
     * @return the Tweet text
     */
    public String getTweetText() {
        return this.Text;
    }


    /**
     * Gets the Retweet count
     *
     * @return the retweet count
     */
    public int getRetweetCount() {
        return this.Retweets;
    }


    /**
     * Gets the favourite count for the tweet
     *
     * @return the favourite count
     */
    public int getFavoriteCount() {
        return this.favoriteCount;
    }


    /**
     * Gets the profile image url of the tweeter
     *
     * @return the profile image url of the tweeter
     */
    public String getUserImageUrl() {
        return this.userImageUrl;
    }

    /**
     * Gets the Tweet information
     *
     * @return  the tweet info as a String
     */
    @Override
    public String toString() {
        return this.getUserName() + " :: " + this.getScreenName() + " :: " + this.getTweetText() +
                " :: " + this.getRetweetCount() + " :: " + this.getFavoriteCount() + " :: " + this.tweetTime;
    }

}
