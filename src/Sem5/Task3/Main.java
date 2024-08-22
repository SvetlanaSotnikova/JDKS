package Sem5.Task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * В рамках выполнения задачи необходимо: <br>
 * ● 3 бегуна должны прийти к старту гонки <br>
 * ● Программа должна гарантировать, что гонка начнется только когда все три
 * участника будут на старте <br>
 * ● Программа должна отсчитать “На старт”, “Внимание”, “Марш” <br>
 * ● Программа должна завершиться когда все участники закончат гонку.<br>
 * ● Подумайте об использовании примитива синхронизации <br>
 **/
public class Main {
    private static final int COUNT_RUNNERS = 3;

    public static void main(String[] args) {
        CountDownLatch readySignal = new CountDownLatch(COUNT_RUNNERS);
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch stopSignal = new CountDownLatch(COUNT_RUNNERS);

        List<Runner> runners = new ArrayList<>(Arrays.asList(
                new Runner("Вася", startSignal, stopSignal, readySignal),
                new Runner("Саша", startSignal, stopSignal, readySignal),
                new Runner("Коля", startSignal, stopSignal, readySignal)
        ));

        new Thread(new Race(startSignal, readySignal, stopSignal)).start();
        for (Runner runner : runners) {
            new Thread(runner).start();
        }
    }
}
