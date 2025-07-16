package de.ait_tr.g_40_1_shop.controller;


import de.ait_tr.g_40_1_shop.domain.dto.ProductDto;
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
    public ProductDto save(@RequestBody ProductDto product) {
        return productService.save(product);
    }

    @GetMapping
    public ProductDto getById(@RequestParam Long id) {
        return productService.getProductById(id);

    }

    @GetMapping("/all")
    public List<ProductDto> getAll() {
        return productService.getAllIActiveProducts();
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto product) {
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
