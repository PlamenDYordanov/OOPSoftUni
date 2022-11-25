package p05_CustomLinkedList;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomLinkedListTest<T> {

    private static final String[] CONSTANT_LIST = {"Sofia", "Varna", "Plovdiv"};
    CustomLinkedList<String> customLinkedList;
    @Before
    public void prepareTest() {
        this.customLinkedList = new CustomLinkedList<>();

        for (int i = 0; i < CONSTANT_LIST.length; i++) {
            this.customLinkedList.add(CONSTANT_LIST[i]);
        }
    }

    @Test()
    public void testGetFunctionalityWorkProperly() {
        for (int i = 0; i < CONSTANT_LIST.length; i++) {
            Assert.assertEquals(customLinkedList.get(i),CONSTANT_LIST[i]);
        }
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetFunctionalityIfIndexIsOutOfBound() {
        customLinkedList.get(3);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetFunctionalityIfIndexIsOutOfBound() {
        customLinkedList.set(3, "Shumen");
    }
    @Test()
    public void testSetFunctionalityWorkProperly() {
        String setElement = "Montana";
        for (int i = 0; i < CONSTANT_LIST.length; i++) {
            customLinkedList.set(i, setElement);
        }
        for (int i = 0; i < CONSTANT_LIST.length; i++) {
            Assert.assertEquals(customLinkedList.get(i), setElement);
        }
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtFunctionalityIfIndexIsOutOfBound() {
        customLinkedList.removeAt(3);
    }
    @Test()
    public void testRemoveAtFunctionalityWorkProperly() {
        int removeIndex = 1;
        for (int i = 1; i <= removeIndex; i++) {
        if (removeIndex == i) {
            customLinkedList.removeAt(i);
            Assert.assertEquals(customLinkedList.get(i),"Plovdiv");
        }
        }
    }
    @Test()
    public void testRemoveFunctionalityWorkProperlyWithCorrectItem() {
        String removeItem = "Plovdiv";
        for (int i = 0; i < CONSTANT_LIST.length; i++) {
            if (removeItem.equals(customLinkedList.get(i))) {
                customLinkedList.remove(removeItem);
            }
        }
    }
    @Test()
    public void testRemoveFunctionalityWorkProperlyWithIncorrectItem() {
        String removeItem = "Burgas";
        int removeItemIfDoesNotExit = -1;
        customLinkedList.remove(removeItem);
       Assert.assertEquals(-1, removeItemIfDoesNotExit);
    }
    @Test()
    public void testIndexOfFunctionalityWorkProperlyWithCorrectItem() {
        String indexOfItem = "Plovdiv";
        for (int i = 0; i < CONSTANT_LIST.length; i++) {
            if (customLinkedList.indexOf(indexOfItem) == i){
                Assert.assertEquals(customLinkedList.indexOf(indexOfItem), i);
            }
        }
    }
    @Test()
    public void testIndexOfFunctionalityWorkProperlyWithIncorrectItem() {
        String indexOfItem = "Burgas";
        int removeItemIfDoesNotExit = -1;
        customLinkedList.indexOf(indexOfItem);
        Assert.assertEquals(-1, removeItemIfDoesNotExit);
    }
    @Test
    public void testContainsFunctionalityWorkProperlyWithCorrectItem() {
        String correctItem = "Plovdiv";
        Assert.assertTrue(customLinkedList.contains(correctItem));

    }




}