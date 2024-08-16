package Sem3.Task2;

import java.util.Arrays;

public class MyArray<T> {
    private Object[] array;
    private int size;

    public MyArray(int size) {
        this.array = new Object[size];
    }

    public void addToArray(T element) {
        if (size >= array.length) {
            array = Arrays.copyOf(array, array.length * 2);

        }
        array[size++] = element;
    }

    public void removeFromArray(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                array[i] = array[--size];
                break;
            }
        }
    }
}
