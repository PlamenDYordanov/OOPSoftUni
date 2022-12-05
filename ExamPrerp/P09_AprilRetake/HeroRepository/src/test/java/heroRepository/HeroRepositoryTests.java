package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class HeroRepositoryTests {
    private static final Hero hero1 = new Hero("Teya", 3);
    private static final Hero hero2 = new Hero("Aya", 2);
    private static final Hero hero3 = new Hero("Moni", 32);
    HeroRepository heroRepository;

    @Before
    public void prepareTest() {
        heroRepository = new HeroRepository();
        heroRepository.create(hero1);
    }

    @Test
    public void testGetCount() {
        int count = heroRepository.getCount();
        Assert.assertEquals(1, count);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNullHeroThrowException() {
        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIfAlreadyExistThrowException() {
        heroRepository.create(hero1);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNullThrowException() {
        heroRepository.remove(null);
    }

    @Test
    public void testRemove() {
        List<Hero> list = heroRepository.getHeroes()
                .stream()
                .filter(hero -> hero.getName().equals("Teya"))
                .collect(Collectors.toList());
        heroRepository.remove(list.get(0).getName());
        Assert.assertEquals(0, heroRepository.getCount());
    }
    @Test
    public void testGetHeroWithHighestLevel() {
        heroRepository.create(hero2);
        heroRepository.create(hero3);
        Hero heroWithHighestLevel = heroRepository.getHeroWithHighestLevel();
        Assert.assertEquals(32,heroWithHighestLevel.getLevel());

    }
    @Test
    public void testGetHeroes() {
        heroRepository.create(hero2);
        heroRepository.create(hero3);
        Collection<Hero> heroes = heroRepository.getHeroes();
        Assert.assertEquals("Teya", heroes.iterator().next().getName());
    }
    @Test
    public void testGetHero() {
        Hero teya = heroRepository.getHero("Teya");
        Assert.assertEquals(3, teya.getLevel());


    }
}
