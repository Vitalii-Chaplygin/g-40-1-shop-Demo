package de.ait_tr.g_40_1_shop.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"
            ), inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) && Objects.equals(customer, cart.customer) && Objects.equals(products, cart.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, products);
    }

    public void addActiveProductToCart(Product product) {
        if (product.isActive()) {
            products.add(product);
        }
    }

    public List<Product> getAllActiveProducts() {
        return products.stream()
                .filter(product -> product.isActive())
                .toList();
    }

    public void deleteProductFromCartById(Long id) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(id)) {
                iterator.remove();
                break;
            }
        }
    }

    public void clearAllProduct() {
        products.clear();
    }

    public BigDecimal getTotalCost() {
      return   products.stream()
                .filter(Product::isActive)
                .map(Product::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);


    }
    public BigDecimal getAverageProductCost() {
        long count = products.stream()
                .filter(Product::isActive)
                .count();

        return count == 0 ?
                BigDecimal.ZERO :
                getTotalCost().divide(new BigDecimal(count), RoundingMode.DOWN);
    }


    @Override
    public String toString() {
        return String.format("Cart: id = %d, Customer = %s, Products = %d",
                id, products == null ? 0 : products.size());
    }
}
