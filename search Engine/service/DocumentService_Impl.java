package service;

import repository.DocumentRepository;
import repository.DocumentRepository_Impl;

import java.util.Map;

/*
 * Business logic layer. Right now it just calls the repository.
 * Later, this is where indexing/ranking logic will go -
 * controller will never need to know HOW it's done.
 */
public class DocumentService_Impl implements DocumentService {

    private DocumentRepository documentRepository = new DocumentRepository_Impl();

    @Override
    public Map<String, String> getAllDocuments(String folderPath) {
        return documentRepository.loadDocuments(folderPath);
    }
}
