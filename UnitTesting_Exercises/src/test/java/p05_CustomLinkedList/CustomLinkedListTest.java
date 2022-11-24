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
    public void testReturnProperGetFunctionality() {
        Assert.assertEquals(customLinkedList.get(0),"Sofia");

    }

}