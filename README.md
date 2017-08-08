# FakeTweets
The goal of this project is to create a program that estimates the likelihood a twitter account is run by a bot.

8/8/2017 - Beginning ideas
One of the difficulties of this project is the fact that Twitter bots are used for many different purposes, and, as a result, can look very
different from one another. For instance, a Twitter bot that is used to retweet news articles looks very different from a bot used to
inflate a user's follower count. A one-size-fits-all approach is therefore unwise, so I plan to implement different standards for
bot-like activity based on things like the account's follower count and the time the account has been active. I will test various
strategies for accuracy.

The following are some ideas for tests that I can implement:
- Check if account tweets at steady, predictable pace
- Check if the follower count seems abnormally high versus the account's number of tweets and the time since creation
- Check if account has abnormally high number of retweets
- Check if the account's tweets are repeated word for word on other accounts
- Check if the account's tweets consistently link to websites/contains images
