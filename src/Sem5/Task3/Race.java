package Sem5.Task3;

import java.util.concurrent.CountDownLatch;

public class Race implements Runnable {
    private final CountDownLatch readySignal;
    private final CountDownLatch startSignal;
    private final CountDownLatch stopSignal;

    public Race(CountDownLatch startSignal, CountDownLatch readySignal, CountDownLatch stopSignal) {
        this.startSignal = startSignal;
        this.readySignal = readySignal;
        this.stopSignal = stopSignal;
    }

    @Override
    public void run() {
        try {
            readySignal.await();
            System.out.println("На старт!");
            Thread.sleep(100);
            System.out.println("Внимание!");
            Thread.sleep(100);
            System.out.println("Марш!");
            startSignal.countDown();
            stopSignal.await();
            System.out.println("Забег окончен");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
