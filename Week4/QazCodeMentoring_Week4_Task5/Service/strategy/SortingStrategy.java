package Service.strategy;

import Model.Product;

import java.util.List;

public interface SortingStrategy {
    void sort(List<Product> productList);
}
