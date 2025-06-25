package de.ait_tr.g_40_1_shop.service.interfaces;

import de.ait_tr.g_40_1_shop.domain.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> getAllIActiveProducts();

    Product getProductById(Long id);

    Product update(Product product);

    void deleteById(Long id);

    void deleteByTitle(String string);

    void restoreById(Long id);

    long getActiveProductQuantity();

    BigDecimal getActiveProductTotalPrice();

    BigDecimal getActiveProductAveragePrice();


}
