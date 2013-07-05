package ua.org.bolt.collection.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BinaryHeap<T> {

    private List<T> elements;

    public BinaryHeap() {
        elements = new LinkedList<>();
    }

    public BinaryHeap(Collection<T> elements) {
        this.elements = new ArrayList<>(elements.size());
        addAll(elements);
    }

    public void add(T element) {

        elements.add(element);
        int i = size() - 1;
        int parent = (i - 1) / 2;

        while (i > 0 && isValueByLeftIndexLowerThanRight(parent, i)) {
            T temp = elements.get(i);
            elements.set(i, elements.get(parent));
            elements.set(parent, temp);

            i = parent;
            parent = (i - 1) / 2;
        }
    }

    public void addAll(Collection<T> elements) {

        this.elements.addAll(elements);

        for (int i = size() / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    public T popRoot() {
        T result = elements.remove(0);
        if (size() > 0) {
            elements.add(0, elements.remove(size() - 1));
            heapify(0);
        }
        return result;
    }

    protected void heapify(int rootIndex) {

        int leftChild;
        int rightChild;
        int largestChild;

        for (; ; ) {
            leftChild = 2 * rootIndex + 1;
            rightChild = 2 * rootIndex + 2;
            largestChild = rootIndex;

            if (leftChild < size() && isValueByLeftIndexBiggerThanRight(leftChild, largestChild)) {
                largestChild = leftChild;
            }

            if (rightChild < size() && isValueByLeftIndexBiggerThanRight(rightChild, largestChild)) {
                largestChild = rightChild;
            }

            if (largestChild == rootIndex) {
                break;
            }

            T temp = elements.get(rootIndex);
            elements.set(rootIndex, elements.get(largestChild));
            elements.set(largestChild, temp);
            rootIndex = largestChild;
        }
    }

    public int size() {
        return elements.size();
    }

    protected List<T> getAsList() {
        return elements;
    }

    private boolean isValueByLeftIndexBiggerThanRight(int left, int right) {
        return ((Comparable) elements.get(left)).compareTo(elements.get(right)) > 0;
    }

    private boolean isValueByLeftIndexLowerThanRight(int left, int right) {
        return !isValueByLeftIndexBiggerThanRight(left, right);
    }
}
