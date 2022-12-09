package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {
    private static final Cat ricky = new Cat("Ricky");
    private static final Cat mikey = new Cat("Mikey");
    private static final Cat leo = new Cat("Leo");

    House house;

    @Before
    public void prepareTest() {
        house = new House("Home", 10);
        house.addCat(ricky);
    }
   @Test(expected = NullPointerException.class)
    public void testSetName() {
        house = new House(null, 10);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCapacity() {
        house = new House("ASD", -1);
    }
    @Test
    public void testGetCount() {
        Assert.assertEquals(1, house.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddCatThrowException() {
       house = new House("asd", 0);
        house.addCat(ricky);
    }
    @Test
    public void testRemoveCat() {
        house.removeCat("Ricky");
        Assert.assertEquals(0, house.getCount());

    }
    @Test (expected = IllegalArgumentException.class)
    public void testRemoveCatThrowException() {
        house.removeCat("Ceco");
    }
    @Test
    public void testCatForSale() {
        Cat cat = house.catForSale("Ricky");
        Assert.assertFalse(cat.isHungry());
        Assert.assertEquals("Ricky",cat.getName());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleThrowException() {
       house.catForSale("Ceco");
    }
    @Test
    public void testStatistics() {
        Assert.assertEquals("The cat Ricky is in the house Home!", house.statistics());
    }
    @Test
    public void testGetCapacity() {
        Assert.assertEquals(10, house.getCapacity());
    }
    @Test
    public void testGetName() {
        Assert.assertEquals("Home", house.getName());
    }
}
