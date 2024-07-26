package com.algorithms.stacksAndQueues;

public interface Queue<Item> {
    public void enqueue(Item s);

    public Item dequeue();

    public boolean isEmpty();

    public int length();
}
