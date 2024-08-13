package Sem1.server.client;

import Sem1.server.server.ServerController;

// work logic
public class ClientController {

    private boolean connected;
    private ServerController serverController;
    private String clientName;
    private ClientView clientView;

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }

    public String getClientName() {
        return clientName;
    }

    public void connectToServer(String clientName) {
        this.clientName = clientName;
        if (serverController.isServerRunning()) {
            serverController.registerClient(this);
            connected = true;
            printText(clientName + " connected to server\n");
            serverController.loadMessageFromFile();
        } else {
            printText(clientName + " cannot connected\n");
        }
    }

    private void printText(String text) {
        if (clientView != null) {
            clientView.sendMessageToServer(text);
        } else {
            System.err.println("ClientView is not initialized!");
        }
    }

    public void sendMessageToServer(String message) {
        if (!connected) return;
        if (!message.isEmpty()) {
            String formattedMessage = clientName + ": " + message + "\n";
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
        if (!connected) return;
        if (!clientName.equals(newName) && !newName.isEmpty()) {
            serverController.unregisterClient(this);
            printText(clientName + " disconnected from server");
        }
        clientName = newName;
        serverController.registerClient(this);
        printText("Login Successful " + clientName + "\n");
    }

}
