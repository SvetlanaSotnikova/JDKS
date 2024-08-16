package Sem1.server.server.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileStorage implements Repository<String> {

    @Override
    public void saveMessageToFile(String message, String clientName, String logFile) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)))) {
            printWriter.println(clientName + ": " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> loadMessageFromFile(String logFile) {
        try {
            return java.nio.file.Files.readAllLines(java.nio.file.Paths.get(logFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
