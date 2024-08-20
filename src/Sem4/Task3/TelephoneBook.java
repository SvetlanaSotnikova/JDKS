package Sem4.Task3;

import java.util.*;
import java.util.stream.Collectors;

/**
 * В рамках выполнения задачи необходимо:
 * ● Создайте телефонный справочник с помощью Map - телефон это ключ, а имя
 * значение
 * ● Найдите человека с самым маленьким номером телефона
 * ● Найдите номер телефона человека чье имя самое большое в алфавитном порядке
 */
public class TelephoneBook {
    public static void main(String[] args) {
        Map<String, String> phoneBook = new HashMap<>();
        phoneBook.put("8454521254", "Nikita");
        phoneBook.put("8744512335", "Katea");
        phoneBook.put("8945541223", "Sveta");
        phoneBook.put("8123544866", "Kostea");
        phoneBook.put("8744532312", "Nikita");

        System.out.println(findMinPhone(phoneBook));
        System.out.println(findLongNameByAlphabet(phoneBook));
    }

    public static String findMinPhone(Map<String, String> phonesBook) {
        Set<Long> numbers = phonesBook.keySet().stream().map(Long::valueOf).collect(Collectors.toSet());
        Long number = numbers.stream().min(Comparator.naturalOrder()).get();
        return String.format("min number: %s, owner: %s",
                number, phonesBook.get(String.valueOf(number)));
    }

    public static String findLongNameByAlphabet(Map<String, String> phonesBook) {
        Set<Map.Entry<String, String>> elements = phonesBook.entrySet();
        Map.Entry<String, String> name = elements.stream().max(Map.Entry.comparingByValue()).get();
        return String.format("min name: %s, phone: %s",
                name.getValue(), name.getKey());
    }
}








