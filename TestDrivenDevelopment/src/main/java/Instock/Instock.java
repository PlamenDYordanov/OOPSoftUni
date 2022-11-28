package Instock;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Instock implements ProductStock {
    private List<Product> productList;

    public Instock() {
        this.productList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public boolean contains(Product product) {
        return productList.contains(product);
    }

    @Override
    public void add(Product product) {
        this.productList.add(product);
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        Product productFound = productList.stream()
                .filter(p -> p.getLabel().equals(product))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        productFound.setQuantity(quantity);


    }

    @Override
    public Product find(int index) {
        return productList.get(index);
    }

    @Override
    public Product findByLabel(String label) {
        return productList.stream()
                .filter(p -> p.getLabel().equals(label))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        if (count < 0 || count > productList.size()) {
            return new ArrayList<>();
        }
        return productList
                .stream()
                .limit(count)
                .sorted(Comparator.comparing(Product::getLabel))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return productList.stream()
                .filter(p -> p.getPrice() > lo && p.getPrice() <= hi)
                .sorted((left, right) -> Double.compare(right.getPrice(), left.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return productList.stream()
                .filter(p -> p.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (count >= 0 && count <= productList.size()) {
            return productList.stream()
                    .sorted((left, right) -> Double.compare(right.getPrice(), left.getPrice()))
                    .collect(Collectors.toList());
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return this.productList
                .stream()
                .filter(product ->  product.getQuantity() == quantity)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
        return this.productList.iterator();
    }
}
