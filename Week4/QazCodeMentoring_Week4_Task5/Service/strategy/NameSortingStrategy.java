package Service.strategy;

import Model.Product;

import java.util.Comparator;
import java.util.List;

public class NameSortingStrategy implements SortingStrategy {

    @Override
    public void sort(List<Product> productList) {
        productList.sort(Comparator.comparing(Product::getName));
    }
}
