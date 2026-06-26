package service;

import model.IndexData;

import java.util.Map;

/*
 * Defines WHAT the indexing service offers.
 * Now returns IndexData (word counts + doc lengths) instead of
 * just a plain word -> file list, so we can calculate TF-IDF later.
 */
public interface IndexService {
    IndexData buildIndex(Map<String, String> documents);
}
