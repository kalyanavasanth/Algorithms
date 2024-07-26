package com.algorithms.dynamicConnectivity;

public class UnionFindImproved {
    int[] arr;
    int[] sz;

    /**
     * Constructor
     * @param n - number of nodes in the network
     */
    public UnionFindImproved(int n) {
        arr = new int[n];
        sz = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = i;
            sz[i] = 1;
        }
    }

    /**
     * @param x - node, whose root is to be determined
     * @return - the root of node x
     */
    public int root(int x) {;
        while(arr[x]!=x) {
            //path compression: point every other node to point to it's grandparent
            arr[x] = arr[arr[x]];
            x = arr[x];
        }
        return x;
    }

    /**
     *
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
     * The tree with less weight will be the child of the tree with relatively more weight
     */
    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ) {
            return;
        }
        if(sz[rootP] < sz[rootQ]) {
            arr[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
        } else {
            arr[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
        }
    }

}
