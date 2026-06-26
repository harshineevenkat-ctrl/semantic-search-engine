package service;

import java.util.Map;

/*
 * Defines WHAT the service layer offers to the controller.
 * Later steps will add methods here like:
 *   buildIndex(...), search(...), rankResults(...)
 */
public interface DocumentService {
    Map<String, String> getAllDocuments(String folderPath);
}
