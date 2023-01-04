package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque {

    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        this.comparator = c;
    }

    public T max() {
        if (this.size() == 0)
            return null;
        T maxItem = (T) this.get(0);
        for (int i = 1; i < this.size(); i++) {
            T other = (T) this.get(i);
            if (comparator.compare(maxItem, other) < 0) {
                maxItem = other;
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (this.size() == 0)
            return null;
        T maxItem = (T) this.get(0);
        for (int i = 1; i < this.size(); i++) {
            T other = (T) this.get(i);
            if (c.compare(maxItem, other) < 0) {
                maxItem = other;
            }
        }
        return maxItem;
    }
}
