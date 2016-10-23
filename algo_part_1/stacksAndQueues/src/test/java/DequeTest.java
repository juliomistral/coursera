import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class DequeTest {
    Deque<String> deque;
    public @Rule ExpectedException thrown = ExpectedException.none();


    @Before
    public void setup() {
        this.deque = new Deque<String>();
    }

    @Test
    public void shouldThrowNPEIfAddingFirstANullValue() {
        thrown.expect(NullPointerException.class);
        deque.addFirst(null);
    }

    @Test
    public void shouldThrowNPEIfAddingLastANullValue() {
        thrown.expect(NullPointerException.class);
        deque.addLast(null);
    }

    @Test
    public void shouldThrowNoSuchElementExceptionIfRemovingFirstFromEmptyDeque() {
        thrown.expect(NoSuchElementException.class);
        deque.removeFirst();
    }

    @Test
    public void shouldThrowNoSuchElementExceptionIfRemovingLastFromEmptyDeque() {
        thrown.expect(NoSuchElementException.class);
        deque.removeLast();
    }

    @Test
    public void shouldThrowNotSupporteExceptionIfCallRemoveOnIterator() {
        thrown.expect(UnsupportedOperationException.class);
        Iterator<String> iter = deque.iterator();
        iter.remove();
    }

    @Test
    public void emptyDequeShouldReturnSizeZero() throws Exception {
        assertThat(deque.size()).isEqualTo(0);
    }

    @Test
    public void shouldIncreaseSizeAsItemsAreAddedToDeque() {
        deque.addFirst("foo");
        deque.addFirst("bar");
        assertThat(deque.size()).isEqualTo(2);

        Deque<String> dequeLast = new Deque<String>();
        dequeLast.addLast("bar");
        dequeLast.addLast("foo");
        assertThat(dequeLast.size()).isEqualTo(2);
    }

    @Test
    public void shouldRemoveItemFromDequeWithRemoveFirst() {
        deque.addFirst("foo");
        deque.addLast("bar");

        String result = deque.removeFirst();

        assertThat(result).isEqualTo("foo");
        assertThat(deque.removeFirst()).isEqualTo("bar");
    }

    @Test
    public void shouldDecrementSizeAsItemsAreRemovedFromDeque() {
        deque.addFirst("foo");
        deque.addLast("boo");

        deque.removeFirst();
        assertThat(deque.size()).isEqualTo(1);

        deque.removeLast();
        assertThat(deque.size()).isEqualTo(0);
    }

    @Test
    public void shouldRemoveItemFromDequeWithRemoveLast() {
        deque.addFirst("foo");
        deque.addLast("bar");

        String result = deque.removeLast();

        assertThat(result).isEqualTo("bar");
        assertThat(deque.removeLast()).isEqualTo("foo");
    }

    @Test
    public void shouldReturnFirstToLastItemsUsingTheIteratior() {
        deque.addFirst("foo");
        deque.addLast("middle");
        deque.addLast("bar");

        Iterator<String> iter = deque.iterator();
        assertThat(iter.next()).isEqualTo("foo");
        assertThat(iter.next()).isEqualTo("middle");
        assertThat(iter.next()).isEqualTo("bar");
    }

    @Test
    public void shouldReturnTrueIfIteratorHasMoreItems() {
        deque.addFirst("foo");
        deque.addLast("middle");
        deque.addLast("bar");

        Iterator<String> iter = deque.iterator();
        assertThat(iter.hasNext()).isEqualTo(true);

        iter.next();
        assertThat(iter.hasNext()).isEqualTo(true);
    }

    @Test
    public void shouldReturnFalseIfIteratorHasNoMoreItems() {
        deque.addFirst("foo");

        Iterator<String> iter = deque.iterator();
        assertThat(iter.hasNext()).isEqualTo(true);

        iter.next();
        assertThat(iter.hasNext()).isEqualTo(false);
    }

    @Test
    public void shouldThrowNoSuchElementIfNextCalledAndNoMoreItemsInIterator() {
        thrown.expect(NoSuchElementException.class);

        Iterator<String> iter = deque.iterator();
        iter.next();
    }
}
