package com.algorithms.stacksAndQueues;

public class QueueLinkedList<Item> implements Queue<Item>{
    private Node<Item> first;
    private Node<Item> last;
    public QueueLinkedList() {
        first = null;
        last = null;
    }

    public void enqueue(Item s) {
        Node oldLast = last;
        last = new Node(s);
        if(first == null) {
            first = last;
            return;
        }
        oldLast.next = last;
    }

    public Item dequeue() {
        if(isEmpty()) {
            throw new UnsupportedOperationException("Cannot Dequeue an empty queue!");
        }
        Item item = first.item;
        first = first.next;
        if(isEmpty()) {
            last = null;
        }
        return item;
    }

    public boolean isEmpty() {
        return first==null;
    }

    public int length() {
        if(isEmpty()) {
            return 0;
        }
        int length = 1;
        Node<Item> itr = first;
        while(itr.next != null) {
            itr = itr.next;
            length++;
        }
        return length;
    }

    public static void main(String args[]) {
        QueueLinkedList<String> q = new QueueLinkedList<String>();
        q.enqueue("abc");
        q.enqueue("x");
        q.enqueue("y");
        q.enqueue("z");
        System.out.println("dequeue():"+q.dequeue());
        System.out.println("dequeue():"+q.dequeue());
        System.out.println("length:"+q.length());
    }
}
