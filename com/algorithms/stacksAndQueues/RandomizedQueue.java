package com.algorithms.stacksAndQueues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private class Node2<Item> {
        Item item;
        Node2<Item> next;
        Node2<Item> prev;
        public Node2(Item item) {
            this.item = item;
            this.prev = null;
            this.next = null;
        }
    }

    private Node2<Item> first;
    private Node2<Item> last;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if(item == null) {
            throw new IllegalArgumentException("Invalid argument for add first method");
        }
        Node2<Item> oldLast = last;
        Node2<Item> newLast = new Node2<Item>(item);
        size++;
        if(first == null) {
            first = newLast;
            last = newLast;
            return;
        }
        oldLast.next = newLast;
        newLast.prev = oldLast;
        last = newLast;
    }

    // remove and return a random item
    public Item dequeue() {
        if(size == 0) {
            throw new NoSuchElementException("Cannot dequeue on an empty Randomized Queue!");
        }
        int rand = StdRandom.uniformInt(1, this.size+1);
        Node2<Item> nodeToDequeue = getNodeAtPosition(rand, first);
        size--;
        return removeAndReturn(nodeToDequeue);
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if(size == 0) {
            throw new NoSuchElementException("Cannot sample on an empty Randomized Queue!");
        }
        int rand = StdRandom.uniformInt(1, this.size+1);
        return getNodeAtPosition(rand, first).item;
    }

    private Node2<Item> getNodeAtPosition(int x, Node2<Item> firstNode) {
        Node2<Item> itm = firstNode;
        for(int i=1;i<x;i++) {
            itm = itm.next;
        }
        return itm;
    }

    private Item removeAndReturn(Node2<Item> nodeToRemove) {
        Item valueToReturn = nodeToRemove.item;
        Node2<Item> previousNode = nodeToRemove.prev;
        Node2<Item> nextNode = nodeToRemove.next;
        if(previousNode != null) {
            previousNode.next = nextNode;
        }
        if(nextNode != null) {
            nextNode.prev = previousNode;
        }
        if(previousNode == null && nextNode != null) {
            first = nextNode;
        }
        if(nextNode == null && previousNode != null) {
            last = previousNode;
        }
        if(previousNode == null && nextNode == null) {
            first = null;
            last = null;
        }
        return valueToReturn;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Node2<Item> copiedFirst;
        private int itemsRemaining;

        public RandomizedQueueIterator() {
            itemsRemaining = 0;
            if(first == null) {
                return;
            }
            copiedFirst = new Node2<>(first.item);
            Node2<Item> tmp = copiedFirst;
            Node2<Item> mainListPointer = first;
            itemsRemaining++;
            for(int i=1;i<size;i++){
                if(mainListPointer.next!=null) {
                    Item val = mainListPointer.next.item;
                    Node2<Item> tmpNode = new Node2<>(val);
                    tmp.next = tmpNode;
                    Node2<Item> prevNode = tmp;
                    tmp = tmp.next;
                    tmp.prev = prevNode;
                    mainListPointer = mainListPointer.next;
                    itemsRemaining++;
                }
            }
        }

        public boolean hasNext() {
            return itemsRemaining > 0;
        }

        public Item next() {
            Item itm=null;
            if(hasNext()) {
                int rand = StdRandom.uniformInt(1, itemsRemaining+1);
                Node2<Item> tmp = getNodeAtPosition(rand, copiedFirst);
                itm = removeAndReturnItr(tmp);
                itemsRemaining--;
            }
            Node2<Item> x = copiedFirst;
            return itm;
        }

        private Item removeAndReturnItr(Node2<Item> nodeToRemove) {
            Item valueToReturn = nodeToRemove.item;
            Node2<Item> previousNode = nodeToRemove.prev;
            Node2<Item> nextNode = nodeToRemove.next;
            if(previousNode != null) {
                previousNode.next = nextNode;
            }
            if(nextNode != null) {
                nextNode.prev = previousNode;
            }
            if(previousNode == null && nextNode != null) {
                copiedFirst = nextNode;
            }
            if(previousNode == null && nextNode == null) {
                copiedFirst = null;
            }
            return valueToReturn;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is unsupported on Randomized Queue!");
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int j = 1; j <= 10; j++)
            queue.enqueue(j);


        Iterator<Integer> iterator = queue.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}