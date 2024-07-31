package com.algorithms.sorting;

public class InsertionSort {
    public static void sort(Comparable a[]) {
        int N = a.length;
        for(int i=1;i<N;i++) {
            for(int j=i;j>0;j--) {
                if(lessThan(a[j], a[j-1])) {
                    exch(a, j, j-1);
                }
            }
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
        Integer a[] = {7,6,1,4,2,3};
        InsertionSort.sort(a);
        for(int i=0;i<a.length;i++) {
            System.out.println(a[i]);
        }
    }
}
