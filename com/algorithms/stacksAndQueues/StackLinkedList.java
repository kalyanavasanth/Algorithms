package com.algorithms.stacksAndQueues;


import java.util.Iterator;

public class StackLinkedList<Item> implements Stack<Item>, Iterable<Item> {
    private Node<Item> first;
    public StackLinkedList() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item s) {
        Node<Item> oldFirst = first;
        first = new Node(s);
        first.next = oldFirst;
    }

    public Item pop() {
        if(first == null) {
            throw new UnsupportedOperationException("Cannot pop() on an empty stack.");
        }
        Item val = first.item;
        first = first.next;
        return val;
    }

    public int length() {
        Node<Item> itr = first;
        int length = 1;
        if(isEmpty()) {
            return 0;
        }
        while(itr.next != null) {
            itr = itr.next;
            length++;
        }
        return length;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node<Item> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if(current == null) {
                throw new UnsupportedOperationException("Cannot iterate an empty stack.");
            }
            Item val = current.item;
            current = current.next;
            return val;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove is unsupported!");
        }
    }

    public static void main(String args[]) {
        StackLinkedList<String> ll = new StackLinkedList<String>();
        ll.push("abc");
        ll.push("def");
        System.out.println(ll.length());
        System.out.println("pop():"+ll.pop());
        System.out.println(ll.length());
        System.out.println("pop():"+ll.pop());
        System.out.println(ll.length());
    }
}
