package model;

import java.util.Map;

/*
 * Holds everything needed to calculate TF-IDF:
 *   1. wordDocumentCounts: for each word, how many times it appears in each file
 *      e.g. "mathematics" -> { notes2.txt -> 2 }
 *   2. documentTotalWords: total (non-stopword) word count per file
 *      e.g. notes2.txt -> 39
 *
 * This is just a data carrier - no logic here, same idea as a
 * simple model/DTO class.
 */
public class IndexData {

    private Map<String, Map<String, Integer>> wordDocumentCounts;
    private Map<String, Integer> documentTotalWords;

    public IndexData(Map<String, Map<String, Integer>> wordDocumentCounts,
                      Map<String, Integer> documentTotalWords) {
        this.wordDocumentCounts = wordDocumentCounts;
        this.documentTotalWords = documentTotalWords;
    }

    public Map<String, Map<String, Integer>> getWordDocumentCounts() {
        return wordDocumentCounts;
    }

    public Map<String, Integer> getDocumentTotalWords() {
        return documentTotalWords;
    }
}
