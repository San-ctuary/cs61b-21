package deque;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDequeTest {
    @Test
    public void integerComparatorTest() {
        MaxArrayDeque<Integer> integerMaxArrayDeque = new MaxArrayDeque<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        integerMaxArrayDeque.addFirst(new Integer(1));
        integerMaxArrayDeque.addFirst(new Integer(2));
        integerMaxArrayDeque.addFirst(new Integer(4));
        integerMaxArrayDeque.addLast(new Integer(400));
        Assert.assertEquals(integerMaxArrayDeque.max(), new Integer(400));
        Comparator<Integer> minComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };

        Assert.assertEquals(integerMaxArrayDeque.max(), new Integer(400));
        Assert.assertEquals(integerMaxArrayDeque.max(minComparator), new Integer(1));
        integerMaxArrayDeque.removeLast();
        Assert.assertEquals(integerMaxArrayDeque.max(), new Integer(4));
        Iterator<Integer> iterator = integerMaxArrayDeque.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
        int idx = 0;
        while (iterator.hasNext()) {
            Assert.assertEquals(integerMaxArrayDeque.get(idx), iterator.next());
            idx += 1;
        }

    }

    @Test
    public void iteratorTest() {
        Comparator<Integer> minComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };
        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<Integer>(minComparator);
        for (int i = 0; i < 100; i++) {
            lld1.addLast(i);
        }
        Iterator<Integer> iterator = lld1.iterator();
        int idx = 0;
        while (iterator.hasNext()) {
            Assert.assertEquals(lld1.get(idx), iterator.next());
            System.out.println(lld1.get(idx));
            idx += 1;
        }
    }
}
