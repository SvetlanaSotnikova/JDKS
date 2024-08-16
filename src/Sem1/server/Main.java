package Sem1.server;

import Sem1.server.client.domain.ClientController;
import Sem1.server.client.ui.ClientGUI;
import Sem1.server.server.domain.ServerController;
import Sem1.server.server.repository.FileStorage;
import Sem1.server.server.ui.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerController serverController = new ServerController(new ServerWindow(), new FileStorage());

        new ClientController(serverController, new ClientGUI());
        new ClientController(serverController, new ClientGUI());
    }
}
