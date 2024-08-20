package Sem4.Task1;

import java.util.*;

/**
 * В рамках выполнения задачи необходимо:
 * ● Создайте коллекцию мужских и женских имен с помощью интерфейса List
 * ● Отсортируйте коллекцию в алфавитном порядке
 * ● Отсортируйте коллекцию по количеству букв в слове
 * ● Разверните коллекцию
 */
public class NameCollection {

    public static void sortByAlphabet(List<String> names) {
        Collections.sort(names);
    }

    public static void sortByLength(List<String> names) {
        names.sort(Comparator.comparing(String::length));
    }

    public static void reverse(List<String> names) {
        Collections.reverse(names);
    }

    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("Маша", "Костя", "Саша", "Света", "Катя"));
        System.out.println(names);
        sortByAlphabet(names);
        System.out.println(names);
        sortByLength(names);
        System.out.println(names);
        reverse(names);
        System.out.println(names);
    }
}
