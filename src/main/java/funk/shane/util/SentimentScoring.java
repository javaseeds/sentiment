package funk.shane.util;

import funk.shane.domain.Statuses;
import funk.shane.domain.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SentimentScoring {
    public final static Logger log = LoggerFactory.getLogger(SentimentScoring.class);
    private final static DictionaryCache cache = new DictionaryCache();

    public static Tweet setScoring(final Tweet tweets, final String queryTerm) {
        for(Statuses statuses : tweets.getStatuses()) {
            int score = 0;
            if(statuses.getText() != null) {
                final String localText = statuses.getText().replaceAll("[^a-zA-Z' ]", "");
                final String[] components = localText.split("\\s");
                log.debug("components has {} elements", components.length);

                for(String word : components) {
                    if(word == null || word.length() < 1) {
                        continue;
                    }
                    else {
                        final String strMatch = word.toLowerCase();

                        if(strMatch.startsWith(queryTerm.toLowerCase())) {
                            statuses.setText(statuses.getText().replaceAll("#" + word, wrapSentiment("sentimentWord", "#" + word)));
                            continue;
                        }

                        int wordScore = (cache.getDictionary().containsKey(strMatch))
                            ? (cache.getDictionary().get(strMatch) == null ? 0 : cache.getDictionary().get(strMatch))
                            : 0;
                        log.debug("str: [{}] has wordScore: [{}]", strMatch, wordScore);
                        if(wordScore != 0) {
                            String sentimentWord = ((wordScore < 0) ? "bad" : "good") + "SentimentWord";
                            statuses.setText(statuses.getText().replaceAll(word, wrapSentiment(sentimentWord, word)));
                        }
                        score += wordScore;
                    }
                }
            }
            statuses.setScore(score);
        }

        return tweets;
    }

    private static String wrapSentiment(final String sentimentWord, final String word) {
        return "<span class='" + sentimentWord + "'>" + word + "</span>";
    }
}
