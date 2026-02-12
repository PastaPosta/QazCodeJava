package Controller;

import Model.Product;
import Service.Service;
import Service.ServiceImpl;
import Service.strategy.NameSortingStrategy;
import Service.strategy.PriceSortingStrategy;

import java.util.Scanner;

public class Controller {
    private final Scanner scanner;
    private final Service service;

    public Controller(Scanner scanner, ServiceImpl service) {
        this.scanner = scanner;
        this.service = service;
    }

    public void run(){
        while(true) {
            System.out.println("Goods List MVC");
            System.out.println("Choose: ");
            System.out.println("1. Get all products");
            System.out.println("2. Add new good");
            int choice = scanner.nextInt();
            int strategy_choice = 0;
            if (choice == 1) {
                System.out.println("Choose a sorting strategy: ");
                System.out.println("1. By name");
                System.out.println("2. By price");
                strategy_choice = scanner.nextInt();
            }
            switch (choice) {
                case 1 -> {
                    switch (strategy_choice) {
                        case 1 -> System.out.println(service.getAllProducts(new NameSortingStrategy()).toString());
                        case 2 -> System.out.println(service.getAllProducts(new PriceSortingStrategy()).toString());
                        default -> throw new IllegalArgumentException("No such strategy");
                    }
                }
                case 2 -> {
                    System.out.println("Enter a name: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.println("Enter a price: ");
                    int price = scanner.nextInt();
                    Product product = new Product(222,name, price);
                    service.addProduct(product);
                }
            }
        }
    }

}