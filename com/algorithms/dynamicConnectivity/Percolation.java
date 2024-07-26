package com.algorithms.dynamicConnectivity;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[] grid;
    private int n;
    private WeightedQuickUnionUF uf;
    /**
     * @param n
     * creates n-by-n grid, with all sites initially blocked
     */
    public Percolation(int n) {
        if(n<=0) {
            throw new IllegalArgumentException("Grid should be a minimum 1x1 grid. So the value should be >=1");
        }
        this.n = n;
        grid = new int[(n*n)+2];
        for(int i=0;i<(n*n)+2;i++) {
            grid[i] = 0;
        }
        uf = new WeightedQuickUnionUF((n*n)+2);
        for(int x=1;x<=n;x++) {
            uf.union(0,x);
        }
        for(int x=(n*(n-1))+1;x<=n*n;x++) {
            uf.union((n*n)+1,x);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        illegalArgumentCheck(row, col);
        int elementIndex = (row-1)*n + col;
        grid[elementIndex] = elementIndex;
        if(elementIndex%n!=1 && grid[elementIndex-1]!=0 && elementIndex-1!=0){
            uf.union(elementIndex,elementIndex-1);
        }
        if(elementIndex%n!=0 && grid[elementIndex+1]!=0 && elementIndex+1!=(n*n)+2){
            uf.union(elementIndex,elementIndex+1);
        }
        if(elementIndex>n && grid[elementIndex-n]!=0 && elementIndex-n!=0) {
            uf.union(elementIndex,elementIndex-n);
        }
        if(elementIndex<=(n*n-n) && grid[elementIndex+n]!=0 && elementIndex+n!=(n*n)+2) {
            uf.union(elementIndex,elementIndex+n);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int elementIndex = (row-1)*n + col;
        illegalArgumentCheck(row, col);
        return grid[elementIndex] != 0;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int elementIndex = (row-1)*n + col;
        illegalArgumentCheck(row, col);
        return grid[elementIndex] != 0 && uf.connected(0, elementIndex);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int numOfOpenSites = 0;
        for(int i=1;i<=n*n;i++) {
            if(grid[i]!=0) {
                numOfOpenSites++;
            }
        }
        return numOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0,(n*n)+1);
    }

    private void illegalArgumentCheck(int r, int c) {
        if (r<1 || c<1) {
            throw new IllegalArgumentException("Row or Column should be >= 1");
        }
    }
}
