package unionfind.algo;

import unionfind.UnionFind;


/**
 * Weighted quick union:  Compare trees and link root of smaller tree as child of larger tree
 */
public class WeightedQuickFindUF implements UnionFind {
    int[] parentIds;
    int[] size;


    public WeightedQuickFindUF(int numberOfNodes) {
        this.parentIds = new int[numberOfNodes];
        this.size = new int[numberOfNodes];

        for (int i = 0; i < numberOfNodes; i++) {
            parentIds[i] = i;
            size[i] = 1;
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

        if (pRootId == qRootId) {
            return;
        }

        if (isFirstRootLighter(pRootId, qRootId)) {
            parentIds[pRootId] = qRootId;
            size[qRootId] += size[pRootId];
        } else {
            parentIds[pRootId] = qRootId;
            size[pRootId] += size[qRootId];
        }
    }

    private boolean isFirstRootLighter(int firstRootId, int secondRootId) {
        return parentIds[firstRootId] > parentIds[secondRootId];
    }

    @Override
    public int[] results() {
        return this.parentIds;
    }
}
