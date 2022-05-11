package com.example.demospring.sell.api;

import com.example.demospring.manytomany.entity.Grade;
import com.example.demospring.manytomany.entity.Student;
import com.example.demospring.sell.entity.Order;
import com.example.demospring.sell.entity.Product;
import com.example.demospring.sell.service.OrderService;
import com.example.demospring.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/products")
public class ProductApi {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET,path = "/get-all")
    public Page<Product> getList(@RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "10") int limit){
        return productService.findAll(page, limit);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getList(){
        return productService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product save(@RequestBody Product product){
        productService.save(product);
        return product;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/order")
    public ResponseEntity<?> addToCart(
            @RequestParam Integer productId,
            @RequestParam Integer orderId){
        Optional<Product> productOptional = productService.findById(productId);
        Optional<Order> orderOptional = orderService.findById(orderId);
        if (!productOptional.isPresent() || !orderOptional.isPresent()){
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        boolean result = productService.addToCart(productOptional.get(), orderOptional.get());
        if (!result){
            return new ResponseEntity<>("Add product error.", HttpStatus.INSUFFICIENT_STORAGE);
        }
        return new ResponseEntity<>("Add order success.", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Product getDetail(@PathVariable int id){
//        Product found = null;
//        for (Product product:
//                productList) {
//            if (product.getId() == id){
//                found = product;
//                break;
//            }
//        }

//        return found;
        return productService.findById(id).get();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public boolean delete(@PathVariable int id){
//        Product found = null;
//        for (Product product:
//                productList) {
//            if (product.getId()  == id){
//                found = product;
//                break;
//            }
//        }
//        if (found != null){
//            productList.remove(found);
//        }
        productService.deleteById(id);
        return true;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public Product update(@PathVariable int id, @RequestBody Product updateProduct){
//        Product found = null;
//        for (Product product:
//                productList) {
//            if (product.getId() == id){
//                found = product;
//                break;
//            }
//        }
//        if (found != null){
//            found.setName(updateProduct.getName());
//            found.setDescription(updateProduct.getDescription());
//            found.setStatus(updateProduct.getStatus());
//        }
//        return found;
        Product existing = productService.findById(id).get();
        existing.setName(updateProduct.getName());
        existing.setDescription(updateProduct.getDescription());
        existing.setPrice(updateProduct.getPrice());
        productService.save(existing);
        return updateProduct;
    }
}
