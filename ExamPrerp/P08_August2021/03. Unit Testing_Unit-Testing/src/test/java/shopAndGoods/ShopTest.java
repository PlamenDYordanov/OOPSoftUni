package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ShopTest {
    private static final Goods bean = new Goods("Bean", "123");
    private static final Goods potato = new Goods("Potato", "1234");
    private static final Goods tomato = new Goods("Tomato", "12345");
    Shop shop;

    @Before
    public void prepareTest() throws OperationNotSupportedException {
        shop = new Shop();
        shop.addGoods("Shelves1", bean);
    }
    @Test
    public void testGetShelves() {
        Map<String, Goods> shelves = shop.getShelves();
        String name = shelves.get("Shelves1").getName();
        assertEquals("Bean", name);

    }
    @Test
    public void test_CreateShop_Successfully() {
        int expectedSize = 12;

        assertEquals(expectedSize, shop.getShelves().size());
    }
    @Test
    public void testAddGoods() throws OperationNotSupportedException {
        shop.addGoods("Shelves2", potato);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testAddGoodThrowExceptionShelfDoesntExist() throws OperationNotSupportedException {
        shop.addGoods("asd" , bean);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testAddGoodThrowExceptionShelfIs() throws OperationNotSupportedException {
        shop.addGoods("Shelves1", potato);
    }
    @Test (expected = OperationNotSupportedException.class)
    public void testAddGoodThrowExceptionGood() throws OperationNotSupportedException {
        shop.addGoods("Shelves2",bean);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsThrowExceptionGoodDeosentExist() {
        shop.removeGoods("Shelves2", bean);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsThrowExceptionShelfDoesntExist() {
        shop.removeGoods("asdasd", bean);
    }
    @Test
    public void testRemoveGoods() {
        String removedGood = shop.removeGoods("Shelves1", bean);
        Assert.assertNull(shop.getShelves().get("Shelves1"));
        String expected = "Goods: 123 is removed successfully!";
        assertEquals(expected, removedGood);
    }
}
