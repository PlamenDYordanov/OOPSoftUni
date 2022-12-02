package football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FootballTeamTests {

   private static final Footballer footballer1 = new Footballer("Plamen");
   private static final Footballer footballer2 = new Footballer("Ivan");
   private static final Footballer footballer3 = new Footballer("Dragan");
   FootballTeam footballTeam;

   @Before
    public void prepareTest() {
       footballTeam = new FootballTeam("Arsenal", 2);
       footballTeam.addFootballer(footballer1);
   }

   @Test(expected = IllegalArgumentException.class)
    public void testAddFootballerOutOfCapacityThrowException() {
       footballTeam.addFootballer(footballer2);
       footballTeam.addFootballer(footballer3);
   }
   @Test (expected =  IllegalArgumentException.class)
   public void testSetVacantPointsNegativeNumberThrowException(){
      footballTeam = new FootballTeam("Arsenal", -1);
   }
   @Test
   public void testGetName() {
      Assert.assertEquals("Arsenal", footballTeam.getName());
   }
   @Test(expected = NullPointerException.class)
   public void testSetNameThrowExceptionNullOrEmpty() {
      footballTeam = new FootballTeam(null, 2);
   }
   @Test
   public void testGetCount() {
      Assert.assertEquals(1, footballTeam.getCount());
   }
   @Test
   public void testVacantPositions (){
      Assert.assertEquals(2, footballTeam.getVacantPositions());
   }
   @Test(expected = IllegalArgumentException.class)
   public void testRemoveFootballerThrowException(){
      footballTeam.removeFootballer("Kiro");
   }
   @Test
   public void testRemoveFootballer() {
      footballTeam.removeFootballer("Plamen");
      Assert.assertEquals(0,footballTeam.getCount());
   }
   @Test(expected = IllegalArgumentException.class)
   public void testFootballerForSaleThrowException() {
      footballTeam.footballerForSale("Kiro");
   }
   @Test
   public void testFootballerForSale() {
      footballTeam.footballerForSale("Plamen");
      Assert.assertFalse(footballer1.isActive());
   }
   @Test
   public void testGetStatistic() {
      Assert.assertEquals(String.format("The footballer %s is in the team %s.", "Plamen", footballTeam.getName()),
              footballTeam.getStatistics());
   }




}
