package archeologicalExcavations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class ExcavationTests {

    private static final Archaeologist archeologist1 = new Archaeologist("Plamen", 50);
    private static final Archaeologist archeologist2 = new Archaeologist("Ivan", 60);
    private static final Archaeologist archeologist4 = new Archaeologist("Sasho", 70);
    private static final Archaeologist archeologist3 = new Archaeologist("Kiril", 100);
    Excavation excavations;

    @Before
    public void prepSetup() {
        excavations = new Excavation("Perpericon", 3);
        excavations.addArchaeologist(archeologist1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddExistThrowException() {
        excavations.addArchaeologist(archeologist1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddThrowExceptionOutOfCapacity() {
        excavations.addArchaeologist(archeologist2);
        excavations.addArchaeologist(archeologist3);
        excavations.addArchaeologist(archeologist4);
    }
    @Test
    public void testGetCount() {
        Assert.assertEquals(1, excavations.getCount());
    }
    @Test
    public void testGetName() {
        Assert.assertEquals("Perpericon", excavations.getName());
    }
    @Test
    public void testRemoveArcheologist() {
        Assert.assertEquals(true, excavations.removeArchaeologist("Plamen"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCapacityBelowZeroThrowException() {
        excavations = new Excavation("Perpericon", -1);
    }
    @Test (expected = NullPointerException.class)
    public void testNameNullOrEmptyThrowException() {
        excavations = new Excavation(null, 3);
    }

}
