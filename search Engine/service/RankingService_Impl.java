package service;

import model.IndexData;

import java.util.*;

/*
 * THE TF-IDF FORMULA, explained simply:
 *
 *   TF (Term Frequency)  = how often the word appears in THIS file
 *                          / total words in THIS file
 *                          -> rewards words that repeat a lot in one file
 *
 *   IDF (Inverse Doc Freq) = log(total files / files containing the word)
 *                          -> rewards RARE words (common words score low,
 *                             since they appear in almost every file)
 *
 *   score = TF * IDF, summed across every word in the query
 *
 * Files with the HIGHEST combined score are the most relevant -> shown first.
 */
public class RankingService_Impl implements RankingService {

    @Override
    public List<String> rankDocuments(IndexData indexData, String query, int totalDocuments) {

        String[] queryWords = query.toLowerCase().trim().split("\\s+");
        Map<String, Double> scores = new HashMap<>();

        for (String word : queryWords) {
            Map<String, Integer> docCounts = indexData.getWordDocumentCounts().get(word);
            if (docCounts == null) {
                continue; // word doesn't exist in any document
            }

            int numDocsWithWord = docCounts.size();
            double idf = Math.log((double) totalDocuments / numDocsWithWord);

            for (Map.Entry<String, Integer> entry : docCounts.entrySet()) {
                String fileName = entry.getKey();
                int countInDoc = entry.getValue();
                int totalWordsInDoc = indexData.getDocumentTotalWords().get(fileName);

                double tf = (double) countInDoc / totalWordsInDoc;
                double tfidf = tf * idf;

                scores.put(fileName, scores.getOrDefault(fileName, 0.0) + tfidf);
            }
        }

        // sort files by score, highest first
        List<Map.Entry<String, Double>> sortedEntries = new ArrayList<>(scores.entrySet());
        sortedEntries.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        List<String> rankedFiles = new ArrayList<>();
        for (Map.Entry<String, Double> entry : sortedEntries) {
            rankedFiles.add(entry.getKey() + "  (score: " + String.format("%.4f", entry.getValue()) + ")");
        }

        return rankedFiles;
    }
}
