package unionfind;

import unionfind.algo.QuickFindUF;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class UnionFindDriver {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("../resources/input_Q2.txt"));

        int numberOfNodes = in.nextInt();
        UnionFind algo = new QuickFindUF(numberOfNodes);

        while(in.hasNext()) {
            int pId = in.nextInt();
            int qId = in.nextInt();

            if (!algo.connected(pId, qId)) {
                algo.union(pId, qId);
            }
        }

        System.out.print(Arrays.toString(algo.results()));
    }


}
