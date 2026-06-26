# Semantic Document Search Engine

A console-based search engine built in Java that indexes text documents and retrieves results ranked by relevance using **TF-IDF scoring** — the same core algorithm behind real-world search engines.

---

## What It Does

- Reads `.txt` files from a folder into memory
- Builds an **inverted index** — maps each word to the documents it appears in, along with frequency counts
- Accepts a search query from the user via console
- Returns results **ranked by relevance** using TF-IDF scoring, not just keyword presence

---

## Architecture

Follows a clean **Controller → Service → Repository** layered architecture, using OOP principles with interfaces and implementation classes.

```
search-engine/
│
├── start/
│    └── Main.java                    → Entry point
│
├── controller/
│    └── SearchController.java        → Handles menu, user input, and output
│
├── service/
│    ├── DocumentService.java         → Interface: load documents
│    ├── DocumentService_Impl.java    → Implementation
│    ├── IndexService.java            → Interface: build inverted index
│    ├── IndexService_Impl.java       → Implementation (tokenize, count, index)
│    ├── RankingService.java          → Interface: rank results by TF-IDF
│    └── RankingService_Impl.java     → Implementation (TF-IDF formula)
│
├── repository/
│    ├── DocumentRepository.java      → Interface: file I/O contract
│    └── DocumentRepository_Impl.java → Implementation (reads .txt files)
│
├── model/
│    └── IndexData.java               → Data carrier: word counts + doc lengths
│
└── documents/                        → Folder containing .txt files to search
```

---

## How It Works — Step by Step

```
User runs the program
        ↓
Main.java → SearchController.initialize()
        ↓
DocumentRepository reads all .txt files from /documents folder
        ↓
IndexService tokenizes each file:
  - Lowercase everything
  - Remove punctuation
  - Remove stop words (a, the, is, ...)
  - Count how often each word appears in each file
        ↓
IndexData stores:
  - wordDocumentCounts: word → { filename → count }
  - documentTotalWords: filename → total word count
        ↓
User types a search query in the console menu
        ↓
RankingService calculates TF-IDF score for each matching document:
  - TF  = word count in this file / total words in this file
  - IDF = log(total files / files containing the word)
  - Score = TF × IDF
        ↓
Results printed ranked by score (highest = most relevant)
```

---

## TF-IDF Explained Simply

| Term | Meaning | Effect |
|------|---------|--------|
| **TF** (Term Frequency) | How often the word appears in this file | Rewards files where the word repeats often |
| **IDF** (Inverse Document Frequency) | How rare the word is across all files | Rewards rare/specific words, penalizes common words |
| **TF-IDF** | TF × IDF | Files with frequent + rare words score highest |

**Example:** Searching `"mathematics"` in 3 files:
- `notes2.txt` contains "mathematics" 2 times, and it's a rare word → **score: 0.2197**
- Other files don't contain it → score: 0

---

## Sample Output

```
Loaded 3 document(s) successfully.
Index built with 26 unique words.

===== Semantic Search Engine =====
1. Search Documents
2. List All Documents
3. Exit
Enter your choice: 1

Enter word(s) to search: mathematics
Ranked results for "mathematics":
1. notes2.txt  (score: 0.2197)

===== Semantic Search Engine =====
1. Search Documents
2. List All Documents
3. Exit
Enter your choice: 3
Goodbye!
```

---

## How to Run

**Prerequisites:** Java JDK 11 or above

```bash
# 1. Clone the repository
git clone https://github.com/YOUR_USERNAME/semantic-search-engine.git
cd semantic-search-engine

# 2. Add .txt files to the documents/ folder

# 3. Compile
javac -d bin start/*.java controller/*.java service/*.java repository/*.java model/*.java

# 4. Run
java -cp bin start.Main
```

---

## Tech Stack

- **Language:** Java
- **Core Concepts:** Inverted Index, TF-IDF, Information Retrieval
- **Design:** OOP, Layered Architecture, Interface + Implementation pattern
- **Data Structures:** HashMap, ArrayList, Map.Entry

---

## Roadmap

- [x] Document loading via file I/O
- [x] Inverted index with word frequency tracking
- [x] Keyword search
- [x] TF-IDF ranking
- [x] Console menu
- [ ] Text embeddings + cosine similarity (semantic search)
- [ ] PDF and DOCX support
- [ ] REST API layer (Spring Boot)
