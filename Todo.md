# LOG FILE ANALYZER - TODO LIST

## PHASE 1: Setup & Basic Structure (3-5 giờ)

### 1.1 Setup project
- [ ] Tạo Java project (Maven hoặc Gradle)
- [ ] Setup Git repository
- [ ] Tạo `.gitignore` cho Java
- [ ] First commit

### 1.2 Tạo cấu trúc thư mục cơ bản
```
src/
  main/
    java/
      [package của bạn]/
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
- [ ] Tạo các thư mục theo cấu trúc trên
- [ ] Tạo các file Java rỗng

### 1.3 Tạo sample log file
- [ ] Tạo file `sample.log` với ~100 dòng log4j 2.x format
- [ ] Bao gồm mix của: DEBUG, INFO, WARN, ERROR, FATAL
- [ ] Có thể tự viết hoặc search "log4j sample log file"

**Câu hỏi checkpoint:** Bạn đã biết cách tạo Maven/Gradle project chưa? Hay cần gợi ý?

---

## PHASE 2: Parse Single Log Line (5-7 giờ)

### 2.1 Tạo class `LogEntry`
- [ ] Class này chứa data của 1 dòng log
- [ ] Xác định fields cần có (timestamp, level, thread, logger, message)
- [ ] Viết Constructor
- [ ] Viết getters
- [ ] Viết toString()

### 2.2 Viết Regex để parse log4j format
- [ ] Tìm hiểu pattern: `%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n`
- [ ] Viết regex cần capture: time, thread, level, logger, message
- [ ] Test regex với 1 dòng log mẫu

**Câu hỏi:** Bạn đã biết cách viết regex groups `()` chưa? Làm sao để extract data từ regex groups trong Java?

### 2.3 Implement `LogParser.parseLine(String line)`
- [ ] Method nhận input: 1 dòng log string
- [ ] Method return: LogEntry object (hoặc null nếu parse failed)
- [ ] Handle invalid lines gracefully

### 2.4 Write unit tests
- [ ] Test với valid log line
- [ ] Test với invalid log line
- [ ] Test với edge cases (empty line, malformed format)

---

## PHASE 3: Read & Parse Entire File (4-6 giờ)

### 3.1 Implement file reading
- [ ] Dùng `Files.lines()` hoặc `BufferedReader`
- [ ] Đọc từng dòng và parse
- [ ] Lưu kết quả vào `List<LogEntry>`

### 3.2 Handle large files
- [ ] Test với file lớn (tạo file 100MB+)
- [ ] Ensure không bị OutOfMemoryError
- [ ] Đo thời gian xử lý

### 3.3 Error handling
- [ ] Handle file không tồn tại
- [ ] Quyết định: skip dòng lỗi hay stop?
- [ ] Log ra bao nhiêu dòng failed

**Câu hỏi:** Bạn muốn tool stop khi gặp lỗi, hay skip dòng lỗi và tiếp tục?

---

## PHASE 4: Analyze Logs (6-8 giờ)

### 4.1 Tạo class `LogAnalyzer`
- [ ] Input: `List<LogEntry>`
- [ ] Method: `countByLevel()` - đếm số lượng mỗi log level
- [ ] Method: `findErrors()` - filter chỉ ERROR + FATAL
- [ ] Method: `getTimeRange()` - earliest & latest timestamp
- [ ] Brainstorm thêm methods khác (optional)

**Câu hỏi:** Ngoài đếm log levels, bạn muốn analyze thêm gì?
- Top 10 loggers có nhiều lỗi nhất?
- Errors theo time range (errors per hour)?
- Pattern detection (cùng 1 lỗi lặp lại nhiều lần)?

### 4.2 Implement analysis methods
- [ ] Mỗi method return data structure phù hợp
- [ ] Example: `Map<LogLevel, Integer>` cho countByLevel()

### 4.3 Test analysis logic
- [ ] Tạo test data với known results
- [ ] Verify calculations đúng

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