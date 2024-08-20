package Sem3.HM.Task1;

/**
 * Написать класс Калькулятор (необобщенный),
 * который содержит обобщенные статические методы:
 * sum(), multiply(), divide(), subtract().
 * Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.
 * Методы должны возвращать результат своей работы.
 */
public class Calculator {
    public static <T extends Number, U extends Number> double sum(T a, U b) {
        return a.doubleValue() + b.doubleValue();
    }

    public static <T extends Number, U extends Number> double subtract(T a, U b) {
        return a.doubleValue() - b.doubleValue();
    }

    public static <T extends Number, U extends Number> double multiply(T a, U b) {
        return a.doubleValue() * b.doubleValue();
    }

    public static <T extends Number, U extends Number> double divide(T a, U b) {
        if (b.doubleValue() == 0) {
            throw new ArithmeticException("Divide by zero");
        }
        return a.doubleValue() / b.doubleValue();
    }

    public static <T extends Number, U extends Number> double pow(T a, U b) {
        return Math.pow(a.doubleValue(), b.doubleValue());
    }

    public static void main(String[] args) {
        System.out.println(Calculator.sum(5, 4));
        System.out.println(Calculator.subtract(5, 11));
        System.out.println(Calculator.multiply(5, 5));
        System.out.println(Calculator.divide(5, 5));
        System.out.println(Calculator.pow(2, 4));
    }
}
