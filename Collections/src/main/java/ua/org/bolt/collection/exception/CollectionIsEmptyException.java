package ua.org.bolt.collection.exception;

/**
 * Class CollectionIsEmptyException
 *
 * @author AckiyBolt
 *         Created: 25.05.13 0:34
 */
public class CollectionIsEmptyException extends RuntimeException {

    public CollectionIsEmptyException() {
        super();
    }

    public CollectionIsEmptyException(String s) {
        super(s);
    }
}
