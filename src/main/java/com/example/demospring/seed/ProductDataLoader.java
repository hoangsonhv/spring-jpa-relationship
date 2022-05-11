package com.example.demospring.seed;

import com.example.demospring.sell.entity.Product;
import com.example.demospring.sell.repository.ProductRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductDataLoader implements CommandLineRunner{

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        productRepository.deleteAll();
        List<Product> list = new ArrayList<>();
        Random rand = new Random();
    }
}
