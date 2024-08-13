package Sem1.server;

import Sem1.server.client.ClientController;
import Sem1.server.client.ClientGUI;
import Sem1.server.server.ServerController;
import Sem1.server.server.ServerWindow;

public class Main {
    public static void main(String[] args) {

        //создание объектов сервера и создание связи между ними
        ServerWindow serverWindow = new ServerWindow();
        ServerController serverController = new ServerController();
        serverWindow.setController(serverController);
        serverController.setServerWindow(serverWindow);

        //создание объектов клиента1 и создание связи между ними
        ClientGUI clientGUI1 = new ClientGUI();
        ClientController clientController1 = new ClientController();
        clientController1.setClientView(clientGUI1);
        clientGUI1.setClientController(clientController1);
        clientController1.setServerController(serverController);

        //создание объектов клиента2 и создание связи между ними
        ClientGUI clientGUI2 = new ClientGUI();
        ClientController clientController2 = new ClientController();
        clientController2.setClientView(clientGUI2);
        clientGUI2.setClientController(clientController2);
        clientController2.setServerController(serverController);
    }
}
