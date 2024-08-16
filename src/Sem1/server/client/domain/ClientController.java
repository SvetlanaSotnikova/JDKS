package Sem1.server.client.domain;

import Sem1.server.client.ui.ClientView;
import Sem1.server.server.domain.ServerController;

import java.util.List;

// work logic
public class ClientController {

    private boolean isHistoryLoad;
    private boolean connected;
    private String clientName;

    private final ServerController serverController;
    private final ClientView clientView;


    public ClientController(ServerController serverController, ClientView clientView) {
        this.serverController = serverController;
        this.clientView = clientView;
        clientView.setClientController(this);
    }

    public String getClientName() {
        return clientName;
    }

    public void connectToServer(String clientName) {
        this.clientName = clientName;
        System.out.println("Connecting with clientName: " + clientName); // Проверка
        if (serverController.isServerRunning()) {
            serverController.registerClient(this);
            connected = true;
            String connectMessage = clientName + " connected to server\n";
            serverController.broadcastMessage(connectMessage, this);
            serverController.appendLog(connectMessage);
            if (!isHistoryLoad) {
                List<String> lines = serverController.loadMessageFromFile();
                for (String line : lines) {
                    printText(line);
                }
                isHistoryLoad = true;
            }
        } else {
            printText(clientName + " cannot connected\n");
        }
    }

    public void printText(String text) {
        if (clientView != null) {
            clientView.sendMessageToServer(text);
        } else {
            System.err.println("ClientView is not initialized!");
        }
    }

    public void sendMessageToServer(String message) {
        if (!connected && !serverController.isServerRunning()) {
            return;
        }
        if (!message.isEmpty()) {
            String formattedMessage = clientName + ": " + message;
            printText(formattedMessage);
            serverController.broadcastMessage(clientName + ": " + message, this);
            serverController.saveMessageToFile(message, clientName);
        }
    }

    public void disconnectFromServer() {
        if (connected && serverController.isServerRunning()) {
            connected = false;
            serverController.unregisterClient(this);
            printText(clientName + " disconnected from server");
        } else {
            printText("Failed to connect to server: Server is not running");
        }
    }

    public void updateLogin(String newName) {
        if (!connected && !serverController.isServerRunning()) return;
        if (!clientName.equals(newName) && !newName.isEmpty()) {
            serverController.unregisterClient(this);
        }
        String oldName = clientName;
        clientName = newName;
        serverController.registerClient(this);
        serverController.broadcastMessage(oldName + " changed name to: " + newName, this);
        printText("Login successful changed" + clientName + "\n");
    }
}
