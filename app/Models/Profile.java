package Models;

/**
 *
 * @author Prasanth, Vaishnavi
 *
 * Profile Model Object to hold various properties of the User information
 */
public class Profile {

    private String userName;
    private String screenName;
    private int followersCount;
    private int friendsCount;
    private String location;
    private int tweets;
    private String profileImageURL;

    /**
     * Default constructor to instantiate the properties of the Profile
     * with Empty strings
     */
    public Profile() {
        this("", "", 0, 0, "", 0, "");
    }

    /**
     * Profile Model Constructor
     *
     * @param uName           User name of the profile.
     * @param sName           Screen name of the profile.
     * @param followers       Follower count of the user.
     * @param friends         Number of friends of the user.
     * @param loc             Country to which the user belongs.
     * @param tweets          Number of tweets tweeted by the user.
     * @param profileImageUrl Profile ImageUrl
     */
    public Profile(String uName, String sName, int followers, int friends, String loc, int tweets, String profileImageUrl) {
        this.userName = uName;
        this.screenName = sName;
        this.followersCount = followers;
        this.friendsCount = friends;
        this.location = loc;
        this.tweets = tweets;
        this.profileImageURL = profileImageUrl;
    }

    /**
     * Gets the user name
     *
     * @return the user name String
     */
    public String getUserName() {
        return this.userName;
    }


    /**
     * Gets the screen name
     *
     * @return the screen name of the user
     */
    public String getScreenName() {
        return this.screenName;
    }

    /**
     * Gets the followers Count of the users
     *
     * @return the follower count of the user
     */
    public int getFollowersCount() {
        return this.followersCount;
    }


    /**
     * Gets the FriendCount of the user
     *
     * @return the friends count
     */
    public int getFriendsCount() {
        return this.friendsCount;
    }

    /**
     * Gets the location of the user
     *
     * @return the location of the user
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Gets the tweets count
     *
     * @return the tweet count
     */
    public int getTweetsCount() {
        return this.tweets;
    }

    /**
     * Gets the profile image URL
     *
     * @return the url of the profile image
     */
    public String getProfileImageUrl() {
        return this.profileImageURL.replace(".jpg", "_400x400.jpg");
    }


    /**
     * Gets the profile information as a String
     *
     * @return the profile info as String
     */
    @Override
    public String toString() {
        return this.getUserName() + " :: " + this.getScreenName() + " :: " + this.getFollowersCount() +
                " :: " + this.getFriendsCount() + " :: " + this.getLocation() + " :: " + this.getProfileImageUrl();
    }
}
