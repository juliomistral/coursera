package unionfind.algo;

import unionfind.UnionFind;

public class QuickUnionUF implements UnionFind {
    int[] parentIds;

    public QuickUnionUF(int numberOfNodes) {
        this.parentIds = new int[numberOfNodes];

        for (int i = 0; i < numberOfNodes; i++) {
            parentIds[i] = i;
        }
    }

    private int root(int id) {
        while (parentIds[id] != id) {
            id = parentIds[id];
        }
        return id;
    }

    @Override
    public boolean connected(int pId, int qId) {
        return root(pId) == root(qId);
    }

    @Override
    public void union(int pId, int qId) {
        int pRootId = root(pId);
        int qRootId = root(qId);

        parentIds[pRootId] = qRootId;
    }

    @Override
    public int[] results() {
        return this.parentIds;
    }
}
