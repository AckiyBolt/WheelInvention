package ua.org.bolt.generics;

import java.util.ArrayList;
import java.util.List;

public abstract class Box<T> {

    protected List<T> elements;

    protected Box () {
        elements = new ArrayList<T>();
    }

    public T pop() {
        return this.elements.remove(0);
    }

    public void put( T element ) {
        this.elements.add(element);
    }

    public int size() {
        return elements.size();
    }

    public void clear () {
        elements.clear();
    }
}
