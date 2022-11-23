/*
package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    Database database;
    private static final Integer[] NUMBERS = {1, 2, 3, 4};
    private static final Integer ABOVE_CAPACITY_NUMBER = 17;
    private static final Integer ZERO_CAPACITY = 0;

    @Before
    public void prepareTest() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }

    //Happy path
    @Test
    public void testConstructorCreateValidDatabase() {
        Integer[] testArr = database.getElements();
        Assert.assertArrayEquals(NUMBERS, testArr);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testCapacityIsBiggerThan16ThrowException() throws OperationNotSupportedException {
        Integer[] testArr = new Integer[ABOVE_CAPACITY_NUMBER];
        new Database(testArr);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testCapacityLessThan1ThrowException() throws OperationNotSupportedException {
        Integer[] testArr = new Integer[ZERO_CAPACITY];
        new Database(testArr);
    }

    @Test
    public void testAddElementInDataBase() throws OperationNotSupportedException {
        database.add(6);
        Integer[] elements = database.getElements();
        Assert.assertEquals(Integer.valueOf(6), elements[elements.length - 1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testCurrentAddElementIsThrowsEx() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testIfRemoveNumberFromEmptyDatabaseThrowsException() throws OperationNotSupportedException {


        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }
        database.remove();
    }

    @Test
    public void testRemoveNumberFormDB() throws OperationNotSupportedException {
        database.remove();
        Integer[] testArr = database.getElements();
        Assert.assertEquals(testArr.length, NUMBERS.length - 1);
        Assert.assertArrayEquals(database.getElements(), testArr);
    }

}*/
