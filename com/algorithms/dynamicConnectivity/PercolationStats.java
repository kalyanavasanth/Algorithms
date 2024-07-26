package com.algorithms.dynamicConnectivity;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int n, trials;
    private double pValues[];

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        illegalArgumentCheck(n, trials);
        this.n = n;
        this.trials = trials;
        this.pValues = new double[trials];
        for(int x=0;x<trials;x++) {
            this.pValues[x] = 0;
        }
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int randRow = StdRandom.uniformInt(1, n + 1);
                int randCol = StdRandom.uniformInt(1, n + 1);
                if (!p.isOpen(randRow, randCol)) {
                    p.open(randRow, randCol);
                }
            }
            this.pValues[i] = (double) p.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.pValues);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.pValues);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double mean = this.mean();
        double stdDev = this.stddev();
        return (mean - (1.96 * stdDev)/Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double mean = this.mean();
        double stdDev = this.stddev();
        return (mean + (1.96 * stdDev)/Math.sqrt(trials));
    }

    private void illegalArgumentCheck(int r, int c) {
        if (r<1 || c<1) {
            throw new IllegalArgumentException("Row or Column should be >= 1");
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        int grid = 0;
        int trials = 0;
        if(args.length>=2) {
            grid = Integer.parseInt(args[0]);
            trials = Integer.parseInt(args[1]);
        } else {
            grid = StdIn.readInt();
            trials = StdIn.readInt();
        }

        double[] pVal = new double[trials];
        if (grid < 1) {
            throw new IllegalArgumentException("n value should be >= 1");
        }
        PercolationStats pStats = new PercolationStats(grid, trials);
        StdOut.println("mean=" + pStats.mean());
        StdOut.println("stddev=" + pStats.stddev());
        StdOut.println("95% confidence interval=[" + pStats.confidenceLo() + "," + pStats.confidenceHi() + "]");
    }
}
