package Instock;

import Instock.Instock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class InstockTest {
    private static final String INCORRECT_LABEL = "Whine";
    private static final String CORRECT_LABEL_AVOCADO = "Avocado";
    private static final Product TEST_PRODUCT = new Product("Pepper", 2.50, 3);
    private Instock instock;

    @Before
    public void prepareSetUpTest() {
        instock = new Instock();
    }


    private void fillStock() {
        Product product1 = new Product("Bread", 2.50, 3);
        Product product2 = new Product("Milk", 2.20, 2);
        Product product3 = new Product("Avocado", 3.30, 1);
        instock.add(product1);
        instock.add(product2);
        instock.add(product3);
    }

    @Test
    public void testAddAndContains() {
        Product testProduct = TEST_PRODUCT;
        Assert.assertFalse(instock.contains(testProduct));
        instock.add(testProduct);
        Assert.assertTrue(instock.contains(testProduct));
        assertEquals("Pepper", testProduct.getLabel());


    }

    @Test
    public void testCountMethod() {
        fillStock();
        assertEquals(3, instock.getCount());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindMethodIncorrectIndexThrowException() {
        fillStock();
        instock.find(3);
    }

    @Test
    public void testFindMethodCorrectIndex() {
        fillStock();
        Assert.assertEquals(CORRECT_LABEL_AVOCADO, instock.find(2).getLabel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityIncorrectLabelThrowException() {
        fillStock();
        instock.changeQuantity(INCORRECT_LABEL, 4);
    }

    @Test
    public void testChangeQuantityCorrectLabel() {
        fillStock();
        instock.changeQuantity(CORRECT_LABEL_AVOCADO, 2);
        Assert.assertEquals(2, instock.find(2).getQuantity());
        Assert.assertEquals("Avocado", instock.find(2).getLabel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelIncorrectLabelThrowException() {
        fillStock();
        instock.findByLabel(INCORRECT_LABEL);
    }

    @Test
    public void testFindByLabelCorrectLabel() {
        fillStock();
        Assert.assertEquals(CORRECT_LABEL_AVOCADO, instock.findByLabel(CORRECT_LABEL_AVOCADO).getLabel());
    }

    @Test
    public void testFindFirstByAlphabeticalOrder() {
        fillStock();
        Iterable<Product> returnedProduct = instock.findFirstByAlphabeticalOrder(3);
        List<Product> sortedProducts = new ArrayList<>();
        for (Product product : returnedProduct) {
            sortedProducts.add(product);
        }
        assertEquals(CORRECT_LABEL_AVOCADO, sortedProducts.get(0).getLabel());
    }

    @Test
    public void testFindFirstByAlphabeticalOrderOutOfRange() {
        fillStock();
        Iterable<Product> returnedProduct = instock.findFirstByAlphabeticalOrder(4);
        Assert.assertFalse(returnedProduct.iterator().hasNext());
    }

    @Test
    public void testFindAllInPriceRange() {
        fillStock();
        Iterable<Product> iterable = instock.findAllInRange(2.00, 3.00);
        List<Product> returnedProducts = new ArrayList<>();
        for (Product product : iterable) {
            returnedProducts.add(product);
        }

        assertEquals("Bread", returnedProducts.get(0).getLabel());
    }
    @Test
    public void testFindAllInPriceRangeNoneMatch() {
        fillStock();
        Iterable<Product> iterable = instock.findAllInRange(10.00, 20.00);
        Assert.assertFalse(iterable.iterator().hasNext());
    }
    @Test
    public void testFindAllByPrice() {
        fillStock();
        Iterable<Product> returnedProducts = instock.findAllByPrice(2.50);
        assertEquals("Bread", returnedProducts.iterator().next().getLabel());
    }
    @Test
    public void testFindAllByPriceNoneMatch() {
        fillStock();
        Iterable<Product> returnedProducts = instock.findAllByPrice(10.00);
        Assert.assertFalse(returnedProducts.iterator().hasNext());
    }

    @Test
    public void testFindMostExpensiveProducts() {
        fillStock();
        Iterable<Product> iterable = instock.findFirstMostExpensiveProducts(3);
        List<Product> mostExpensiveProducts = new ArrayList<>();
        for (Product product : iterable) {
            mostExpensiveProducts.add(product);
        }
        assertEquals("Avocado", mostExpensiveProducts.get(0).getLabel());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFindMostExpensiveProductsOutOfRange() {
        fillStock();
        Iterable<Product> iterable = instock.findFirstMostExpensiveProducts(5);
        Assert.assertFalse(iterable.iterator().hasNext());
    }
    @Test
    public void testFindAllByQuantity() {
        fillStock();
        Iterable<Product> iterable = instock.findAllByQuantity(2);
        List<Product> returnedProducts = new ArrayList<>();
        for (Product product: iterable) {
            returnedProducts.add(product);
        }
        Assert.assertEquals(1, returnedProducts.size());
        assertEquals(2 , returnedProducts.get(0).getQuantity());
    }
    @Test
    public void testFindAllByQuantityNoneMatch() {
        fillStock();
        Iterable<Product> iterable = instock.findAllByQuantity(10);
        Assert.assertFalse(iterable.iterator().hasNext());
    }
    @Test
    public void testGetIterableProducts() {
        fillStock();
        Iterator<Product> iterable = instock.iterator();
        assertEquals("Bread", iterable.next().getLabel());
        Assert.assertTrue(iterable.hasNext());
        iterable.next();
        Assert.assertTrue(iterable.hasNext());
        assertEquals("Avocado", iterable.next().getLabel());

    }

}