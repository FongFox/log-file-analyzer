# LOG FILE ANALYZER - TODO LIST

## PHASE 1: Setup & Basic Structure (3-5 giờ)

### 1.1 Setup project

- [x] Tạo Java project (Maven hoặc Gradle)
- [x] Setup Git repository
- [x] Tạo `.gitignore` cho Java
- [x] First commit

### 1.2 Tạo cấu trúc thư mục cơ bản

```
src/
  main/
    java/
      com/fongfox/
        Main.java
        LogParser.java
        LogEntry.java
        LogAnalyzer.java
  test/
    java/
resources/
  sample-logs/
README.md
```

- [x] Tạo các thư mục theo cấu trúc trên
- [x] Tạo các file Java rỗng

### 1.3 Tạo sample log file

- [x] Tạo file `sample.log` với ~100 dòng log4j 2.x format
- [x] Bao gồm mix của: DEBUG, INFO, WARN, ERROR, FATAL
- [x] Có thể tự viết hoặc search "log4j sample log file"

**Câu hỏi checkpoint:** Bạn đã biết cách tạo Maven/Gradle project chưa? Hay cần gợi ý?

---

## PHASE 2: Parse Single Log Line (5-7 giờ)

### 2.1 Tạo class `LogEntry`

- [x] Class này chứa data của 1 dòng log
- [x] Xác định fields cần có (timestamp, level, thread, logger, message)
- [x] Viết Constructor
- [x] Viết getters
- [x] Viết toString()

### 2.2 Viết Regex để parse log4j format

- [x] Tìm hiểu pattern: `%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n`
- [x] Viết regex cần capture: time, thread, level, logger, message
- [x] Test regex với 1 dòng log mẫu

**Câu hỏi:** Bạn đã biết cách viết regex groups `()` chưa? Làm sao để extract data từ regex groups trong Java?

**Note**:

- Regex syntax:

```bash
"(\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}\.\d{3}) \[([^]]+)\] (\w+)\s+(\w+) - (.*)"gm
```

- Example:

```bash
2026-01-20 12:40:01.123 [main] INFO  AppStarter - Starting OrderServiceApplication v2.4.0 on localhost
2026-01-20 12:42:45.880 [http-nio-8080-exec-4] ERROR OrderController - Unexpected error while fetching order history
```

### 2.3 Implement `LogParser.parseLine(String line)`

- [x] Method nhận input: 1 dòng log string
- [x] Method return: LogEntry object (hoặc null nếu parse failed)
- [x] Handle invalid lines gracefully

### 2.4 Write unit tests

- [x] Test với valid log line
- [x] Test với invalid log line
- [x] Test với edge cases (empty line, malformed format)

---

## PHASE 3: Read & Parse Entire File (4-6 giờ)

### 3.1 Implement file reading

- [x] Dùng `Files.lines()` hoặc `BufferedReader`
- [x] Đọc từng dòng và parse
- [x] Lưu kết quả vào `List<LogEntry>`

### 3.2 Handle large files

- [x] Test với file lớn (tạo file 100MB+)
- [x] Ensure không bị OutOfMemoryError
- [x] Đo thời gian xử lý

### 3.3 Error handling

- [x] Handle file không tồn tại
- [x] Quyết định: skip dòng lỗi hay stop?
- [x] Log ra bao nhiêu dòng failed

**Câu hỏi:** Bạn muốn tool stop khi gặp lỗi, hay skip dòng lỗi và tiếp tục?

---

## PHASE 4: Analyze Logs (6-8 giờ)

### 4.1 Tạo class `LogAnalyzer`

- [x] Input: `List<LogEntry>`
- [x] Method: `countByLevel()` - đếm số lượng mỗi log level
- [x] Method: `findErrors()` - filter chỉ ERROR + FATAL
- [x] Method: `getTimeRange()` - earliest & latest timestamp
- [ ] Brainstorm thêm methods khác (optional)

**Câu hỏi:** Ngoài đếm log levels, bạn muốn analyze thêm gì?

- Top 10 loggers có nhiều lỗi nhất?
- Errors theo time range (errors per hour)?
- Pattern detection (cùng 1 lỗi lặp lại nhiều lần)?

### 4.2 Implement analysis methods

- [x] countByLevel() returns Map<LogLevel, Long>
- [x] findErrors() implementation
- [x] getTimeRange() implementation
- Mỗi method return data structure phù hợp

**Example:** `Map<LogLevel, Integer>` cho countByLevel()

### 4.3 Test analysis logic

- [x] Tạo test data với known results
- [x] Verify calculations đúng

---

## PHASE 5: Output Results (3-5 giờ)

### 5.1 Console output

- [ ] Print analysis results ra console
- [ ] Format đẹp, dễ đọc
- [ ] Example format:

```
=== Log Analysis Report ===
Total lines: 1000
DEBUG: 300
INFO: 500
WARN: 150
ERROR: 45
FATAL: 5

Time range: 07:00:00.000 - 18:30:45.123
```

### 5.2 Text file output

- [ ] Ghi report vào `.txt` file
- [ ] Same format như console

### 5.3 JSON output

- [ ] Serialize analysis results thành JSON
- [ ] Dùng library (Gson, Jackson) hoặc tự build JSON string
- [ ] Ghi vào `.json` file

**Câu hỏi:** Bạn đã dùng JSON library nào chưa? Muốn dùng library hay tự build?

---

## PHASE 6: CLI Interface (2-4 giờ)

### 6.1 Command line arguments

- [ ] Parse input file path
- [ ] Parse output format (console/text/json)
- [ ] Parse options (ví dụ: `--level ERROR`)

### 6.2 Usage instructions

- [ ] Print help message khi user gõi sai
- [ ] Example format:

```
Usage: java -jar log-analyzer.jar <log-file> [options]
Options:
  --output <format>    Output format: console, text, json
  --level <level>      Filter by log level
```

---

## PHASE 7: Polish & Documentation (2-3 giờ)

### 7.1 Code cleanup

- [ ] Remove debug code
- [ ] Consistent naming
- [ ] Add comments where needed

### 7.2 Write README.md

- [ ] Project description
- [ ] Features
- [ ] How to build & run
- [ ] Usage examples
- [ ] Sample output

### 7.3 Final testing

- [ ] Test với different log files
- [ ] Test all features
- [ ] Fix bugs

### 7.4 Git commits

- [ ] Review commit history
- [ ] Ensure có meaningful commit messages

---

## OPTIONAL EXTENSIONS (nếu còn thời gian)

- [ ] Filter by time range - Analyze logs trong khoảng thời gian cụ thể
- [ ] Search by keyword - Tìm logs chứa keyword
- [ ] Duplicate error detection - Tìm errors giống nhau
- [ ] Support multiple log formats - Thêm Apache/Nginx
- [ ] Performance benchmarking - So sánh performance với file sizes khác nhau

---

## NOTES & DECISIONS

### Decisions made:

- Log format: Log4j 2.x
- Programming language: Java
- File reading approach: Files.lines() or BufferedReader
- Focus: Analysis over multiple format support

### Questions to answer during development:

1. Stop on error vs skip and continue?
2. Which JSON library to use?
3. What additional analysis features to add?

---

## PROGRESS TRACKING

**Started:** [Date]
**Target completion:** [Date]
**Hours spent:** [Track here]

### Daily log:

- Day 1:
- Day 2:
- ...