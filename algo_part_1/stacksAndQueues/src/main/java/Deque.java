import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private static final int INITIAL_DEQUE_SIZE = 20;
    private int size;
    private Node first;
    private Node last;


    private class Node {
        private Item item;
        private Node previous;
        private Node next;
    }

    public Deque() {
        this.size = 0;
    }

    // return an iterator over items in order from front to end
    private class DequeIterator implements Iterator<Item> {
        Node current;

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

    // add the item to the front
    public void addFirst(Item item) {
        assertItem(item);

        Node oldFirst = this.first;

        this.first = new Node();
        this.first.item = item;
        this.first.previous = null;

        if (oldFirst == null) {
            this.last = this.first;
        }
        else {
            oldFirst.previous = this.first;
            this.first.next = oldFirst;
        }

        this.size++;
    }

    public boolean isEmpty() { return size == 0; }

    // add the item to the end
    public void addLast(Item item) {
        assertItem(item);

        Node oldLast = this.last;

        this.last = new Node();
        this.last.item = item;
        this.last.next = null;

        if (oldLast == null) {
            this.first = this.last;
        }
        else {
            oldLast.next = this.last;
            this.last.previous = oldLast;
        }

        this.size++;
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
        this.first = this.first.next;

        this.size--;
        return removed;
    }

    // remove and return the item from the end
    public Item removeLast() {
        assertNotEmpty();

        Item removed = this.last.item;
        this.last = this.last.previous;

        this.size--;
        return removed;
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        int result = -1;

        deque.addFirst(0);
        deque.addFirst(1);
        assert(!deque.isEmpty());

        result = deque.removeLast(); //      ==> 0
        assert(result == 0);

        result = deque.removeLast(); //      ==> 1
        assert(result == 1);

        deque.addFirst(5);
        assert(deque.removeLast() == 5);
    }
}
