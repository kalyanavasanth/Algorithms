package com.algorithms.dynamicConnectivity;

public class UnionFind {
    int[] arr;

    /**
     * Constructor
     * @param n - number of nodes in the network
     */
    public UnionFind(int n) {
        arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = i;
        }
    }

    /**
     * @param x - node, whose root is to be determined
     * @return - the root of node x
     */
    public int root(int x) {;
        while(arr[x]!=x) {
            x = arr[x];
        }
        return x;
    }

    /**
     * @param p - source node
     * @param q - target node
     * @return - true if p and q are connected
     */
    public boolean isConnected(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        return rootP == rootQ;
    }

    /**
     * @param p - node to be connected
     * @param q - node to be connected
     * Connects 2 elements in a network.
     */
    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ) {
            return;
        }
        arr[rootP] = rootQ;
    }

}
