import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int INITIAL_SIZE = 2;
    private Item[] items;
    private int last;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.items = (Item[]) new Object[INITIAL_SIZE];
        this.last = -1;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return this.last == -1;
    }

    // return the number of items on the queue
    public int size() {
        return this.last + 1;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        this.last++;
        this.items[this.last] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        assertNotEmptyQueue();

        Item item;
        int randomIdx;

        if (this.last == 0) {
            randomIdx = 0;
        } else {
            randomIdx = StdRandom.uniform(0, this.last);
        }

        item = this.items[randomIdx];
        this.items[randomIdx] = this.items[last];
        this.items[last] = null;

        this.last--;
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        assertNotEmptyQueue();

        int randomIdx = StdRandom.uniform(0, this.last);
        return this.items[randomIdx];
    }

    private void assertNotEmptyQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    public static void main(String[] args) {

    }
}
