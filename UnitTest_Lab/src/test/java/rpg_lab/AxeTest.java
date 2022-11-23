package rpg_lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AxeTest {
    private final static  int ATTACK_POINTS_AXE = 10;
    private final  static  int DURABILITY_POINTS_AXE = 10;
    private final  static  int DURABILITY_BROKEN_AXE = 0;
    private final  static  int DUMMY_HEALTH = 10;
    private final  static  int DUMMY_XP = 10;
    private Axe axe;
    private Dummy dummy;
    @Before
    public void initializeTestObjects() {
        this.axe = new Axe(ATTACK_POINTS_AXE, DURABILITY_POINTS_AXE);
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP);
    }

    @Test
    public void testWeaponLoseDurabilityPointsAfterAttack() {

        axe.attack(dummy);

        Assert.assertEquals(DURABILITY_POINTS_AXE - Axe.DURABILITY_DECREASE_AFTER_HIT, axe.getDurabilityPoints());

    }
    @Test(expected =  IllegalStateException.class)
    public void testAttackWithBrokenWeapon(){
        Axe axe = new Axe(ATTACK_POINTS_AXE,DURABILITY_BROKEN_AXE);

        axe.attack(dummy);
    }

}