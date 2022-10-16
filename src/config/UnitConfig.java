package config;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class UnitConfig {
    public static String unitConfig(String key) {
        Path path = Paths.get("src/Config.conf");
        Map<String, String> map = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] s = currentLine.split("=");
                map.put(s[0], s[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map.get(key);
    }
}
