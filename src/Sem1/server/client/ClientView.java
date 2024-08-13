package Sem1.server.client;

public interface ClientView {
    void sendMessageToServer(String message);
    void disconnectedFromServer();
}
