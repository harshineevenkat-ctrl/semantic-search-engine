# 🔍 Semantic Document Search Engine

> A console-based search engine built in Java that indexes text documents and retrieves results ranked by relevance using **TF-IDF scoring** — the same core algorithm behind real-world search engines.

[![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white)](https://java.com)
[![Algorithm](https://img.shields.io/badge/Algorithm-TF--IDF-blue?style=flat)]()
[![Architecture](https://img.shields.io/badge/Architecture-Layered-green?style=flat)]()

---

## 🧠 What it does

- Reads `.txt` files from a folder into memory
- Builds an **inverted index** — maps each word to documents it appears in
- Accepts search queries from the user via console
- Returns results **ranked by TF-IDF score**, not just keyword presence

---

## 🏗️ System Architecture

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
  - Count word frequency per file
        ↓
IndexData stores:
  - wordDocumentCounts: word → { filename → count }
  - documentTotalWords: filename → total word count
        ↓
User types a search query
        ↓
RankingService calculates TF-IDF score:
  - TF  = word count in file / total words in file
  - IDF = log(total files / files containing the word)
  - Score = TF × IDF
        ↓
Results printed ranked by score (highest = most relevant)
```

---

## 📁 Project Structure

```
search-engine/
│
├── start/
│    └── Main.java                     → Entry point
│
├── controller/
│    └── SearchController.java         → Handles menu, user input, output
│
├── service/
│    ├── DocumentService.java          → Interface: load documents
│    ├── DocumentService_Impl.java     → Implementation
│    ├── IndexService.java             → Interface: build inverted index
│    ├── IndexService_Impl.java        → Implementation
│    ├── RankingService.java           → Interface: rank by TF-IDF
│    └── RankingService_Impl.java      → Implementation
│
├── repository/
│    ├── DocumentRepository.java       → Interface: file I/O contract
│    └── DocumentRepository_Impl.java  → Implementation
│
├── model/
│    └── IndexData.java                → Data carrier
│
└── documents/                         → .txt files to search
```

---

## 📊 TF-IDF Explained

| Term | Meaning | Effect |
|------|---------|--------|
| **TF** (Term Frequency) | How often word appears in this file | Rewards files where word repeats |
| **IDF** (Inverse Document Frequency) | How rare the word is across all files | Rewards rare/specific words |
| **TF-IDF** | TF × IDF | Files with frequent + rare words score highest |

---

## 💻 Sample Output

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
```

---

## 🚀 How to Run

**Prerequisites:** Java JDK 11 or above

```bash
# 1. Clone the repository
git clone https://github.com/harshineevenkat-ctrl/semantic-search-engine.git
cd semantic-search-engine

# 2. Add .txt files to the documents/ folder

# 3. Compile
javac -d bin start/*.java controller/*.java service/*.java repository/*.java model/*.java

# 4. Run
java -cp bin start.Main
```

---

## 🛠️ Tech Stack

| Tool | Purpose |
|------|---------|
| Java | Core language |
| Inverted Index | Fast document lookup |
| TF-IDF | Relevance ranking algorithm |
| HashMap / ArrayList | Core data structures |
| Layered Architecture | Controller → Service → Repository |

---

## 🗺️ Roadmap

- [x] Document loading via file I/O
- [x] Inverted index with word frequency tracking
- [x] Keyword search
- [x] TF-IDF ranking
- [x] Console menu
- [ ] Text embeddings + cosine similarity
- [ ] PDF and DOCX support
- [ ] REST API layer (Spring Boot)

---

## 👩‍💻 Built by

**Harshinee Venkatasubramaniyan** — GSSoC '26 Contributor | CS Engineering Student

[![LinkedIn](https://img.shields.io/badge/LinkedIn-blue?style=flat&logo=linkedin)](https://www.linkedin.com/in/harshinee-venkatasubramaniyan-8353b7379)
