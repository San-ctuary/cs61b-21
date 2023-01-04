package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    /**
     * real node in LinkedListDequ echo node with a prev point and a next point
     */
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        Node(T item) {
            this.item = item;
            this.prev = this;
            this.next = this;
        }

        public T get(int index) {
            if (index == 0) {
                return this.item;
            }
            return this.next.get(index - 1);
        }

        public boolean equals(Object o) {
            if (!(o instanceof LinkedListDeque.Node)) {
                return false;
            }
            return this.item.equals(((Node) o).item);
        }
    }

    private Node sentinel;
    private int size;


    public LinkedListDeque() {
        Node node = new Node(null);
        this.sentinel = node;
    }


    public void addFirst(T item) {
        size += 1;
        Node node = new Node(item);
        Node preFirstNode = this.sentinel.next;
        node.next = preFirstNode;
        preFirstNode.prev = node;
        this.sentinel.next = node;
        node.prev = this.sentinel;
    }

    public void addLast(T item) {
        size += 1;
        Node node = new Node(item);
        Node preLastNode = this.sentinel.prev;
        preLastNode.next = node;
        node.prev = preLastNode;
        node.next = this.sentinel;
        this.sentinel.prev = node;
    }


    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = this.sentinel.next;
        while (p != this.sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Node node = this.sentinel.next;
        Node next = this.sentinel.next.next;
        this.sentinel.next = next;
        next.prev = this.sentinel;
        return node.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Node node = this.sentinel.prev;
        Node prev = this.sentinel.prev.prev;
        this.sentinel.prev = prev;
        prev.next = this.sentinel;
        return node.item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node p = this.sentinel;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return this.sentinel.next.get(index);
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node p = sentinel.next;

        @Override
        public boolean hasNext() {
            return p != sentinel;
        }

        @Override
        public T next() {
            T returnItem = p.item;
            p = p.next;
            return returnItem;
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque other = (Deque) o;
        if (this.size != other.size()) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            T t = this.get(i);
            Object o1 = other.get(i);
            if (!(t.equals(o1))) {
                return false;
            }
        }
        return true;
    }


}
