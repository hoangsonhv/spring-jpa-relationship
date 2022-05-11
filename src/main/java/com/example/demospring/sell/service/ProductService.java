package com.example.demospring.sell.service;

import com.example.demospring.manytomany.entity.StudentGrade;
import com.example.demospring.manytomany.entity.StudentGradeId;
import com.example.demospring.sell.entity.Order;
import com.example.demospring.sell.entity.OrderDetail;
import com.example.demospring.sell.entity.Product;
import com.example.demospring.sell.entity.ProductOrderId;
import com.example.demospring.sell.repository.OrderRepository;
import com.example.demospring.sell.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Page<Product> findAll(int page, int limit){
        return productRepository.findAll(PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "price")));
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public List<Product> findByNameAndPrice(String name, double price){
        return productRepository.findAllByNameContainsAndPriceLessThanEqual(name,price);
    }

//    public List<Product> findAll() {
//        return productRepository.findAll();
//    }

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    public boolean addToCart(Product product, Order order) {
        try{
            Set<OrderDetail> orderDetails = order.getOrderDetails();
            if (orderDetails == null){
                orderDetails = new HashSet<>();
            }
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(new ProductOrderId(product.getId(), order.getId()));
            orderDetail.setProduct(product);
            orderDetail.setOrder(order);
            orderDetails.add(orderDetail);
            order.setOrderDetails(orderDetails);
            orderRepository.save(order);
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
        return true;
    }
}
