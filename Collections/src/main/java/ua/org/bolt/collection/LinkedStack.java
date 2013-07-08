package ua.org.bolt.collection;

/**
 * Class LinkedStack
 *
 * @author AckiyBolt
 */
public class LinkedStack<T> implements UnstorableCollection<T> {

    private Element head;
    private int size;

    public LinkedStack() {
        head = new Element(null, null);
        head.previous = head;
    }

    @Override
    public void push(T element) {
        if (element == null)
            throw new IllegalArgumentException("Null is not allowed.");

        this.head = new Element(element,head);
        this.size++;
    }

    @Override
    public T pop() {

        size--;

        Element buffHeadElement = head;
        head = buffHeadElement.previous;

        return buffHeadElement.value;
    }

    @Override
    public T peek() {
        return head.value;
    }

    @Override
    public int size() {
        return size;
    }

    private class Element {

        private T value;
        private Element previous;

        Element (T value, Element previous) {
            this.value = value;
            this.previous = previous;
        }
    }

    @Override
    public String toString() {

        if (head == null)
            return "h[]";

        return "h[" + toStringHelper("",head) + " ]";
    }

    private String toStringHelper (String actualString, Element element) {
        if (element.value == null)
            return actualString;
        return toStringHelper (actualString + " " + element.value, element.previous);
    }
}
