package Sem5.Task1;

/**
 * В рамках выполнения задачи необходимо:
 * ● Создать два класс ObjectA, ObjectB
 * ● Реализовать класс в котором два потока при запуске провоцируют DeadLock для
 * объектов ObjectA, ObjectB
 **/
public class Main {
    static final Object objectA = new Object();
    static final Object objectB = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                workThread1();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                workThread2();
            }
        });

        thread1.start();
        thread2.start();
    }

    public static void workThread1() {
        synchronized (objectA) {
            try {
                System.out.println("Поток 1 с объектом А");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (objectB) {
                System.out.println("Поток 1 с объектом B");
            }
        }
    }

    public static void workThread2() {
        synchronized (objectB) {
            System.out.println("Поток 2 с объектом B");

            synchronized (objectA) {
                System.out.println("Поток 2 с объектом A");
            }
        }
    }
}
