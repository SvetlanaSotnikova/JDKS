package Sem5.HM;

import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Philosopher implements Runnable {
    private final int id;
    private final String name;
    private final Lock leftFork;
    private final Lock rightFork;
    private int timesEaten;

    public Philosopher(int id, String name, Lock leftFork, Lock rightFork) {
        this.id = id;
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        runActionPhilosopher();
    }

    private void runActionPhilosopher() {
        try {
            while (timesEaten < 3) {
                think();
                leftFork.lock();
                try {
                    rightFork.lock();
                    try {
                        eat();
                    } finally {
                        rightFork.unlock();
                    }
                } finally {
                    leftFork.unlock();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Philosopher " + id + ": " + name + " interrupted");
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + ": " + name + " is thinking");
        Thread.sleep(new Random().nextInt(1000, 3000));
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + name + " is eating");
        Thread.sleep(new Random().nextInt(3000, 5000));
        timesEaten++;
        System.out.println("Philosopher " + id + ": " + name + " ate " + timesEaten + " times\n");
    }
}
