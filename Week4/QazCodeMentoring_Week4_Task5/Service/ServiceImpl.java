package Service;

import Model.Product;
import Repository.ProductRepository;
import Repository.Repository;
import Service.strategy.SortingStrategy;

import java.util.List;

public class ServiceImpl implements Service{
    private final ProductRepository repository;

    public ServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAllProducts(SortingStrategy strategy) {
        List<Product> products = repository.findAll();
        strategy.sort(products);
        return products;
    }

    @Override
    public void addProduct(Product product) {
        repository.save(product);
    }

}