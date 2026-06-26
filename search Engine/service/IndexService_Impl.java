package service;

import model.IndexData;

import java.util.*;

/*
 * Builds the index, now tracking COUNTS instead of just presence:
 *   - how many times each word appears in each file
 *   - total word count per file (needed for TF calculation)
 *
 * Steps for each document (same as before):
 *   1. Lowercase everything
 *   2. Remove punctuation
 *   3. Split into individual words
 *   4. Skip stop words
 *   5. Count word occurrences per file
 */
public class IndexService_Impl implements IndexService {

    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "a", "an", "the", "is", "are", "was", "were", "in", "on", "at", "to",
            "for", "of", "and", "or", "with", "this", "that", "it", "as", "by",
            "be", "has", "have", "i", "you", "we"
    ));

    @Override
    public IndexData buildIndex(Map<String, String> documents) {
        Map<String, Map<String, Integer>> wordDocumentCounts = new HashMap<>();
        Map<String, Integer> documentTotalWords = new HashMap<>();

        for (Map.Entry<String, String> entry : documents.entrySet()) {
            String fileName = entry.getKey();
            String content = entry.getValue();

            String[] words = content.toLowerCase()
                                     .replaceAll("[^a-z0-9\\s]", "")
                                     .split("\\s+");

            int wordCount = 0;

            for (String word : words) {
                if (word.isEmpty() || STOP_WORDS.contains(word)) {
                    continue;
                }

                wordCount++;

                wordDocumentCounts.computeIfAbsent(word, k -> new HashMap<>());
                Map<String, Integer> docCounts = wordDocumentCounts.get(word);
                docCounts.put(fileName, docCounts.getOrDefault(fileName, 0) + 1);
            }

            documentTotalWords.put(fileName, wordCount);
        }

        return new IndexData(wordDocumentCounts, documentTotalWords);
    }
}
