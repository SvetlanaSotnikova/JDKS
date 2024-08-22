package Sem5.Task3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Runner implements Runnable {
    private final String name;
    private final CountDownLatch readySignal;
    private final CountDownLatch startSignal;
    private final CountDownLatch stopSignal;

    public Runner(String name, CountDownLatch startSignal, CountDownLatch stopSignal, CountDownLatch readySignal) {
        this.name = name;
        this.readySignal = readySignal;
        this.startSignal = startSignal;
        this.stopSignal = stopSignal;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(1000, 3000));
            System.out.println("Участник " + this.name + " на старте");
            readySignal.countDown();
            startSignal.await();
            System.out.println("Участник " + this.name + " побежал");
            Thread.sleep(new Random().nextInt(3000, 5000));
            stopSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
