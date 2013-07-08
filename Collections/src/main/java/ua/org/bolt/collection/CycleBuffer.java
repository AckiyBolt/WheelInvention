package ua.org.bolt.collection;

import ua.org.bolt.collection.exception.CollectionIsEmptyException;
import ua.org.bolt.collection.exception.CollectionIsFullException;

/**
 * Class CycleBuffer
 *
 * @author AckiyBolt
 */
public class CycleBuffer<T> implements UnstorableCollection<T> {

    private int initialCapacity;
    private int size;
    private Element head;
    private Element tail;

    /**
     * Constructs an empty buffer with the unbound initial capacity
     */
    public CycleBuffer() {
        this(0);
    }

    /**
     * Constructs an empty buffer with the specified initial capacity
     */
    public CycleBuffer(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        Element empty = new Element(null, null);
        this.tail = empty;
        this.head = empty;
        this.head.nextElement = empty;
    }

    @Override
    public void push(T obj) {

        if (obj == null)
            throw new IllegalArgumentException("Null is not allowed");

        if (initialCapacity > 0 && size >= initialCapacity)
            throw new CollectionIsFullException("Buffer is full");

        pushElement(new Element(obj, this.tail));

        this.size++;
    }

    private void pushElement(Element element){
        this.tail.nextElement = element;
        this.tail = this.tail.nextElement;
    }

    @Override
    public T pop() {

        if ( size <= 0 )
            throw new CollectionIsEmptyException("Buffer is empty");

        replaceEmptyElement();

        T pop = this.head.value;
        this.head = this.head.nextElement;

        this.size--;

        return pop;
    }

    private void replaceEmptyElement() {
        if (head.value == null) {
            Element nextElement = head.nextElement;
            pushElement(head);
            head = nextElement;
        }
    }

    @Override
    public T peek() {

        if ( size <= 0 )
            throw new CollectionIsEmptyException("Buffer is empty");

        replaceEmptyElement();

        return head.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    private class Element {
        private T value;
        private Element nextElement;

        private Element(T value, Element nextElement) {
            this.value = value;
            this.nextElement = nextElement;
        }
    }
}
