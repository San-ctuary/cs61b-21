package tester;

import static org.junit.Assert.*;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import java.lang.invoke.VarHandle;

public class TestArrayDequeEC {
    @Test
    public void randomizedTest() {
        ArrayDequeSolution<Integer> L = new ArrayDequeSolution<Integer>();
        StudentArrayDeque<Integer> bugList = new StudentArrayDeque<Integer>();
        int N = 50000;
        String message = "";
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 5);
            if (operationNumber == 0) {
                // addLast
                Integer randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                bugList.addLast(randVal);
                message += "addFirst(" + randVal + ")" + "\n";
            } else if (operationNumber == 1) {
                assertEquals(L.size(), bugList.size());
            } else if (operationNumber == 2) {
                // addFirst
                Integer randVal = StdRandom.uniform(0, 100);
                L.addFirst(randVal);
                bugList.addFirst(randVal);
                message += "addFirst(" + randVal + ")" + "\n";
            } else if (operationNumber == 3) {
                if (L.size() == 0)
                    continue;

                Integer last = L.removeLast();
                Integer buglast = bugList.removeLast();
                message += "removeLast()" + "\n";
                assertEquals(message, last, buglast);
//                System.out.println("removeLast(" + last + ")");
            } else if (operationNumber == 4) {
                if (L.size() == 0)
                    continue;
                Integer first = L.removeFirst();
                Integer bugfirst = bugList.removeFirst();
                message += "removeFirst()" + "\n";
                assertEquals(message, first, bugfirst);
            }
        }
    }
}
