package Sem5.HM;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
 * Вилки лежат на столе между каждой парой ближайших философов.
 * Каждый философ может либо есть, либо размышлять.
 * Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
 * Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
 * Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */
public class Main {
    private static final int COUNT_PHILOSOPHERS = 5;

    public static void main(String[] args) {
        AtomicReference<List<Philosopher>> philosophers = new AtomicReference<>(new ArrayList<>(COUNT_PHILOSOPHERS));
        List<Lock> forks = new ArrayList<>(COUNT_PHILOSOPHERS);

        for (int i = 0; i < COUNT_PHILOSOPHERS; i++) {
            forks.add(new ReentrantLock());
        }
        String[] names = {"Vitea", "Plato", "Aristotle", "Descartes", "Confucius"};
        for (int i = 0; i < COUNT_PHILOSOPHERS; i++) {
            Lock leftFork = forks.get(i);
            Lock rightFork = forks.get((i + 1) % COUNT_PHILOSOPHERS);
            Philosopher philosopher = new Philosopher(i + 1, names[i], leftFork, rightFork);
            philosophers.get().add(philosopher);
            new Thread(philosopher).start();
        }
    }
}
