package de.ait_tr.g_40_1_shop.controller;

import de.ait_tr.g_40_1_shop.domain.dto.CustomerDto;
import de.ait_tr.g_40_1_shop.service.interfaces.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerDto save(@RequestBody CustomerDto dto) {
        return customerService.save(dto);
    }

    @GetMapping
    public List<CustomerDto> get(@RequestParam Long id) {
        if (id == null) {
            return customerService.getAllActiveCustomers();

        } else {
            CustomerDto customerDto = customerService.getActiveCustomerById(id);
            return customerDto == null ? null : List.of(customerDto);
        }

    }
    @PutMapping
public CustomerDto update(@RequestBody CustomerDto customerDto){
    CustomerDto update = customerService.update(customerDto);
    return update;
}


}
