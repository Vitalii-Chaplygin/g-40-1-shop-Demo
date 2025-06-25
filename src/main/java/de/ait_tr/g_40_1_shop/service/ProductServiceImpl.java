package de.ait_tr.g_40_1_shop.service;

import de.ait_tr.g_40_1_shop.domain.entity.Product;
import de.ait_tr.g_40_1_shop.repository.ProductRepository;
import de.ait_tr.g_40_1_shop.service.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private  final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        product.setId(null);// это делается если в теле передали id(product id = 1)
       product.setActive(true);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllIActiveProducts() {
        return productRepository.findAll().stream()
                .filter(Product::isActive)
                .toList();
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);//Optional
         if (product==null||!product.isActive()){
             return null;
         }
        return product;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteByTitle(String string) {

    }

    @Override
    public void restoreById(Long id) {

    }

    @Override
    public long getActiveProductQuantity() {
        return 0;
    }

    @Override
    public BigDecimal getActiveProductTotalPrice() {
        return null;
    }

    @Override
    public BigDecimal getActiveProductAveragePrice() {
        return null;
    }

}
