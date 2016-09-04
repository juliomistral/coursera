package unionfind;

import unionfind.algo.QuickFindUF;
import unionfind.algo.WeightedQuickFindUF;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class UnionFindDriver {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("../resources/input_Q2.txt"));

        int numberOfNodes = in.nextInt();
        UnionFind algo = new WeightedQuickFindUF(numberOfNodes);

        prettyPrint(algo.results());
        while(in.hasNext()) {
            int pId = in.nextInt();
            int qId = in.nextInt();

            if (!algo.connected(pId, qId)) {
                algo.union(pId, qId);
            }
            prettyPrint(algo.results());
        }

    }

    private static void prettyPrint(int[] results) {
        for (int i = 0; i < results.length; i++){
            System.out.print(results[i]);
            System.out.print(" ");
        }
        System.out.println("");
    }


}
