package com.algorithms.stacksAndQueues;

public interface Stack<Item> {
    public void push(Item s);

    public Item pop();

    public boolean isEmpty();

    public int length();
}
