import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PercolationTest {
    private Percolation percolator;
    public @Rule ExpectedException thrown = ExpectedException.none();


    @Test
    public void shoudlThrowExceptionInvalidGridSize() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        percolator = new Percolation(-1);
    }

    @Test
    public void shouldThrowExceptionIfRowIsInvalid() {
        thrown.expect(IndexOutOfBoundsException.class);

        percolator = new Percolation(4);
        percolator.isOpen(10, 1);
    }

    @Test
    public void shouldThrowExceptionIfColIsInvalid() {
        thrown.expect(IndexOutOfBoundsException.class);

        percolator = new Percolation(4);
        percolator.isOpen(1, 10);
    }

    @Test
    public void shouldOpenUpNodeAtCoordinates() {
        percolator = new Percolation(4);
        percolator.open(2, 3);
        assertThat(percolator.isOpen(2, 3), is(true));

        percolator.open(1, 4);
        assertThat(percolator.isOpen(1, 4), is(true));
    }

    @Test
    public void shouldRegisterNodeAsFullIfTopNeighborIsFull() {
        percolator = new Percolation(4);

        // given: a top level node is open
        percolator.open(1, 2);

        // then: the top level node is also full
        assertThat(percolator.isFull(1,2), is(true));

        // when: the bottom neighbor is opened
        percolator.open(2, 2);

        // then: the bottom neighbor is also full
        assertThat(percolator.isFull(2,2), is(true));

        percolator.open(4, 2);
        assertThat(percolator.isFull(4, 2), is(false));
    }

    @Test
    public void shouldNotRegisterNodeAsFullIfTopNeighborIsNotFull() {
        percolator = new Percolation(4);

        percolator.open(2, 2);
        assertThat(percolator.isOpen(2, 2), is(true));
        assertThat(percolator.isFull(2,2), is(false));

        percolator.open(1, 2);
        assertThat(percolator.isFull(2,2), is(true));
    }
}
