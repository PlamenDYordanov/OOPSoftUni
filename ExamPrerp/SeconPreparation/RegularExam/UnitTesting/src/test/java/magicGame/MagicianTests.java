package magicGame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MagicianTests {
    private static  final  Magic magic1 = new Magic("Iris", 50);
    private static  final  Magic magic2 = new Magic("Calypso", 50);
    private static  final  Magic magic3 = new Magic("Flame", 50);

    Magician magician;

    @Before
    public void prepareTest () {
        magician = new Magician("Plamen", 200);
        magician.addMagic(magic1);
    }
    @Test
    public void setUsername() {
        Assert.assertEquals("Plamen",magician.getUsername());
    }
    @Test(expected = NullPointerException.class)
    public void testThrowExceptionSetNameNull() {
        magician = new Magician(null, 300);
    }
    @Test
    public void testGetHealth() {
        Assert.assertEquals(200, magician.getHealth());
    }
    @Test(expected =  IllegalArgumentException.class)
    public void testHealthThrowExceptionNegativeNumber() {
        magician = new Magician("Ivan", -1);
    }
    @Test
    public  void testGetMagics() {
        magician.addMagic(magic2);
        List<Magic> magics = magician.getMagics();
        Assert.assertEquals("Calypso", magics.get(1).getName());
    }
    @Test
    public void takeDamage() {
        magician.takeDamage(1);
        Assert.assertEquals(199, magician.getHealth());
    }
    @Test
    public void testDamageAboveHealth() {
        magician.takeDamage(201);
        Assert.assertEquals(0, magician.getHealth());
    }
    @Test(expected = IllegalStateException.class)
    public void testDamageMagicianDead(){
        magician.takeDamage(200);
        magician.takeDamage(200);
    }
    @Test(expected = NullPointerException.class)
    public void testAddMagicThrowException() {
        magician.addMagic(null);
    }
    @Test
    public void removeMagicTrue() {
       Assert.assertTrue(magician.removeMagic(magic1));
    }
    @Test
    public void removeMagicFalse() {
        Assert.assertFalse(magician.removeMagic(magic3));
    }
    @Test
    public void testGetMagic() {
        Magic iris = magician.getMagic("Iris");
            Assert.assertEquals(50, iris.getBullets());
    }
    @Test
    public void testGetMagicNull() {
        Magic magic = magician.getMagic("FIRE");
        Assert.assertNull(magic);
    }

    //TODO
}
