package de.ait_tr.g_40_1_shop.service;

import de.ait_tr.g_40_1_shop.domain.dto.CustomerDto;
import de.ait_tr.g_40_1_shop.domain.entity.Cart;
import de.ait_tr.g_40_1_shop.domain.entity.Customer;
import de.ait_tr.g_40_1_shop.domain.entity.Product;
import de.ait_tr.g_40_1_shop.repository.CustomerRepository;
import de.ait_tr.g_40_1_shop.repository.ProductRepository;
import de.ait_tr.g_40_1_shop.service.interfaces.CustomerService;
import de.ait_tr.g_40_1_shop.service.mapping.CustomerMappingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMappingService mappingService;
    private final ProductRepository productRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMappingService mappingService, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.mappingService = mappingService;
        this.productRepository = productRepository;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer entity = mappingService.mapDtoToEntity(customerDto);
        Cart cart = new Cart();
        entity.setCart(cart);
        cart.setCustomer(entity);
        customerRepository.save(entity);
        return mappingService.mapEntityToDto(entity);
    }

    @Override
    public List<CustomerDto> getAllActiveCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> list = customers.stream()
                .filter(Customer::isActive)
                .map(mappingService::mapEntityToDto)
                .toList();

        return list;
    }

    @Override
    public CustomerDto getActiveCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        CustomerDto customerDto = mappingService.mapEntityToDto(customer);
        return customerDto;
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        Long id = customerDto.getId();
        Customer entity = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("NOT FOUND"));
        entity.setName(customerDto.getName());
        customerRepository.save(entity);

        return mappingService.mapEntityToDto(entity);
    }

    @Override
    public void deleteById(Long customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException("Customer ID must not be null");

        }
        if (!customerRepository.existsById(customerId)) {
            throw new EntityNotFoundException("Customer not found with id: " + customerId);
        }

        customerRepository.deleteById(customerId);
    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public CustomerDto restoreByid(Long customerId) {
        Customer entity = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
        entity.setActive(true);
        customerRepository.save(entity);
        return mappingService.mapEntityToDto(entity);
    }

    @Override
    public long getCountAllActiveCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        long count = customerList.stream()
                .filter(customer -> customer.isActive()).count();
        return count;
    }

    @Override
    public BigDecimal getAlLPriceCardActiveCustomer(Long customerId) {
    Customer customer = customerRepository.findById(customerId).orElseThrow(()->new RuntimeException("Customer not found with id: " + customerId));
        if (!customer.isActive()) {
            throw new IllegalStateException("Customer is not active");
        }

        if (customer.getCart() == null) {
            return BigDecimal.ZERO; // или выбросить исключение, если это считается ошибкой
        }

        return customer.getCart().getTotalCost();


    }

    @Override
    public BigDecimal getAveragePriceActiveCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new RuntimeException("Customer not found with id: " + customerId));
        if (!customer.isActive()) {
            throw new IllegalStateException("Customer is not active");
        }

        if (customer.getCart() == null) {
            return BigDecimal.ZERO; // или выбросить исключение, если это считается ошибкой
        }

        return customer.getCart().getAverageProductCost();
    }

    @Override
    public void addActiveProductToActiveCardById(Long customerId, Long productId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->new RuntimeException("Customer not found with id: " + customerId));
        Product product = productRepository.findById(productId).orElseThrow(()->new RuntimeException("Product not found with id: " + productId));

        if (customer.isActive()&& product.isActive()) {
            customer.getCart().addActiveProductToCart(product);
        }else if (!customer.isActive()){
            throw new IllegalStateException("Customer is not active");
        }else if(!product.isActive()){
            throw new IllegalStateException("Product is not active");
        }
    }

    @Override
    public void delProductFromCardById(Long productId, Long CartId) {


    }

    @Override
    public void delAllProductFromCardById(Long customerId) {

    }
}
