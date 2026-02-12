package Repository;

import Model.Product;

import java.util.ArrayList;
import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    void save(Product product);
}
