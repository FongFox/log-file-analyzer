package com.fongfox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import java.lang.reflect.Type;
import java.time.LocalDateTime;

import com.google.gson.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Reporter {
    public void printToConsole(Report report) {
        System.out.println("=== Log Analysis Report ===");
        System.out.printf("Total lines: %d%n", report.getTotalLines());
        report.getLevelCounts().forEach((lvl, cnt) ->
                System.out.printf("%s: %d%n", lvl, cnt));
        TimeRange tr = report.getTimeRange();
        if (tr != null)
            System.out.printf("Time range: %s - %s%n",
                    tr.getStart(), tr.getEnd());
    }

    public void writeToTextFile(Report report, Path path) throws IOException {
        if (path == null) throw new IllegalArgumentException("Output path cannot be null");

        // Nếu đường dẫn có thư mục cha thì tạo ra nó
        Path parent = path.getParent();
        if (parent != null) Files.createDirectories(parent);

        StringBuilder sb = new StringBuilder();

        sb.append("=== Log Analysis Report ===\n");
        sb.append(String.format("Total lines: %d%n", report.getTotalLines()));
        report.getLevelCounts().forEach((lvl, cnt) ->
                sb.append(String.format("%s: %d%n", lvl, cnt)));
        TimeRange tr = report.getTimeRange();
        if (tr != null)
            sb.append(String.format("Time range: %s - %s%n",
                    tr.getStart(), tr.getEnd()));
        else
            sb.append("Time range: N/A\n");

        Files.writeString(path, sb.toString());
    }

    public void writeToJsonFile(Report report, Path path) throws IOException {
        if (path == null) throw new IllegalArgumentException("Path cannot be null");

        // Create parent directories if needed
        Path parent = path.getParent();
        if (parent != null) Files.createDirectories(parent);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                    @Override
                    public JsonElement serialize(LocalDateTime src,
                                                 Type typeOfSrc,
                                                 JsonSerializationContext context) {
                        // Use ISO‑8601 string (default toString() of LocalDateTime)
                        return new JsonPrimitive(src.toString());
                    }
                })
                .setPrettyPrinting()
                .create();

        String jsonString = gson.toJson(report);   // now works
        Files.writeString(path, jsonString);
    }

}
