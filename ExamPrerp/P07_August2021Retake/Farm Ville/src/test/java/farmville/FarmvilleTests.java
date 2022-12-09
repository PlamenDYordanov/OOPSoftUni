package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {
    private static final  Animal  animal1 = new Animal("Cow", 10);
    private static final  Animal  animal2 = new Animal("Lion", 20);
    private static final  Animal  animal3 = new Animal("Tiger", 20);
    Farm farm;

    @Before
    public void prepareTest() {
        farm = new Farm("Farm", 20);
        farm.add(animal1);
    }
    @Test
    public void testGetCount() {
        Assert.assertEquals(1, farm.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalThrowException() {
        farm = new Farm("Farm", 1);
        farm.add(animal1);
        farm.add(animal2);
    }
    @Test (expected = IllegalArgumentException.class)
    public void addTestAddAnimalThrowExceptionExistAnimal(){
        farm.add(animal1);
    }
    @Test
    public void testRemove() {
       Assert.assertTrue(farm.remove("Cow"));

    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityBelowZero() {
        farm = new Farm("Farm", -1);
    }
    @Test(expected = NullPointerException.class)
    public void testSetNameThrowException() {
        farm = new Farm(null, -1);
    }
    @Test
    public void testGetName() {
        Assert.assertEquals("Farm", farm.getName());
    }
}
