import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


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
        assertThat(deque.size(), is(0));
    }

    @Test
    public void shouldIncreaseSizeAsItemsAreAddedToDeque() {
        deque.addFirst("foo");
        deque.addFirst("bar");
        assertThat(deque.size(), is(2));

        Deque<String> dequeLast = new Deque<String>();
        dequeLast.addLast("bar");
        dequeLast.addLast("foo");
        assertThat(dequeLast.size(), is(2));
    }

    @Test
    public void shouldRemoveItemFromDequeWithRemoveFirst() {
        deque.addFirst("foo");
        deque.addLast("bar");

        String result = deque.removeFirst();

        assertThat(result, is("foo"));
        assertThat(deque.removeFirst(), is("bar"));
    }

    @Test
    public void shouldDecrementSizeAsItemsAreRemovedFromDeque() {
        deque.addFirst("foo");
        deque.addLast("boo");

        deque.removeFirst();
        assertThat(deque.size(), is(1));

        deque.removeLast();
        assertThat(deque.size(), is(0));
    }

    @Test
    public void shouldRemoveItemFromDequeWithRemoveLast() {
        deque.addFirst("foo");
        deque.addLast("bar");

        String result = deque.removeLast();

        assertThat(result, is("bar"));
        assertThat(deque.removeLast(), is("foo"));
    }

    @Test
    public void shouldReturnFirstToLastItemsUsingTheIteratior() {
        deque.addFirst("foo");
        deque.addLast("middle");
        deque.addLast("bar");

        Iterator<String> iter = deque.iterator();
        assertThat(iter.next(), is("foo"));
        assertThat(iter.next(), is("middle"));
        assertThat(iter.next(), is("bar"));
    }

    @Test
    public void shouldReturnTrueIfIteratorHasMoreItems() {
        deque.addFirst("foo");
        deque.addLast("middle");
        deque.addLast("bar");

        Iterator<String> iter = deque.iterator();
        assertThat(iter.hasNext(), is(true));

        iter.next();
        assertThat(iter.hasNext(), is(true));
    }

    @Test
    public void shouldReturnFalseIfIteratorHasNoMoreItems() {
        deque.addFirst("foo");

        Iterator<String> iter = deque.iterator();
        assertThat(iter.hasNext(), is(true));

        iter.next();
        assertThat(iter.hasNext(), is(false));
    }

    @Test
    public void shouldThrowNoSuchElementIfNextCalledAndNoMoreItemsInIterator() {
        thrown.expect(NoSuchElementException.class);

        Iterator<String> iter = deque.iterator();
        iter.next();
    }
}
