import java.util.Date;

public class Tweet {
	protected String text;
	protected Date date;
	protected Boolean isRetweet;
	protected Boolean containsLink;
	protected int numLikes;
	protected int numRetweets;
	
	public Tweet(String text) {
		this.text = text;
	}
}
