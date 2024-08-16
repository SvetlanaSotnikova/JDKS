package Sem3.HM.Task3;

/**
 * Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
 * Класс должен иметь методы getFirst(), getSecond()
 * для получения значений каждого из составляющих пары, а также переопределение метода toString(),
 * возвращающее строковое представление пары
 */
public record Pair<F, S>(F firstValue, S secondValue) {

    @Override
    public String toString() {
        return String.format("First value: %s\nSecond value: %s)", firstValue, secondValue);
    }

    public static void main(String[] args) {
        Pair pair = new Pair<>("Hi", "Guys!");
        System.out.println(pair);
        System.out.println(pair.firstValue() + ", " + pair.secondValue());
    }
}
