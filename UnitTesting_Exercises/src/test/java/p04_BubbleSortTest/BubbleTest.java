package p04_BubbleSortTest;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleTest {
    @Test
    public void testProperWorkOfBubbleSort() {
        int[] testArr = {1, 3, 0, 5, 2};
        Bubble.sort(testArr);
        int[] expect = {0, 1, 2, 3, 5};

        Assert.assertArrayEquals(expect, testArr);
    }

}