import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
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
            queue.enqueue("foo");
            queue.enqueue("foo");
            queue.enqueue("foo");
            queue.enqueue("foo");
            queue.enqueue("foo");
            queue.enqueue("foo");
            queue.enqueue("bar");

            List<String> results = new ArrayList<String>();
            while (!queue.isEmpty()) {
                results.add(queue.dequeue());
            }

            assertThat(results).hasSize(7);
            assertThat(results).contains("foo", "foo", "foo", "foo", "foo", "foo", "bar");
        }
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
}