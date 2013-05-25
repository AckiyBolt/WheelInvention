package ua.org.bolt.collection.exception;

/**
 * Class CollectionIsFullException
 *
 * @author AckiyBolt
 *         Created: 25.05.13 0:34
 */
public class CollectionIsFullException extends RuntimeException {

    public CollectionIsFullException() {
        super();
    }

    public CollectionIsFullException(String s) {
        super(s);
    }
}
