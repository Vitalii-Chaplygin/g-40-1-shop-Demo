package de.ait_tr.g_40_1_shop.domain.dto;

import java.util.Objects;

public class CustomerDto {
    private Long id;
    private String name;
    private CartDto cartDto;

    public CartDto getCartDto() {
        return cartDto;
    }

    public void setCartDto(CartDto cartDto) {
        this.cartDto = cartDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(cartDto, that.cartDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cartDto);
    }

    @Override
    public String toString() {
        return String.format("Customer: id- %d, name - %s, active - %s, catd - %s"
                , id, name, cartDto == null ? "ERROR" : cartDto);
    }
}
