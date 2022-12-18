package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.List;

public class GarageTests {
    private static final  Car car1 = new Car("VW", 240, 7500);
    private static final  Car car2 = new Car("SUBARU", 230, 8500);
    private static final  Car car3 = new Car("MAZDA", 220, 9500);
    private static final  Car car4 = new Car("VW", 250, 10000);
    Garage garage;

    @Before
    public void prepareTest() {
        garage = new Garage();
        garage.addCar(car1);
    }
    @Test
    public void getCars(){
        List<Car> cars = garage.getCars();
        Assert.assertEquals("VW", cars.get(0).getBrand());
        Assert.assertEquals(240, cars.get(0).getMaxSpeed());
    }
    @Test
    public void getCount() {
        int count = garage.getCount();
        Assert.assertEquals(1, count);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddCarThrowNull() {
        garage.addCar(null);
    }
    @Test
    public void testFindAllCarsWithMaxSpeedAbove () {
        garage.addCar(car2);
        garage.addCar(car3);
        List<Car> allCarsWithMaxSpeedAbove = garage.findAllCarsWithMaxSpeedAbove(229);
        Assert.assertEquals("VW", allCarsWithMaxSpeedAbove.get(0).getBrand());
        Assert.assertEquals("SUBARU", allCarsWithMaxSpeedAbove.get(1).getBrand());
    }
    @Test
    public void getMostExpensiveCar() {
        garage.addCar(car2);
        garage.addCar(car3);
        Car theMostExpensiveCar = garage.getTheMostExpensiveCar();
        Assert.assertEquals("MAZDA", theMostExpensiveCar.getBrand());
    }
    @Test
    public void testFindAllCarsByBrand() {
        garage.addCar(car2);
        garage.addCar(car4);
        List<Car> cars = garage.findAllCarsByBrand("VW");
        Assert.assertEquals(240, cars.get(0).getMaxSpeed());
        Assert.assertEquals(250, cars.get(1).getMaxSpeed());

    }
}