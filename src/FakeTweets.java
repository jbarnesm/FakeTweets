import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class FakeTweets {
	protected static List<String> tweets = new ArrayList<>();
	protected static String userName;
	protected static String tempFile;
	private static Date createdate;
	private static int followersCount;
	private static int followingCount;



	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);		
		//The following will be used to check custom accounts...
		System.out.println("Welcome to FakeTweets! Please enter the account you'd like to examine:");
		userName = scan.nextLine();
		//userName = "congressedits";
		fetchUserTweets(userName, 100);
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");		
		System.out.println(userName);
		System.out.println(df.format(createdate));
		System.out.println("followers: ");
		System.out.println(followersCount);
		System.out.println("following: ");
		System.out.println(followingCount);
		for(String tweet : tweets) {
			System.out.println(tweet);
		}
		
		//tweets.add("sdf dsfdfwerferyr dfgkghgscvcb vbdfhrtrfzx sdf wercb rytuuou fdfbcb");
		//tweets.add("Explainer: What is artificial intelligence? https://t.co/ztrO8KRX6g via @abcnews #AI");
		System.out.println(LevDistance.getAvgDistance(tweets));
		

	}

	private static void fetchUserTweets(String userName, int numTweets) {
		try {
			//Fetch basic info
			User user = Constants.TWITTER.showUser(userName);
			createdate = user.getCreatedAt();
			followersCount = user.getFollowersCount();
			followingCount = user.getFriendsCount();
			//Fetch tweets
			Paging page = new Paging(1, Constants.PAGE_SIZE);
			for (int p = 1; p <= Math.ceil((double) numTweets / Constants.PAGE_SIZE); p++) {
				page.setPage(p);
				for (Status status : Constants.TWITTER.getUserTimeline(userName, page))
					tweets.add(status.getText());
			}     
			int numberTweets = tweets.size();			
		}
		catch (TwitterException e) {
			System.out.println("Unable to retrieve tweets from Twitter for " + userName + " at this time.");
			System.out.println(e.getMessage());
		}
	}   

}
