import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INITIAL_SIZE = 2;
    private Item[] items;
    private int last;


    private class RandomizedIterator<Item> implements Iterator<Item> {
        private int current;
        private int[] randomIndexes;


        public RandomizedIterator() {
            int itemsSize = RandomizedQueue.this.size();
            current = 0;
            randomIndexes = new int[itemsSize];

            for (int i = 0; i < itemsSize; i++) {
                randomIndexes[i] = i;
            }

            StdRandom.shuffle(randomIndexes);
        }

        @Override
        public boolean hasNext() {
            return current <= last;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int randomIndex = randomIndexes[current];
            Item value = (Item) items[randomIndex];

            current++;
            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.items = (Item[]) new Object[INITIAL_SIZE];
        this.last = -1;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedIterator<Item>();
    }

    // is the queue empty?
    public boolean isEmpty() {
        return this.size() == 0;
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
        resizeItemsArrayUpIfNeeded();

        this.items[this.last] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        assertNotEmptyQueue();

        int randomIdx = generateRandomIndex();
        Item item = this.items[randomIdx];

        this.items[randomIdx] = this.items[last];
        this.items[last] = null;

        this.last--;
        resizeItemsArrayDownIfNeeded();

        return item;
    }

    private int generateRandomIndex() {
        int randomIdx;
        if (this.last == 0) {
            randomIdx = 0;
        } else {
            randomIdx = StdRandom.uniform(0, this.last);
        }
        return randomIdx;
    }

    private void resizeItemsArrayUpIfNeeded() {
        if (this.last != this.items.length) return;

        int redoubledSize = this.items.length * 2;
        Item[] newItems = (Item[]) new Object[redoubledSize];

        copyAndResetItemsArray(newItems);
    }

    private void resizeItemsArrayDownIfNeeded() {
        if (this.last <= 0) return;
        if (this.items.length == INITIAL_SIZE) return;


        if (this.last == this.size() / 4) {
            int quarterSize = this.items.length / 4;
            Item[] newItems = (Item[]) new Object[quarterSize + 1];

            copyAndResetItemsArray(newItems);
        }

    }

    private void copyAndResetItemsArray(Item[] newItems) {
        for (int i = 0; i < this.last; i++) {
            newItems[i] = this.items[i];
        }
        this.items = newItems;
    }

    // return (but do not remove) a random item
    public Item sample() {
        assertNotEmptyQueue();

        int randomIdx = generateRandomIndex();
        return this.items[randomIdx];
    }

    private void assertNotEmptyQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }
}
