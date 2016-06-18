package domain;

import funk.shane.util.DictionaryCache;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DictionaryCacheTest {
    private DictionaryCache cache;

    @Before
    public void setUp() {
        cache = new DictionaryCache();
    }

    @Test
    public void testDictionaryCache() throws Exception {
        assertNotNull(cache);
        assertTrue(cache.getDictionary().size() > 0);
        assertTrue(cache.getDictionary().containsKey("abandoned"));
        assertTrue(cache.getDictionary().containsKey("can't stand"));
        assertTrue(cache.getDictionary().containsKey("zealous"));
    }
}
