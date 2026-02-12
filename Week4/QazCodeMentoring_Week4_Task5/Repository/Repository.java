package Repository;

import Model.Product;

import java.util.ArrayList;
import java.util.List;

public class Repository implements ProductRepository{

    private final List<Product> productList = new ArrayList<>();
    int size = 1;

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productList);
    }

    @Override
    public void save(Product product) {
        Product productId = new Product(size, product.getName(), product.getPrice());
        productList.add(productId);
        size++;
    }
}