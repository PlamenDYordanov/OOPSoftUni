package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class GiftFactoryTests {
    private static final Gift gift1 = new Gift("Christmas", 10);
    private static final Gift gift2 = new Gift("Wooden", 20);
    private static final Gift gift3 = new Gift("Iron", 30);
    GiftFactory giftFactory;


    @Before
    public void prepareTest() {
        giftFactory = new GiftFactory();
        giftFactory.createGift(gift1);
    }

    @Test(expected =  IllegalArgumentException.class)
    public void testCreateGiftThrowException() {
        giftFactory.createGift(gift1);
    }
    @Test
    public void testGetCount() {
        Assert.assertEquals(1, giftFactory.getCount());
    }
    @Test
    public void testRemoveGift() {
        giftFactory.createGift(gift2);
      Assert.assertTrue(giftFactory.removeGift("Wooden"));
    }
    @Test(expected =  NullPointerException.class)
    public void testRemoveGiftThrowException() {
        giftFactory.removeGift(null);
    }
    @Test
    public void testGetPresentWithLeastMagic(){
        giftFactory.createGift(gift2);
        Gift presentWithLeastMagic = giftFactory.getPresentWithLeastMagic();
        Assert.assertEquals("Christmas", presentWithLeastMagic.getType());
    }
    @Test
    public void testGetPresent() {
        Gift christmas = giftFactory.getPresent("Christmas");
        Assert.assertEquals("Christmas", christmas.getType());
    }
    @Test
    public void testGetPresents() {
        giftFactory.removeGift("Christmas");
        giftFactory.createGift(gift3);
        Collection<Gift> presents = giftFactory.getPresents();
        Assert.assertEquals("Iron", presents.iterator().next().getType());
    }

}
