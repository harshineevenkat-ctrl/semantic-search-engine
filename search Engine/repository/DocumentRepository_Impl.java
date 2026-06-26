package repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/*
 * HOW documents are actually loaded - reads every .txt file
 * from the given folder and stores filename -> content.
 */
public class DocumentRepository_Impl implements DocumentRepository {

    @Override
    public Map<String, String> loadDocuments(String folderPath) {
        Map<String, String> documents = new HashMap<>();

        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null) {
            return documents; // empty map, folder didn't exist or had no .txt files
        }

        for (File file : files) {
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                documents.put(file.getName(), content);
            } catch (IOException e) {
                System.out.println("Error reading file: " + file.getName());
            }
        }

        return documents;
    }
}
