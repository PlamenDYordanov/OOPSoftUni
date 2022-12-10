package toyStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Map;

public class ToyStoryTest {
    private static  final  Toy toy1 = new Toy("Adidas", "123");
    private static  final  Toy toy2 = new Toy("Nike", "1234");
    private static  final  Toy toy3 = new Toy("Pumba", "12345");

    ToyStore toyStore;

    @Before
    public void testPrepare() throws OperationNotSupportedException {
        toyStore = new ToyStore();
        toyStore.addToy("A", toy1);
    }
    @Test
    public void testGetToyShelf() {
        Map<String, Toy> toyShelf = toyStore.getToyShelf();
        Assert.assertEquals("123",toyShelf.get("A").getToyId());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddThrowException() throws OperationNotSupportedException {
        toyStore.addToy("asd", toy1);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testAddThrowExceptionShelfAlreadyTaken() throws OperationNotSupportedException {
        toyStore.addToy("A", toy2);
    }
    @Test (expected = OperationNotSupportedException.class)
    public void testAddThrowEx() throws OperationNotSupportedException {
        toyStore.addToy("B", toy1);
    }
    @Test
    public void testSuccessfulAdd() throws OperationNotSupportedException {
        String addToy = toyStore.addToy("B", toy2);
        Assert.assertEquals("Toy:1234 placed successfully!", addToy);
    }
    @Test(expected =  IllegalArgumentException.class)
    public void testRemove() {
        toyStore.removeToy("PO", toy1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveThrow() {
        toyStore.removeToy("A", toy3);
    }
    @Test
    public void testRemoveSuccessful() {
        String toy = toyStore.removeToy("A", toy1);
        Assert.assertNull(toyStore.getToyShelf().get("A"));
        Assert.assertEquals("Remove toy:123 successfully!", toy);
    }
}