package p03_IteratorTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ListIteratorTest {
    private static final String[] CONSTANT_ELEMENT = {"Three" , "Milk"};
    private static final String[] CONSTANT_ELEMENT_EMPTY = {};
    private static final String[] CONSTANT_ELEMENT_ONE_ELEMENT = {"Milk"};
    private ListIterator listIterator;
    @Before
    public void prepareTest() throws OperationNotSupportedException {
      listIterator = new ListIterator(CONSTANT_ELEMENT);
    }
    @Test(expected = OperationNotSupportedException.class)
    public  void testConstructorThrowNullPointerException() throws OperationNotSupportedException {
        listIterator = new ListIterator(null);
        listIterator.hasNext();
    }
    @Test
     public void testHasNextFunctionality() {
        Assert.assertTrue(listIterator.hasNext());
        listIterator.move();
        Assert.assertFalse(listIterator.hasNext());
    }
    @Test
    public void testMoveFunctionality() {
        Assert.assertTrue(listIterator.move());
        Assert.assertFalse(listIterator.move());
    }
    @Test(expected = IllegalStateException.class)
    public void testPrintFunctionalityWithZeroSize() throws OperationNotSupportedException {
        listIterator = new ListIterator(CONSTANT_ELEMENT_EMPTY);
        listIterator.print();
    }
    @Test
    public void testPrintFunctionalityWithElements() throws OperationNotSupportedException {
        listIterator = new ListIterator(CONSTANT_ELEMENT_ONE_ELEMENT);

        Assert.assertEquals(listIterator.print(),"Milk");



    }


}