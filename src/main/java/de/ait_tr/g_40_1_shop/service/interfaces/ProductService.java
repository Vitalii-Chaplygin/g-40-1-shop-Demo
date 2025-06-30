package de.ait_tr.g_40_1_shop.service.interfaces;

import de.ait_tr.g_40_1_shop.domain.dto.ProductDto;
import de.ait_tr.g_40_1_shop.domain.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    ProductDto save(ProductDto product);

    List<ProductDto> getAllIActiveProducts();

    ProductDto getProductById(Long id);

    ProductDto update(ProductDto product);

    void deleteById(Long id);

    void deleteByTitle(String string);

    void restoreById(Long id);

    long getActiveProductQuantity();

    BigDecimal getActiveProductTotalPrice();

    BigDecimal getActiveProductAveragePrice();


}
