package unionfind;

public interface UnionFind {
    boolean connected(int pId, int qId);

    void union(int pId, int qId);

    int[] results();
}
