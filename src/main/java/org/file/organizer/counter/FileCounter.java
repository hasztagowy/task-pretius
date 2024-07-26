package org.file.organizer.counter;

import org.file.organizer.utils.Directory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class FileCounter {

    public void increment(String catalogName) {
        File counter = createIfNotExists();

        Map<String, Integer> map = new HashMap<>();
        try {
            if (counter.length() > 0) {
                Files.lines(counter.toPath()).forEach(line -> {
                    String[] split = line.split("=");
                    map.put(split[0], Integer.parseInt(split[1]));
                });
            }

            map.put(catalogName, map.getOrDefault(catalogName, 0) + 1);
            map.put("TOTAL", map.getOrDefault("TOTAL", 0) + 1);

            try (BufferedWriter writer = Files.newBufferedWriter(counter.toPath())) {
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    writer.write(entry.getKey() + "=" + entry.getValue());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File createIfNotExists() {
        File file = new File(Directory.HOME.getDirectory() + "/count.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot create count file", e);
        }
        return file;
    }
}
