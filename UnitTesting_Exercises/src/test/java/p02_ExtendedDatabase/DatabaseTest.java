package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private static final Person[] PEOPLE = {new Person(1, "Ivan"), new Person(2, "Plamen"), new Person(3, "Kiro")};
    private static final Person[] NULL_PEOPLE = {null};
    private static final int HIGHER_THAN_CAPACITY = 17;
    private static final  int CAPACITY = 16;

    Database database;

    @Before
    public void prepareTest() throws OperationNotSupportedException {
        database = new Database(PEOPLE);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddMoreThan16PersonsToDatabaseThrowException() throws OperationNotSupportedException {
        Person[] people = new Person[HIGHER_THAN_CAPACITY];
        database = new Database(people);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowExceptionLessThan1Person() throws OperationNotSupportedException {
        Person[] people = new Person[0];
        database = new Database(people);
    }

    @Test
    public void testCorrectlyAddPersonToDatabase() throws OperationNotSupportedException {
        database.add(new Person(4, "Suren"));
        Person[] testPersons = database.getElements();
        Assert.assertEquals(database.getElements().length, testPersons.length);
        Assert.assertArrayEquals(database.getElements(), testPersons);
        Assert.assertEquals(testPersons[testPersons.length - 1].getUsername(), "Suren");
        Assert.assertEquals(testPersons[testPersons.length - 1].getId(), 4);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddNullIdToDatabaseThrowException() throws OperationNotSupportedException {
        database.add(null);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void testFindByNameDoesNotContainInDataBase() throws OperationNotSupportedException {
        Person[] elements = database.getElements();
        database.findByUsername("Sasho");
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testAllParametersOfDatabaseIsNull () throws OperationNotSupportedException {
        new Database(NULL_PEOPLE);
        database.findByUsername(null);
    }
    @Test
    public void testFindByNameExistInDatabase() throws OperationNotSupportedException {
        Person[] elements = database.getElements();
        database.findByUsername("Ivan");
    }
    @Test(expected =  OperationNotSupportedException.class)
    public void testFindByIdDoesNotContainsInDatabase() throws OperationNotSupportedException {
        database.findById(10);
    }
    @Test
    public void testFindByIdExistInDataBase() throws OperationNotSupportedException {
        database.findById(1);
    }
    @Test(expected =  OperationNotSupportedException.class)
    public void testRemoveThrowExceptionIfAllParametersInDBIsNull() throws OperationNotSupportedException {
        Person[] people = new Person[CAPACITY];
        new Database(people);
        for (int i = 0; i < CAPACITY + 1; i++) {
            database.remove();
        }
    }
    @Test
    public void testRemovePersonFromDatabase() throws OperationNotSupportedException {
        database.remove();
        Assert.assertEquals(database.getElements()[database.getElements().length-1].getUsername(), "Plamen");
        Assert.assertEquals(database.getElements()[database.getElements().length-1].getId(), 2);
    }
}
