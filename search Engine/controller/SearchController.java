package controller;

import service.DocumentService;
import service.DocumentService_Impl;
import service.IndexService;
import service.IndexService_Impl;
import service.RankingService;
import service.RankingService_Impl;
import model.IndexData;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * Coordinates the full application flow.
 * Handles menu display, user input, and delegates to service layer.
 * Same role as ATMController - shows menu, reads choice, calls service.
 */
public class SearchController {

    private DocumentService documentService = new DocumentService_Impl();
    private IndexService indexService = new IndexService_Impl();
    private RankingService rankingService = new RankingService_Impl();

    private Map<String, String> documents;
    private IndexData indexData;
    private int totalDocuments = 0;

    private Scanner scanner = new Scanner(System.in);

    // called once at startup - loads files and builds index
    public void initialize(String folderPath) {
        documents = documentService.getAllDocuments(folderPath);

        if (documents.isEmpty()) {
            System.out.println("No documents found in '" + folderPath + "' folder.");
            return;
        }

        indexData = indexService.buildIndex(documents);
        totalDocuments = documents.size();

        System.out.println("Loaded " + totalDocuments + " document(s) successfully.");
        System.out.println("Index built with " + indexData.getWordDocumentCounts().size() + " unique words.");
    }

    // main menu loop - keeps running until user chooses Exit
    public void showMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Semantic Search Engine =====");
            System.out.println("1. Search Documents");
            System.out.println("2. List All Documents");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    searchDocuments();
                    break;
                case "2":
                    listDocuments();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private void searchDocuments() {
        if (indexData == null) {
            System.out.println("No documents loaded.");
            return;
        }

        System.out.print("Enter word(s) to search: ");
        String query = scanner.nextLine();

        List<String> rankedResults = rankingService.rankDocuments(indexData, query, totalDocuments);

        if (rankedResults.isEmpty()) {
            System.out.println("No documents found containing \"" + query + "\"");
        } else {
            System.out.println("\nRanked results for \"" + query + "\":");
            int rank = 1;
            for (String result : rankedResults) {
                System.out.println(rank++ + ". " + result);
            }
        }
    }

    private void listDocuments() {
        if (documents == null || documents.isEmpty()) {
            System.out.println("No documents loaded.");
            return;
        }

        System.out.println("\nLoaded documents:");
        int count = 1;
        for (Map.Entry<String, String> entry : documents.entrySet()) {
            System.out.println(count++ + ". " + entry.getKey() + " (" + entry.getValue().length() + " characters)");
        }
    }
}
