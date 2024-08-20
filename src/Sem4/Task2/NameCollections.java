package Sem4.Task2;

import java.util.*;

/**
 * В рамках выполнения задачи необходимо:
 * ● Создайте коллекцию мужских и женских имен с помощью интерфейса List -
 * добавьте повторяющиеся значения
 * ● Получите уникальный список Set на основании List
 * ● Определите наименьший элемент (алфавитный порядок)
 * ● Определите наибольший элемент (по количеству букв в слове но в обратном
 * порядке)
 * ● Удалите все элементы содержащие букву ‘A’
 */
public class NameCollections {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("Маша", "Костя", "Саша", "Света", "Катя", "Катя", "Костя"));
        System.out.println(names);
        Set<String> set = convertListToSet(names);
        System.out.println(set);
        System.out.println("min elem: " + getMinElemFromList(set));
        System.out.printf("min = %s, max = %s\n", getMinMaxElemFromList(set).get(0), getMinMaxElemFromList(set).get(1));
        deleteElementByChar(set, "А");
        System.out.println(set);
    }

    public static Set<String> convertListToSet(List<String> list) {
        return new HashSet<>(list);
    }

    public static String getMinElemFromList(Set<String> list) {
        return list.stream().min(Comparator.naturalOrder()).orElse(null);
    }

    public static List<String> getMinMaxElemFromList(Set<String> list) {
        List<String> resultList = new ArrayList<>();
        resultList.add(list.stream().min(Comparator.comparing(String::length)).orElse(null));
        resultList.add(list.stream().max(Comparator.comparing(String::length)).orElse(null));
        return resultList;
    }

    public static void deleteElementByChar(Set<String> list, String ch) {
        list.removeIf(element -> element.toLowerCase().contains(ch.toLowerCase()));
    }
}
