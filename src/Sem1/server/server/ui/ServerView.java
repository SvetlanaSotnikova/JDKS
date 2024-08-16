package Sem1.server.server.ui;

import Sem1.server.server.domain.ServerController;

public interface ServerView {
    void appendLog(String log);
    void setServerController(ServerController serverController);
}
