package Sem1.server.client.ui;

import Sem1.server.client.domain.ClientController;

public interface ClientView {
    void sendMessageToServer(String message);
    void disconnectedFromServer();
    void appendLog(String message);
    void setClientController(ClientController clientController);
}
