package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ComputerManagerTests {
    private  static  final  Computer pc1 = new Computer("Intel", "I5", 2000);
    private  static  final  Computer pc2 = new Computer("Ryzen", "2400g", 2500);
    private  static  final  Computer pc3 = new Computer("Intel", "rx5600x", 3000);
    ComputerManager computerManager;

    @Before
    public void prepareTest() {
        computerManager = new ComputerManager();
        computerManager.addComputer(pc1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddThrowException() {
        computerManager.addComputer(pc1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveThrow() {
        Computer computer = computerManager.getComputer(null, null);
    }
    @Test
    public void testGetComputers() {
        List<Computer> computers = computerManager.getComputers();
        Assert.assertEquals("Intel", computers.get(0).getManufacturer());
        Assert.assertEquals("I5", computers.get(0).getModel());
    }
    @Test
    public void testGetComputer() {
        Computer computer = computerManager.getComputer("Intel", "I5");
        Assert.assertEquals("Intel", computer.getManufacturer());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerThrowException() {
        computerManager.getComputer("ASD","DSA");
    }
    @Test
    public void testGetComputerThrowNull() {
        computerManager.getComputer(null, null);
    }
    @Test
    public void testGetCount() {
        int count = computerManager.getCount();
        Assert.assertEquals(1, count);
    }
    @Test
    public void testGetComputerByManufacturer() {
        computerManager.addComputer(pc3);
        List<Computer> intelPC = computerManager.getComputersByManufacturer("Intel");
        Assert.assertEquals("I5", intelPC.get(0).getModel());
        Assert.assertEquals("rx5600x", intelPC.get(1).getModel());
    }
    @Test
    public void testRemovePc() {
        computerManager.removeComputer("Intel", "I5");
        Assert.assertEquals(0, computerManager.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddComputerThrowNull() {
        computerManager.addComputer(null);
    }

}