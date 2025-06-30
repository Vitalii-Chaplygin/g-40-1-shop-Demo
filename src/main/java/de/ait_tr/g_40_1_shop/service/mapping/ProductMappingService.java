package de.ait_tr.g_40_1_shop.service.mapping;

import de.ait_tr.g_40_1_shop.domain.dto.ProductDto;
import de.ait_tr.g_40_1_shop.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Mapper(componentModel = "spring")
public interface ProductMappingService {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active",constant = "true")
    Product mapDtoToEntity(ProductDto dto);
    ProductDto mapEntityToDto(Product entity);



    // методы без использования MAPSTRUCT  анотации @MAPPER

//    public Product mapDtoToEntity(ProductDto dto) {
//        Product entity = new Product();
//
//
//        BigDecimal price = dto.getPrice();
//        String title = dto.getTitle();
//
//
//        entity.setPrice(price);
//        entity.setTitle(title);
//        entity.setActive(true);
//
//        return entity;
//    }
//
//    public ProductDto mapEntityToDto(Product entity) {
//        ProductDto dto = new ProductDto();
//
//        Long id = entity.getId();
//        BigDecimal price = entity.getPrice();
//        String title = entity.getTitle();
//
//        dto.setId(id);
//        dto.setPrice(price);
//        dto.setTitle(title);
//
//
//        return dto;
//    }
}
