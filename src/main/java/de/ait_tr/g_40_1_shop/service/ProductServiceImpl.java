package de.ait_tr.g_40_1_shop.service;

import de.ait_tr.g_40_1_shop.domain.dto.ProductDto;
import de.ait_tr.g_40_1_shop.domain.entity.Product;
import de.ait_tr.g_40_1_shop.repository.ProductRepository;
import de.ait_tr.g_40_1_shop.service.interfaces.ProductService;
import de.ait_tr.g_40_1_shop.service.mapping.ProductMappingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMappingService productMappingService;

    public ProductServiceImpl(ProductMappingService productMappingService, ProductRepository productRepository) {
        this.productMappingService = productMappingService;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product entity = productMappingService.mapDtoToEntity(productDto);
        Product save = productRepository.save(entity);
        ProductDto dto = productMappingService.mapEntityToDto(entity);
        return dto;
    }

    @Override
    public List<ProductDto> getAllIActiveProducts() {
        return productRepository.findAll().stream()
                .filter(Product::isActive).map(productMappingService::mapEntityToDto)//   либо через лямда productMappingService.mapEntityToDto(product))
                .toList();
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);//Optional
        if (product == null || !product.isActive()) {
            return null;
        }
        ProductDto dto = productMappingService.mapEntityToDto(product);
        return dto;
    }

    @Override
    public ProductDto update(ProductDto product) {
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
