package repository;

import java.util.Map;

/*
 * Defines WHAT operations are available for getting document data.
 * The actual file-reading logic lives in DocumentRepository_Impl.
 */
public interface DocumentRepository {
    Map<String, String> loadDocuments(String folderPath);
}
