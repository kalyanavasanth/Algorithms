package com.algorithms.dynamicConnectivity;

public class QuickFind {
    int[] arr;
    int n;
    public QuickFind(int n) {
        this.n = n;
        this.arr = new int[n];
        for(int i=0;i<n;i++) {
            this.arr[i] = i;
        }
    }

    /**
     * @param p - source node
     * @param q - target node
     * @return - true if p and q are connected
     */
    public boolean isConnected(int p, int q) {
        return this.arr[p] == this.arr[q];
    }

    /**
     * @param p - node to be connected
     * @param q - node to be connected
     * Connects 2 elements in a network. Connected
     */
    public void union(int p, int q) {
        int pVal = arr[p];
        int qVal = arr[q];
        for(int i=0;i<n;i++) {
            if(this.arr[i] == pVal) {
                this.arr[i] = qVal;
            }
        }
    }
}
