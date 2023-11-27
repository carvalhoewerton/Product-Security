package productsecurity.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import productsecurity.model.product.Product;
import productsecurity.model.product.ProductDto;
import productsecurity.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {
    @Autowired
    public ProductRepository repository;

    @PostMapping
    public ResponseEntity<String> postProduct(@Valid @RequestBody ProductDto productDto){
        var product = new Product();
        BeanUtils.copyProperties(productDto, product);
        repository.save(product);
        return ResponseEntity.ok().body("Product added to the date base");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@Valid @PathVariable UUID id){
        Product product = repository.findProductById(id);
        return ResponseEntity.ok().body(product);

    }


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = repository.findAll();
        return ResponseEntity.ok(products);
    }

}
