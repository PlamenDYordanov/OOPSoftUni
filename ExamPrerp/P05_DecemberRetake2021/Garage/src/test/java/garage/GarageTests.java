package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    private static final Car commonCar = new Car("MAZDA", 200, 5000);
    private static final Car sportCar = new Car("BMW", 300, 10000);
    private static final Car muscleCar = new Car("FORD", 320, 20000);
    Garage garage;

    @Before
    public void prepareTest() {
        garage = new Garage();
        garage.addCar(commonCar);
    }

    @Test
    public void testGetCars() {
        List<Car> cars = garage.getCars();
        Assert.assertEquals("MAZDA",cars.get(0).getBrand());
    }
    @Test
    public void testGetCount() {
        Assert.assertEquals(1, garage.getCount());
    }
    @Test
    public void testFindAllCarsWithMaxSpeedAbove() {
        garage.addCar(muscleCar);
        List<Car> allCarsWithMaxSpeedAbove = garage.findAllCarsWithMaxSpeedAbove(300);
        Assert.assertEquals("FORD", allCarsWithMaxSpeedAbove.get(0).getBrand());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddCarThrowException() {
        garage.addCar(null);
    }
    @Test
    public void testGetTheMostExpensiveCar() {
        garage.addCar(muscleCar);
        Car theMostExpensiveCar = garage.getTheMostExpensiveCar();
        Assert.assertEquals("FORD", theMostExpensiveCar.getBrand());
    }
    @Test
    public void testFindAllCarsByBrand() {
        garage.addCar(sportCar);
        garage.addCar(muscleCar);
        List<Car> cars = garage.findAllCarsByBrand("MAZDA");
        Assert.assertEquals(200, cars.get(0).getMaxSpeed());
    }
}