import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private static final int INITIAL_DEQUE_SIZE = 20;
    private int size;
    private Node first;
    private Node last;


    private class Node {
        Item item;
        Node previous;
        Node next;
    }

    public Deque() {
        this.size = 0;
    }

    // return an iterator over items in order from front to end
    @Override
    public Iterator<Item> iterator() {
        return null;
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

    private boolean isEmpty() { return size == 0; }

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
}
