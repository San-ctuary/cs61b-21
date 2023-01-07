package flik;

import org.junit.Assert;
import org.junit.Test;

public class FlikTest {
    @Test
    public void IntegerTest() {
        Assert.assertTrue(Flik.isSameNumber(1, 1));
//        Assert.assertTrue(Flik.isSameNumber(128, 128));
    }
}
