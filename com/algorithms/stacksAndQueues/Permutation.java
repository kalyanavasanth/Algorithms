package com.algorithms.stacksAndQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = 0;
        if(args.length < 1) {
            k = StdIn.readInt();
        }
        k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        while(!StdIn.isEmpty()) {
            String word = StdIn.readString();
            rq.enqueue(word);
        }
        Iterator itr = rq.iterator();
        for(int i=1;i<=k;i++){
            StdOut.println(itr.next());
        }
    }
}
