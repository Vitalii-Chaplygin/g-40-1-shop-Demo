package de.ait_tr.g_40_1_shop.service;

import de.ait_tr.g_40_1_shop.domain.entity.Customer;
import de.ait_tr.g_40_1_shop.service.interfaces.CustomerService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> getAllActiveCustomer() {
        return List.of();
    }

    @Override
    public Customer getActiveCustomerById(Long customerId) {
        return null;
    }

    @Override
    public Customer updateById(Long customerId) {
        return null;
    }

    @Override
    public void deleteById(Long customerId) {

    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public Customer restoreByid(Long customerId) {
        return null;
    }

    @Override
    public int getCountAllActiveCustomer(List<Customer> customers) {
        return 0;
    }

    @Override
    public BigDecimal getAlLPriceCardActiveCustomer(Customer customer) {
        return null;
    }

    @Override
    public BigDecimal getAveragePriceActiveCustomerById(Long customerId) {
        return null;
    }

    @Override
    public void addActiveProductToActiveCardById(Long customerId, Long cardId) {

    }

    @Override
    public void delProductFromCardById(Long productId, Long cardId) {

    }

    @Override
    public void delAllProductFromCardById(Long customerId) {

    }
}
