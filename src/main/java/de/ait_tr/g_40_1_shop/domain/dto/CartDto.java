package de.ait_tr.g_40_1_shop.domain.dto;

import de.ait_tr.g_40_1_shop.domain.entity.Product;

import java.util.List;
import java.util.Objects;

public class CartDto {

        private Long id;
        private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProduct() {
        return products;
    }

    public void setProduct(List<Product> product) {
        this.products = product;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartDto cartDto = (CartDto) o;
        return Objects.equals(id, cartDto.id) && Objects.equals(products, cartDto.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products);
    }
    public String toString() {
        return String.format("Cart: id - %d, contains %d products",
                id, products == null ? 0 : products.size());
    }
}