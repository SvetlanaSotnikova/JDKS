package Sem1.server.server;

import Sem1.server.client.ClientController;

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

    private ServerWindow serverWindow;

    public void setServerWindow(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
    }

    public boolean isServerRunning() {
        System.out.println("Checking if server is running: " + isServerWorks);
        return isServerWorks;
    }

    public void setServerWorks(boolean isServerWorks) {
        ServerController.isServerWorks = isServerWorks;
        System.out.println("isServerWorks " + ServerController.isServerWorks);
    }

    // мало ли пригодится
    public void appendLog(String message) {
        if (!isServerWorks) return;
        log.append(message + "\n");
    }

    public void appendLogToServerWindow(String message) {
        if (!isServerWorks) return;
        serverWindow.appendLog(message);
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
            appendLogToServerWindow("Cannot broadcast message, server is not working");
            return;
        }
        for (ClientController client : clients) {
            if (clientController == null || client != clientController) {
                client.printText(message);
            }
        }
        appendLogToServerWindow(message);
    }


    public void saveMessageToFile(String message, String clientName) {
        if (!isServerWorks) return;
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(LOG_FILE, true)))) {
            printWriter.println(clientName + ": " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> loadMessageFromFile() {
        try {
            return java.nio.file.Files.readAllLines(java.nio.file.Paths.get(LOG_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
