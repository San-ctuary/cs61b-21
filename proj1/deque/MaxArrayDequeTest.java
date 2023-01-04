package deque;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

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
    }
}
