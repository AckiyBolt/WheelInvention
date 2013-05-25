package ua.org.bolt.collection;

/**
 * Class UnstorableCollection
 *
 * @author AckiyBolt
 *         Created: 24.05.13 23:40
 */
public interface UnstorableCollection<T> {

    void push(T obj);
    T pop();
    T peek();
    int size();
}
