package com.algorithms.stacksAndQueues;

public class StackDynamicArray implements Stack<String>{
    private int N;
    private String[] items;
    public StackDynamicArray() {
        N = 0;
        items = new String[1];
    }

    public boolean isEmpty() {
        return N==0;
    }

    public void push(String s) {
        if(N == items.length-1) {
            String[] newItems = new String[items.length*2];
            for(int i=0;i<items.length;i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }
        items[N++]=s;
    }

    public String pop() {
        if(N == 0) {
            throw new UnsupportedOperationException("Cannot pop() on an empty stack.");
        }
        if(N <= items.length/4) {
            String[] newItems = new String[items.length/2];
            for(int i=0;i<N;i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }
        return items[--N];
    }

    public int length() {
        return N;
    }

    public static void main(String args[]) {
        StackDynamicArray st = new StackDynamicArray();
        st.push("abc");
        st.push("def");
        st.push("x");
        st.push("y");
        st.push("z");
        st.push("o");
        System.out.println(st.length());
        System.out.println("pop():"+st.pop());
        System.out.println(st.length());
        System.out.println("pop():"+st.pop());
        System.out.println(st.length());
        System.out.println("pop():"+st.pop());
        System.out.println(st.length());
        System.out.println("pop():"+st.pop());
        System.out.println(st.length());
        System.out.println("pop():"+st.pop());
        System.out.println(st.length());
        System.out.println("pop():"+st.pop());
        System.out.println(st.length());
    }
}
