package com.algorithms.sorting;

public class SelectionSort {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for(int i=0; i<N;i++) {
            int min = i;
            for(int j=i+1;j<N;j++) {
                if(lessThan(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
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
        SelectionSort.sort(a);
        for(int i=0;i<a.length;i++) {
            System.out.println(a[i]);
        }
    }
}
