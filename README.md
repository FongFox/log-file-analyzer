# Log File Analyzer

A command-line tool written in Java to analyze log files from applications using Log4j 2.x format.

---

## What is this?

Log File Analyzer helps developers and system administrators quickly analyze application logs by:
- Counting logs by severity level (DEBUG, INFO, WARN, ERROR, FATAL)
- Finding and filtering errors in the system
- Analyzing log timelines
- Exporting analysis results in multiple formats (console, text, JSON)

**Goal:** This is my first personal project aimed at practicing parsing, pattern matching, and large file processing skills in Java.

---

## How does it work?

### 1. Parsing
The tool reads the log file line by line and parses according to Log4j 2.x format:
```
07:25:30.123 [main] INFO com.example.MyApp - My log message
```

Each log line is analyzed into components:
- **Timestamp:** `07:25:30.123`
- **Thread:** `main`
- **Log Level:** `INFO`
- **Logger Name:** `com.example.MyApp`
- **Message:** `My log message`

### 2. Analysis
After parsing, the tool performs analysis such as:
- Count logs by each level
- Find the time range when logs were created
- Filter errors (ERROR + FATAL levels)

### 3. Output
Analysis results are exported in your chosen format:
- **Console:** Display directly on terminal
- **Text file:** Save report as `.txt`
- **JSON file:** Structured data for further processing

---

## How do I try it?

### Prerequisites
- Java 11 or higher
- Maven or Gradle

### Installation & Running

**Clone and build:**
```bash
git clone https://github.com/FongFox/log-file-analyzer.git
cd log-file-analyzer

# Using Maven
mvn clean package

# Using Gradle
gradle build
```

**Run the analyzer:**
```bash
# Basic usage
java -jar target/log-analyzer.jar <path-to-log-file>

# With options
java -jar target/log-analyzer.jar <path-to-log-file> --output json
java -jar target/log-analyzer.jar <path-to-log-file> --level ERROR
```

### Example

**Input file (sample.log):**
```
07:25:30.123 [main] INFO com.example.MyApp - Application started
07:25:31.456 [main] WARN com.example.MyApp - Low memory warning
07:25:32.789 [worker-1] ERROR com.example.MyApp - Connection failed
```

**Command:**
```bash
java -jar log-analyzer.jar sample.log --output console
```

**Output:**
```
=== Log Analysis Report ===
Total lines: 3
INFO: 1
WARN: 1
ERROR: 1

Time range: 07:25:30.123 - 07:25:32.789
```

### Command-line Options

| Option | Description | Example |
|--------|-------------|---------|
| `--output <format>` | Output format: console, text, json | `--output json` |
| `--level <level>` | Filter by log level | `--level ERROR` |
| `--help` | Show usage instructions | `--help` |

---

## In the future, maybe I...

### Planned Features
- Support multiple log formats (Apache Access Log, Nginx Error Log)
- Time range filtering for specific periods
- Keyword search functionality
- Duplicate error detection
- Performance benchmarking tools
- Real-time log monitoring
- Advanced analytics (top errors, frequency timelines)
- Web dashboard for visual analysis
- Custom log pattern configuration

### Potential Improvements
- Multi-threading for faster large file processing
- Database storage for historical analysis
- Alert system for critical errors
- Integration with monitoring tools (Prometheus, Grafana)

---

## Project Structure
```
log-file-analyzer/
├── src/
│   ├── main/java/com/fongfox/
│   │   ├── Main.java
│   │   ├── LogParser.java
│   │   ├── LogEntry.java
│   │   └── LogAnalyzer.java
│   └── test/java/
├── resources/sample-logs/
├── README.md
├── TODO.md
└── pom.xml (or build.gradle)
```

---

## Technologies Used
- **Language:** Java 11+
- **Build Tool:** Maven / Gradle
- **Libraries:** [To be added as project develops]
- **Tools:** Git, IntelliJ IDEA / Eclipse / VS Code

---

## Learning Outcomes

Through this project, I learned:
- Regular expressions and pattern matching in Java
- Efficient large file processing techniques
- Object-oriented design for data parsing
- Unit testing best practices
- Command-line argument parsing
- Git workflow and version control

---

## Contributing

This is a personal learning project, but feedback and suggestions are welcome!

Feel free to open issues for bugs or feature requests.

---

## License

MIT License

---

## Contact

- GitHub: FongFox
- Email: phong.tgn.coder@gmail.com

---

## Acknowledgments

Thanks to Boot.dev for the structured approach to personal projects.