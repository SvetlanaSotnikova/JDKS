package Sem1.server.server;

import Sem1.server.client.ClientController;
import Sem1.server.client.ClientGUI;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ServerController {
    private final List<ClientController> clients = new ArrayList<>();
    private final JTextArea log = new JTextArea();

    private static final String LOG_FILE = "CHAT_LOG_FILE.txt";

    private static boolean isServerWorks;


    public boolean isServerRunning() {
        return isServerWorks;
    }
    public void setServerWorks(boolean isServerWorks) {
        ServerController.isServerWorks = isServerWorks;
    }

    private void appendLog(String message) {
        if (!isServerWorks) return;
        log.append(message + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

    public void registerClient(ClientController clientController) {
        if (!isServerWorks) return;
        for (ClientController client : clients) {
            if (client.getClientName().equals(clientController.getClientName())) {
                return;
            }
        }

        clients.add(clientController);
    }

    public void unregisterClient(ClientController clientController) {
        if (!isServerWorks) return;
        clients.remove(clientController);
    }

    public void broadcastMessage(String message, ClientController clientController) {
        if (!isServerWorks) {
            appendLog("Cannot broadcast message, server is not working");
            return;
        }
        for (ClientController client : clients) {
            if (client != clientController) {
                clientController.sendMessageToServer(message);
            }
        }
        appendLog(message);
    }

    public void saveMessageToFile(String message, String clientName) {
        if (!isServerWorks) return;
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(LOG_FILE, true)))) {
            printWriter.println(clientName + ": " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMessageFromFile() {
        try {
            java.util.List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(LOG_FILE));
            for (String line : lines) {
                appendLog(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
