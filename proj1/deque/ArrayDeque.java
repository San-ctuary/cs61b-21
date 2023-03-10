package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private int size;
    private int firstPos;
    private int lastPos;
    private double maxFactor = 0.75;
    private double minFactor = 0.25;
    private T[] array;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        firstPos = array.length - 1;
        lastPos = 0;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int oldArrayLength = array.length;
        for (int i = 0; i < size; i++) {
            a[i] = array[(firstPos + i + 1) % oldArrayLength];
        }
        array = a;
        firstPos = array.length - 1;
        lastPos = size;
    }

    public void addFirst(T item) {
        if (size > array.length * maxFactor) {
            resize(size * 2);
        }
        size += 1;
        array[firstPos] = item;
        firstPos = (firstPos - 1 + array.length) % array.length;
    }

    public void addLast(T item) {
        if (size > array.length * maxFactor) {
            resize(size * 2);
        }
        size += 1;
        array[lastPos] = item;
        lastPos = (lastPos + 1) % array.length;
    }


    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[(firstPos + i + 1) % array.length]);
        }
        System.out.println();
    }

    public T removeFirst() {
        if (array.length > 16 && size < array.length * minFactor) {
            resize(array.length / 2);
        }
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T ans = array[(firstPos + 1) % array.length];
        array[(firstPos + 1) % array.length] = null;
        firstPos = (firstPos + 1) % array.length;
        return ans;
    }

    public T removeLast() {
        if (array.length > 16 && size < array.length * minFactor) {
            resize(array.length / 2);
        }
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T ans = array[(lastPos - 1 + array.length) % array.length];
        array[(lastPos - 1 + array.length) % array.length] = null;
        lastPos = (lastPos - 1 + array.length) % array.length;
        return ans;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        T ans = array[(firstPos + 1 + index) % array.length];
        return ans;
    }


    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {

        private int wizPos;

        ArrayDequeIterator() {
            this.wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T returnItem = array[(firstPos + 1 + wizPos) % array.length];
            wizPos += 1;
            return returnItem;
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque other = (Deque) o;
        if (this.size() != other.size()) {
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
