import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


public class RandomizedQueueTest {
    RandomizedQueue<String> queue;
    public @Rule ExpectedException thrown = ExpectedException.none();


    @Before
    public void setup() {
        queue = new RandomizedQueue<String>();
    }

    @Test
    public void shouldThrowNPEIfNullValueIsEnqueued() throws Exception {
        thrown.expect(NullPointerException.class);
        queue.enqueue(null);
    }

    @Test
    public void shouldThrowUnsupporteOpdExceptionWhenCallingRemoveOnIterator() {
        thrown.expect(UnsupportedOperationException.class);

        Iterator<String> iter = queue.iterator();
        iter.remove();
    }

    @Test
    public void shouldThrowNSEEIfSampleIsCalledOnEmptytQueue() {
        thrown.expect(NoSuchElementException.class);
        queue.sample();
    }

    @Test
    public void shouldThrowNSEEIfDequeueIsCalledOnEmptytQueue() {
        thrown.expect(NoSuchElementException.class);
        queue.dequeue();
    }

    @Test
    public void shouldIncrementSizeAsItemsAreEnqueued() {
        assertThat(queue.size()).isEqualTo(0);

        queue.enqueue("foo");
        assertThat(queue.size()).isEqualTo(1);

        queue.enqueue("bar");
        assertThat(queue.size()).isEqualTo(2);
    }

    @Test
    public void shouldDecrementSizeAsItemsAreDequeued() {
        queue.enqueue("foo");
        queue.enqueue("bar");
        assertThat(queue.size()).isEqualTo(2);

        queue.dequeue();
        assertThat(queue.size()).isEqualTo(1);

        queue.dequeue();
        assertThat(queue.size()).isEqualTo(0);
    }

    @Test
    public void shouldReturnTrueWhenCallingIsEmptyOnEmptyQueue() {
        assertThat(queue.isEmpty()).isTrue();
        queue.enqueue("foo");
    }

    @Test
    public void shouldReturnFalseWhenCallingIsEmptyOnFilledQueue() {
        queue.enqueue("foo");
        assertThat(queue.isEmpty()).isFalse();
    }

    @Test
    public void shouldReturnAllItemsThatWereEnqueued() {
        for (int run = 0; run < 10; run++) {
            RandomizedQueue<String> runQueue = new RandomizedQueue<String>();
            runQueue.enqueue("foo1");
            runQueue.enqueue("foo2");
            runQueue.enqueue("foo3");
            runQueue.enqueue("foo4");
            runQueue.enqueue("foo5");
            runQueue.enqueue("foo6");
            runQueue.enqueue("bar");

            List<String> results = new ArrayList<String>();
            while (!runQueue.isEmpty()) {
                results.add(runQueue.dequeue());
            }

            assertThat(results).hasSize(7);
            assertThat(results).contains("foo1", "foo2", "foo3", "foo4", "foo5", "foo6", "bar");
        }
    }

    @Test
    public void shouldReturnFalseIfNoItemsLeftInIterator() {
        queue.enqueue("foo");
        queue.enqueue("middle");
        queue.enqueue("bar");

        Iterator<String> iterator = queue.iterator();
        assertThat(iterator.hasNext()).isTrue();

        iterator.next();
        assertThat(iterator.hasNext()).isTrue();

        iterator.next();
        assertThat(iterator.hasNext()).isTrue();

        iterator.next();
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void shouldReturnAllItemsThatWereEnqueuedUsingIterator() {
        for (int run = 0; run < 10; run++) {
            RandomizedQueue runQueue = new RandomizedQueue();
            runQueue.enqueue("foo1");
            runQueue.enqueue("foo2");
            runQueue.enqueue("foo3");
            runQueue.enqueue("foo4");
            runQueue.enqueue("foo5");
            runQueue.enqueue("foo6");
            runQueue.enqueue("bar");

            Iterator<String> iterator = runQueue.iterator();
            List<String> results = new ArrayList<String>();
            while (iterator.hasNext()) {
                results.add(iterator.next());
            }

            assertThat(results).hasSize(7);
            assertThat(results).contains("foo1", "foo2", "foo3", "foo4", "foo5", "foo6", "bar");
        }
    }
}