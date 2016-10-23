import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private static final int INITIAL_DEQUE_SIZE = 20;
    private int size;
    private Node first;
    private Node last;


    public Deque() {
        this.size = 0;
    }

    // return an iterator over items in order from front to end
    private class DequeIterator implements Iterator<Item> {
        private Node current;

        public DequeIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item currentItem = current.item;
            current = current.next;

            return currentItem;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    public boolean isEmpty() { return size == 0; }

    // add the item to the front
    public void addFirst(Item item) {
        assertItem(item);

        if (isEmpty()) {
            initFirstItem(item);
        } else {
            Node newFirst = new Node(item);

            newFirst.next = this.first;
            newFirst.next.previous = newFirst;
            this.first = newFirst;
        }

        this.size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        assertItem(item);

        if (isEmpty()) {
            initFirstItem(item);
        } else {
            Node newLast = new Node(item);

            newLast.previous = this.last;
            newLast.previous.next = newLast;
            this.last = newLast;
        }

        this.size++;
    }

    private void initFirstItem(Item item) {
        Node initial = new Node(item);
        this.first = initial;
        this.last = initial;
    }

    private void assertItem(Item item) {
        if (item == null) {
            throw new NullPointerException("Passed in item was NULL!");
        }
    }

    private void assertNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("Can't remove items from an empty deque!");
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        assertNotEmpty();

        Item removed = this.first.item;
        this.size--;

        if (isEmpty()) {
            clearPointers();
        } else {
            this.first = this.first.next;
            this.first.previous = null;
        }

        return removed;
    }

    // remove and return the item from the end
    public Item removeLast() {
        assertNotEmpty();

        Item removed = this.last.item;
        this.size--;

        if (isEmpty()) {
            clearPointers();
        } else {
            this.last = this.last.previous;
            this.last.next = null;
        }

        return removed;
    }

    private void clearPointers() {
        this.first = null;
        this.last = null;
    }

    private class Node {
        private Item item;
        private Node next;
        private Node previous;

        public Node(Item item) {
            this.item = item;
        }
    }
}
