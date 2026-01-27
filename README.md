# Log File Analyzer

A command-line tool written in Java to analyze log files from applications using Log4j 2.x format.

---

## What is this?

Log File Analyzer helps developers and system administrators quickly analyze application logs by:

- Counting logs by severity level (DEBUG, INFO, WARN, ERROR, FATAL)
- Finding and filtering errors in the system
- Analyzing log timelines
- Exporting analysis results in multiple formats (console, text, JSON)

**Goal:** This is my first personal project aimed at practicing parsing, pattern matching, and large file processing
skills in Java.

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
# Run with default settings (Console output)
./gradlew run --args="src/main/resources/sample-logs/sample.log"

# Run with specific level filtering and JSON output
./gradlew run --args="src/main/resources/sample-logs/sample.log --level ERROR --output json"

# Run with custom output file path
./gradlew run --args="src/main/resources/sample-logs/sample.log --output text --file ./reports/my-analysis.txt"
```

### Example

**Input file (sample.log):**

```bash
WARNING: A restricted method in java.lang.System has been called
WARNING: java.lang.System::load has been called by net.rubygrapefruit.platform.internal.NativeLibraryLoader in an unnamed module 
(file:/C:/Users/Phong%20Tran/.gradle/wrapper/dists/gradle-9.2.0-bin/11i5gvueggl8a5cioxuftxrik/gradle-9.2.0/lib/native-platform-0.22-milestone-29.jar)
WARNING: Use --enable-native-access=ALL-UNNAMED to avoid a warning for callers in this module
WARNING: Restricted methods will be blocked in a future release unless native access is enabled

> Task :run
=== Parse Statistics ===
Total lines read: 125
Valid entries: 109
Invalid lines skipped: 16
Processing time: 20ms
Filtered entries for level: ERROR. Remaining: 6
JSON report written to D:\Desktop\log-file-analyzer\src\main\resources\output\json\report.json

BUILD SUCCESSFUL in 791ms
3 actionable tasks: 1 executed, 2 up-to-date
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

| Option              | Description                           | Default                         | Example                   |
|---------------------|---------------------------------------|---------------------------------|---------------------------|
| `<path>`            | Required: Path to the source log file | N/A                             | `app.log`                 |
| `--output <format>` | Output format: console, text, json    | `console`                       | `--output json`           |
| `--file <path>`     | Custom output path for text/json      | `src/main/resources/output/...` | `--file ./my-report.json` |
| `--level <level>`   | Filter by severity: DEBUG, INFO, etc. | All levels                      | `--level ERROR`           |
| `--help`            | Show usage instructions               | N/A                             | `--help`                  |

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

```bash
log-file-analyzer/
├── gradle/
│   ├── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │       ├── fongfox/
│   │   │           ├── LogAnalyzer.java
│   │   │           ├── LogAnalyzerException.java
│   │   │           ├── LogEntry.java
│   │   │           ├── LogFileReader.java
│   │   │           ├── LogLevel.java
│   │   │           ├── LogParser.java
│   │   │           ├── Main.java
│   │   │           ├── Report.java
│   │   │           ├── Reporter.java
│   │   │           └── TimeRange.java
│   │   ├── resources/
│   │       ├── output/
│   │       │   ├── json/
│   │       │   │   └── report.json
│   │       │   ├── txt/
│   │       │       └── report.txt
│   │       ├── sample-logs/
│   │           └── sample.log
│   ├── test/
│       ├── java/
│       │   ├── com/
│       │       ├── fongfox/
│       │           ├── LogAnalyzerTest.java
│       │           └── LogParserTest.java
│       ├── resources/
├── README.md
├── Todo.md
├── build.gradle.kts
├── gradlew
├── gradlew.bat
└── settings.gradle.kts

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