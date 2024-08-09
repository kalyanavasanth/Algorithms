package com.algorithms.sorting;

public class ShellSort {
    public static void sort(Comparable a[]) {
        int N = a.length;
        int h = 1;
        while(h<N/3) {
            h = 3*h+1;
        }

        while(h>=1) {
            for(int i=h; i<N; i++) {
                for(int j=i; j>=h && lessThan(a[j], a[j-h]); j-=h) {
                    exch(a, j, j-h);
                }
            }
            h=h/3;
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static boolean lessThan(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Integer a[] = {7,6,1,4,2,3,5,8,9,0};
        ShellSort.sort(a);
        for(int i=0;i<a.length;i++) {
            System.out.println(a[i]);
        }
    }


}
