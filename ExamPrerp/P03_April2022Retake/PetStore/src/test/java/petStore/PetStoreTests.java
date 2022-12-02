package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PetStoreTests {
    private static final Animal animal1 = new Animal("Wolf", 70, 1000);
    private static final Animal animal2 = new Animal("Lion", 300, 2000);
    private static final Animal animal3 = new Animal("Cow", 400, 500);
    PetStore petStore;

    @Before
    public void prepareTest() {
        petStore = new PetStore();
        petStore.addAnimal(animal1);
    }
    @Test
    public void  testGetSpecies() {
        Assert.assertEquals("Wolf",petStore.getAnimals().get(0).getSpecie() );
    }
    @Test
    public void testGetAge() {
        petStore.getAnimals().get(0).setAge(1);
        Assert.assertEquals(1, petStore.getAnimals().get(0).getAge());
    }
    @Test
    public void testGetCount() {
        Assert.assertEquals(1, petStore.getCount());
    }
    @Test
    public void testAnimalWithMaxKilograms() {
        petStore.addAnimal(animal3);
        petStore.addAnimal(animal2);
        List<Animal> maxKgAnimal = petStore.findAllAnimalsWithMaxKilograms(301);
        maxKgAnimal.get(0).setAge(5);
        Assert.assertEquals("Cow", maxKgAnimal.get(0).getSpecie());
        Assert.assertEquals(5, maxKgAnimal.get(0).getAge());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullThrowException() {
        petStore.addAnimal(null);
    }
    @Test
    public void testGetMostExpensiveAnimal() {
        petStore.addAnimal(animal2);
        Animal mostExpensive = petStore.getTheMostExpensiveAnimal();
        mostExpensive.setAge(10);
        Assert.assertEquals("Lion", mostExpensive.getSpecie());
        Assert.assertEquals(10, mostExpensive.getAge());
    }
    @Test
    public void testFindAllAnimalBySpecie() {
        List<Animal> animalsBySpecie = petStore.findAllAnimalBySpecie("Wolf");
        Assert.assertEquals("Wolf", animalsBySpecie.get(0).getSpecie());
    }
}

