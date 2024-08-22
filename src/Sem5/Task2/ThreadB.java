package Sem5.Task2;

public class ThreadB implements Runnable {
    private static int counter = 100;
    private final ThreadA threadA;

    public ThreadB(ThreadA threadA) {
        this.threadA = threadA;
    }

    @Override
    public void run() {
        while (counter >= 0) {
            if (threadA.isSwitcher()) {
                try {
                    Thread.sleep(100);
                    System.out.println(counter--);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
