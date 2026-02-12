package Service;

import Model.Product;
import Service.strategy.SortingStrategy;

import java.util.List;

public interface Service {
    List<Product> getAllProducts(SortingStrategy strategy);
    void addProduct(Product product);
}
