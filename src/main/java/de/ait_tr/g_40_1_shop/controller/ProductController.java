package de.ait_tr.g_40_1_shop.controller;


import de.ait_tr.g_40_1_shop.domain.entity.Product;
import de.ait_tr.g_40_1_shop.service.interfaces.ProductService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping
    public List<Product> getProduct(@RequestParam(required = false) Long id) {
        if (id == null) {
            return productService.getAllIActiveProducts();
        } else {
            Product product = productService.getProductById(id);
            return product == null ? null : List.of(product);
        }


    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping
    public void del(@RequestParam Long id) {
        productService.deleteById(id);
    }

    @DeleteMapping("/title")
    public void delByTitle(@RequestParam String title) {
        productService.deleteByTitle(title);
    }

    @PutMapping("/restore")
    public void restore(@RequestParam Long id) {
        productService.restoreById(id);
    }

    @GetMapping("/quantity")
    public Long getActiveProductQuantity() {
        return productService.getActiveProductQuantity();
    }

    @GetMapping("/total-price")
    public BigDecimal getActiveProductTotalPrice() {
        return productService.getActiveProductTotalPrice();
    }

    @GetMapping("/average-price")
    public BigDecimal getActiveProductAveragePrice() {
        return productService.getActiveProductAveragePrice();
    }

}
