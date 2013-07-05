package ua.org.bolt.collection.tree;

/**
 * Class BinaryTree
 * Used manual: <a href="http://habrahabr.ru/post/65617/">habrahabr.ru/post/65617</a>
 *
 * @author AckiyBolt
 */

public class BinaryTree <K extends Comparable<K>, V> {

    private int size;
    Node<K, V> root;

    protected Node<K, V> getRoot() {
        return root;
    }

    public V get (K key) {

        Node<K, V> node = root;

        while (node != null) {

            int cmp = key.compareTo(node.key);

            if (cmp == 0)
                return node.value;

            node = (cmp < 0) ? node.left : node.right;
        }

        return null;
    }

    public void put (K key, V value) {

        Node<K, V> newNode, lower, upper;
        upper = lower = root;

        while (upper != null) {

            int cmp = key.compareTo(upper.key);
            lower = upper;

            switch (cmp) {
                case -1 :
                    upper = upper.left;
                    break;
                case 1 :
                    upper = upper.right;
                    break;
                default:
                    upper.value = value;
                    return;
            }
        }

        newNode = new Node<K, V>(key, value);

        if (lower == null)
            root = newNode;
        else
            if (key.compareTo(lower.key) < 0)
                lower.left = newNode;
            else
                lower.right = newNode;
        size++;
    }

    public void remove(K key) {
        Node<K, V> upper = root, lower = null;

        loop: while (upper != null) {

            int cmp = key.compareTo(upper.key);
            lower = upper;

            switch (cmp) {
                case -1 :
                    upper = upper.left;
                    break;
                case 1 :
                    upper = upper.right;
                    break;
                default:
                    break loop;
            }
        }

        if (upper == null) return;

        if (upper.right == null)

            if (lower == null)
                root = upper.left;
            else
                if (upper == lower.left)
                    lower.left = upper.left;
                else
                    lower.right = upper.left;
        else {

            Node<K, V> leftMost = upper.right;
            lower = null;

            while (leftMost.left != null) {
                lower = leftMost;
                leftMost = leftMost.left;
            }

            if (lower != null)
                lower.left = leftMost.right;
            else
                upper.right = leftMost.right;

            upper.key = leftMost.key;
            upper.value = leftMost.value;
        }

        this.size--;
    }

    public int size () {
        return size;
    }

    protected static class Node <K, V> {
        protected K key;
        protected V value;
        Node<K, V> left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Node)) return false;

            Node other = (Node) obj;

            return key.equals(other.key) && value.equals(other.value);
        }

        @Override
        public int hashCode() {
            int result = key.hashCode();
            result = 31 * result + value.hashCode();
            return result;
        }
    }
}
