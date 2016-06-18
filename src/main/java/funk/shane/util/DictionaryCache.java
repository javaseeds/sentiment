package funk.shane.util;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

public class DictionaryCache {
    private final static Logger log = LoggerFactory.getLogger(DictionaryCache.class);
    private final static String WHITESPACE_REGEX = "\\s";
    private Map<String, Integer> dictionary;

    public DictionaryCache() {
        dictionary = Maps.newHashMap();
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load()
     * Will load into a quasi-cache the official Li Chen Scoring Dictionary (TM) that will be used
     * when reading tweets.
     *
     * Dictionary will have 1 or more space delimited terms and a final number that is the additive for the tweet
     * This hacked
     * @throws IOException
     */
    private void load() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
             DictionaryCache.class.getClassLoader().getResourceAsStream("assets/Dictionary.txt")
        ));
        String resource = null;
        while( (resource = br.readLine()) != null) {

            LinkedList<String> parseStr = new LinkedList<>();
            parseStr.addAll(Arrays.asList(resource.trim().split(WHITESPACE_REGEX)));

            StringBuilder sb = new StringBuilder();
            ListIterator<String> li = parseStr.listIterator();
            while(li.hasNext()) {
                if(li.nextIndex() < parseStr.size()-1) {
                    sb.append(li.next() + " ");
                }
                else {
                    li.next();
                }
            }

            dictionary.put(sb.toString().trim(), Integer.parseInt(parseStr.getLast()));

            // clean up
            sb.delete(0, sb.length());
            parseStr.clear();
        }
    }

    public synchronized Map<String, Integer> getDictionary() {
        return dictionary;
    }
}
