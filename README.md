So a few years ago, I was playing with a Twitter reader and running through a really basic "sentiment" analysis
tool and I recently dusted off the code and updated it for others to review if they'd like.

The dictionary used here was borrowed from somewhere, I don't remember where, but it takes a non-biased, non-contexual
look at the words in live tweets based on a hashtag search.

A good resource for **_way_** more inteligent sentiment analysis can be found here:
http://sentic.net/

Twitter allows for modest use of their APIs for free but one needs to set up with specific OAuth
keys and codes.  Those codes are not available here, I have them in a "secret.yml" file not checked in to
this repository.  Dropwizard nicely allows for on the fly uploads of such information - the links below contain
instructions on how to get these keys using your Twitter account.  

I have it in my YAML file to look like:

    OAuth:
      accessToken: twitterAccessTokenHere
      accessSecret: twitterAccessSecretHere
      consumerKey: twitterConsumerKeyHere
      consumerSecret: twitterConsumerSecretHere

Here are some of the links I culled to build this rudimentary tool

The Links:
- http://dropwizard.io (root of entire project and worth learning)
- https://jersey.java.net (heart of webserver in Dropwizard)

- http://www.smartjava.org/content/access-twitter-rest-api-v11-scala-and-java-using-signpost
- https://dev.twitter.com/oauth/overview/single-user
- https://github.com/twitter/hbc
- http://java.dzone.com/articles/access-twitter-rest-api-v11
- http://www.smartjava.org/content/access-twitter-rest-api-v11-scala-and-java-using-signpost

(Note: signpost has not been updated for quite sometime, not sure if it's still safe to use - be cautious)
- http://code.google.com/p/oauth-signpost/wiki/GettingStarted 
- http://code.google.com/p/oauth-signpost/wiki/TwitterAndSignpost
- https://github.com/mttkay/signpost-examples/blob/master/OAuthTwitterExample/src/TwitterMain.java

- https://dev.twitter.com/rest/reference/get/search/tweets
- https://dev.twitter.com/rest/public
- http://www.jsoneditoronline.org/
- https://dev.twitter.com/rest/tools/console
- http://stackoverflow.com/questions/19646777/google-oauth-java-client-and-twitter-api
- http://fabiouechi.blogspot.co.uk/2011/11/using-google-oauth-java-client-to.html
- http://www.wigwag.com/devblog/twitter-api-java-tutorial/

==================================================
### start the server
sentiment>java -jar target/sentiment-0.0.X-SNAPSHOT.jar server secret.yml


