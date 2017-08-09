import java.io.File;
import java.io.FileNotFoundException;
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
	protected static List<Tweet> tweets = new ArrayList<>();
	protected static String userName;
	protected static String tempFile;
	private static Date createdate;
	private static int followersCount;
	private static int followingCount;
	
	protected static int numReplies = 0;



	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);		
		//The following will be used to check custom accounts...
		System.out.println("Welcome to FakeTweets! Please enter the account you'd like to examine:");
		userName = scan.nextLine();
		scan.close();
		fetchUserTweets(userName, 100);
		exportTweets();
		//processTweets();

	}

	private static void exportTweets() {
		PrintStream out;
		try {
			out = new PrintStream(new FileOutputStream("temp" + File.separator + userName +"_tweets.txt"));
			out.println("Number of tweets = " + tweets.size());
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy|HH:mm");		
			for (Tweet t : tweets) {
				out.println(t.text+" "+df.format(t.date).toString()+"|"+t.numLikes+"|"+t.numRetweets+"|"+t.isRetweet+"|"+t.containsLink);
			}
			out.println("end");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private static void processTweets() {
		// sdfsdf


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
				for (Status status : Constants.TWITTER.getUserTimeline(userName, page)) {
					Tweet temp = new Tweet(status.getText());
					temp.date = status.getCreatedAt();
					temp.numLikes = status.getFavoriteCount();
					temp.numRetweets = status.getRetweetCount();
					temp.isRetweet = status.isRetweet();
					temp.containsLink = temp.text.contains("https://t.co/");
					
					tweets.add(temp);
				}
			}     
		}
		catch (TwitterException e) {
			System.out.println("Unable to retrieve tweets from Twitter for " + userName + " at this time.");
			System.out.println(e.getMessage());
		}
	}   

}
