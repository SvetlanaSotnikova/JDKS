package Sem3.Task3;

import java.util.Iterator;
import java.util.List;

public class MyIterator<T> implements Iterator<T> {

    private final List<T> list;
    private int index;

    public MyIterator(List<T> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public T next() {
        return list.get(index++);
    }

    @Override
    public void remove() {
        list.remove(index--);
    }
}
