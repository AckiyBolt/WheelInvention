package ua.org.bolt.collection;

/**
 * Class UnstorableCollection
 *
 * @author AckiyBolt
 */
public interface UnstorableCollection<T> {

    void push(T obj);
    T pop();
    T peek();
    int size();
}
