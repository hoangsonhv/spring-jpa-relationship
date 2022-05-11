package com.example.demospring.sell.service;

import com.example.demospring.sell.entity.Order;
import com.example.demospring.sell.entity.Product;
import com.example.demospring.sell.repository.OrderRepository;
import com.example.demospring.sell.repository.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Page<Order> findAll(int page, int limit){
        return orderRepository.findAll(PageRequest.of(page, limit));
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }


    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

}
