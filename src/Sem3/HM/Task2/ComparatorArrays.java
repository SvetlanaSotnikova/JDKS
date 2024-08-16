package Sem3.HM.Task2;

/**
 * Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true,
 * если они одинаковые, и false в противном случае. Массивы могут быть любого типа данных,
 * но должны иметь одинаковую длину и содержать элементы одного типа по парно по индексам
 */
public class ComparatorArrays {
    public static <T> boolean compareArray(T[] a, T[] b) {
        if (a == null || b == null) {
            return false;
        }
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = {"A", "B", "C", "D", "E", "F"};
        String[] b = {"A", "B", "C", "D", "E", "F"};
        System.out.println(compareArray(a, b));

        Integer[] a1 = {1, 2, 3, 4, 5};
        Integer[] a2 = {1, 2, 3, 5, 5};
        System.out.println(compareArray(a1, a2));
    }
}
