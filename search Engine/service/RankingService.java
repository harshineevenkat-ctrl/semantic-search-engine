package service;

import model.IndexData;

import java.util.List;

/*
 * Defines WHAT ranking offers: given the index, a query, and the
 * total number of documents, return files sorted by relevance.
 */
public interface RankingService {
    List<String> rankDocuments(IndexData indexData, String query, int totalDocuments);
}
