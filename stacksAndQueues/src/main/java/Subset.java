import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("usage:  Subset [size]");
            return;
        }

        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int size = Integer.parseInt(args[0]);

        for (int i = 0; i < size; i++) {
            String input = StdIn.readString();
            queue.enqueue(input);
        }

        while (!queue.isEmpty()) {
            StdOut.println(queue.dequeue());
        }
    }
}
