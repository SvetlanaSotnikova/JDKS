package Sem1.server.server.domain;

import Sem1.server.client.domain.ClientController;
import Sem1.server.server.repository.Repository;
import Sem1.server.server.ui.ServerView;
import Sem1.server.server.ui.ServerWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ServerController {
    private final List<ClientController> clients;
    private final JTextArea log = new JTextArea();
    private final Repository<String> repository;
    private final ServerView serverView;

    private static final String LOG_FILE = "src/Sem1/server/server/repository/CHAT_LOG_FILE.txt";

    private static boolean isServerWorks;


    public ServerController(ServerView serverView, Repository<String> repository) {
        this.serverView = serverView;
        this.repository = repository;
        serverView.setServerController(this);
        clients = new ArrayList<>();
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
            serverView.appendLog("Cannot broadcast message, server is not working");
            return;
        }
        for (ClientController client : clients) {
            if (clientController == null || client != clientController) {
                client.printText(message);
            }
        }
        serverView.appendLog(message);
    }


    public void startServer() {
        if (!isServerWorks) {
            setServerWorks(true);
            serverView.appendLog("Server started");
        } else {
            serverView.appendLog("Server is already running");
        }
    }

    public void stopServer() {
        if (isServerWorks) {
            setServerWorks(false);
            serverView.appendLog("Server stopped");
        } else {
            serverView.appendLog("Server is already stopped");
        }
    }

    public void saveMessageToFile(String message, String clientName) {
        if (!isServerWorks) return;
        repository.saveMessageToFile(message, clientName, LOG_FILE);
    }

    public List<String> loadMessageFromFile() {
        return repository.loadMessageFromFile(LOG_FILE);
    }
}
