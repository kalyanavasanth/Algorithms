package com.algorithms.stacksAndQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

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

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first==null && last==null;
    }

    // return the number of items on the deque
    public int size() {
        if(isEmpty()) {
            return 0;
        }
        int length = 1;
        Node2<Item> itr = first;
        while(itr.next != null) {
            itr = itr.next;
            length++;
        }
        return length;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if(item == null) {
            throw new IllegalArgumentException("Invalid argument for add first method");
        }
        Node2<Item> oldFirst = first;
        first = new Node2<>(item);
        first.next = oldFirst;
        if(oldFirst != null) {
            oldFirst.prev = first;
        }
        first.prev = null;
        if(last==null) {
            last = first;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if(item == null) {
            throw new IllegalArgumentException("Invalid argument for add first method");
        }
        Node2<Item> oldLast = last;
        Node2<Item> newLast = new Node2<Item>(item);
        if(first == null) {
            first = newLast;
            last = newLast;
            return;
        }
        oldLast.next = newLast;
        newLast.prev = oldLast;
        last=newLast;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if(first == null) {
            throw new NoSuchElementException("Cannot remove from the front of an empty Deque!");
        }
        Item ele = first.item;
        first = first.next;
        if(first!=null){
            first.prev = null;
        }
        if (first == null) {
            last = null;
        }
        return ele;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if(last == null) {
            throw new NoSuchElementException("Cannot remove from the last of an empty Deque!");
        }
        Item ele = last.item;
        last = last.prev;
        if(last!=null) {
            last.next = null;
        }
        if(last == null) {
            first = null;
        }
        return ele;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node2<Item> current = first;

        public boolean hasNext() {
            if(current != null) {
                return true;
            }
            return false;
        }

        public Item next() {
            Item itm = null;
            if(current == null) {
                throw new NoSuchElementException("No elements left in the Deque!");
            }
            if(hasNext()){
                itm = current.item;
                current = current.next;
            }
            return itm;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is unsupported on Deque!");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> d = new Deque<>();
        d.addLast("1");
        d.removeFirst();
        System.out.println(d.isEmpty());
        Iterator i = d.iterator();
        while (i.hasNext()) {
            System.out.print("->"+i.next());
        }
    }

}