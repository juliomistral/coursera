package unionfind.algo;

import unionfind.UnionFind;

public class QuickFindUF implements UnionFind {
    private int[] rootIds;


    public QuickFindUF(int numberOfNodes) {
        rootIds = new int[numberOfNodes];
        for (int i =  0; i < numberOfNodes; i++) {
            rootIds[i] = i;
        }
    }

    @Override
    public boolean connected(int pId, int qId) {
        return rootIds[pId] == rootIds[qId];
    }

    @Override
    public void union(int pId, int qId) {
        int toFindRootId = rootIds[pId];
        int replaceWithRootId = rootIds[qId];

        for (int i = 0; i < rootIds.length; i++) {
            if (rootIds[i] == toFindRootId) {
                rootIds[i] = replaceWithRootId;
            }
        }
    }

    @Override
    public int[] results() {
        return this.rootIds;
    }
}
