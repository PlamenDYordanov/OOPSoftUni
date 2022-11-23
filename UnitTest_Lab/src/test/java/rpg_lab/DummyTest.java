package rpg_lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.http.HttpRequest;

import static org.junit.Assert.*;

public class DummyTest {
    private final static  int ATTACK_POINTS_AXE = 10;
    private final  static  int DURABILITY_POINTS_AXE = 10;
    private final static int DEAD_DUMMY_HEALTH = 0;
    private final  static  int DUMMY_HEALTH = 10;
    private final  static  int DUMMY_XP = 10;
    private Axe axe;
    private Dummy dummy;
    private Hero hero;

    @Before
    public void initializeTestObjects() {
        this.axe = new Axe(ATTACK_POINTS_AXE, DURABILITY_POINTS_AXE);
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP);
        this.hero = new Hero("Pesho");
    }
    @Test
    public void dummyLosesHealthAfterAttack() {
        dummy.takeAttack(axe.getAttackPoints());
        Assert.assertEquals(0, dummy.getHealth());
    }

    @Test(expected =  IllegalStateException.class)
    public void attackDeadDummyThrowException() {
        Dummy dummy = new Dummy(DEAD_DUMMY_HEALTH, 10);
        dummy.takeAttack(axe.getAttackPoints());
    }
    @Test
    public void deadDummyGiveXp() {
        hero.attack(dummy);
        Assert.assertEquals(10,hero.getExperience());

    }
    @Test(expected = IllegalStateException.class)
    public void aliveDummyCantGiveXp() {
        Dummy dummy = new Dummy(DUMMY_HEALTH + DUMMY_HEALTH , 10);
        Axe axe = new Axe(10, 10);
        dummy.giveExperience();
    }


}