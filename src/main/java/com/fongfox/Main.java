package com.fongfox;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LogParser parser = new LogParser();

        // String testLog = "2026-01-20 12:40:01.123 [main] INFO  AppStarter - Starting OrderServiceApplication v2.4.0 on localhost";
        // LogEntry entry = parser.parseLine(testLog);
        // System.out.println(entry);  // Nhờ @ToString sẽ in ra thông tin

        String[] testLogs = {
                "2026-01-20 12:40:01.123 [main] INFO  AppStarter - Starting OrderServiceApplication v2.4.0 on localhost",
                "2026-01-20 12:42:45.880 [http-nio-8080-exec-4] ERROR OrderController - Unexpected error while fetching order history",
                "2026-01-20 12:40:10.050 [http-nio-8080-exec-1] DEBUG UserAuthService - Validating credentials against LDAP provider...",
                "2026-01-20 12:40:16.120 [http-nio-8080-exec-2] WARN  PaymentProcessor - Payment gateway response delayed (1670ms). Retrying...",
                "Invalid log line"  // Test dòng lỗi format
        };

        for (String log : testLogs) {
            LogEntry entry = parser.parseLine(log);
            System.out.println(entry);
            System.out.println("---");
        }
    }
}
