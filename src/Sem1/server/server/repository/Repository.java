package Sem1.server.server.repository;

import java.util.List;

public interface Repository<T> {

    void saveMessageToFile(T message, T clientName, T logFile);

    List<T> loadMessageFromFile(T logFile);
}
