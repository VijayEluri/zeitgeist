// ============================================================================
//   Copyright 2009 Daniel W. Dyer
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
// ============================================================================
package org.uncommons.zeitgeist;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import org.testng.annotations.Test;

/**
 * Unit test for the article class.
 * @author Daniel Dyer
 */
public class ArticleTest
{
    @Test
    public void testTitleWordCounts()
    {
        // For clarity, deliberately choosing words that won't get truncated by the stemmer.
        Article article = new Article("fish fish yeah", "", null, new Date(), Collections.<Image>emptyList());
        Map<String, Integer> wordCounts = article.getWordCounts();
        assert wordCounts.size() == 2 : "Should be 2 words, is " + wordCounts.size();
        assert wordCounts.get("fish") == 2 : "Count should be 2 is " + wordCounts.get("fish");
        assert wordCounts.get("yeah") == 1 : "Count should be 1 is " + wordCounts.get("yeah");
    }

    @Test
    public void testContentWordCounts()
    {
        // For clarity, deliberately choosing words that won't get truncated by the stemmer.
        Article article = new Article("", "dog cat rabbit cat", null, new Date(), Collections.<Image>emptyList());
        Map<String, Integer> wordCounts = article.getWordCounts();
        assert wordCounts.size() == 3 : "Should be 3 words, is " + wordCounts.size();
        assert wordCounts.get("dog") == 1 : "Count should be 1 is " + wordCounts.get("dog");
        assert wordCounts.get("cat") == 2 : "Count should be 2 is " + wordCounts.get("cat");
        assert wordCounts.get("rabbit") == 1 : "Count should be 1 is " + wordCounts.get("rabbit");
    }


    /**
     * Word counts should include words in both the title and the article text.
     */
    @Test
    public void testCombinedWordCounts()
    {
        // For clarity, deliberately choosing words that won't get truncated by the stemmer.
        Article article = new Article("dog magic", "fish magic rabbit magic", null, new Date(), Collections.<Image>emptyList());
        Map<String, Integer> wordCounts = article.getWordCounts();
        assert wordCounts.size() == 4 : "Should be 4 words, is " + wordCounts.size();
        assert wordCounts.get("dog") == 1 : "Count should be 1 is " + wordCounts.get("dog");
        assert wordCounts.get("fish") == 1 : "Count should be 2 is " + wordCounts.get("fish");
        assert wordCounts.get("rabbit") == 1 : "Count should be 1 is " + wordCounts.get("rabbit");
        assert wordCounts.get("magic") == 3 : "Count should be 3 is " + wordCounts.get("magic");
    }
}