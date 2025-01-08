package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<Customer> listCustomers();
    Customer getCustomerById(UUID id);
    Customer addCustomer(Customer customer);

    void updateCustomerById(UUID customerId, Customer customer);

    void deleteCustomerByID(UUID customerId);

    void patchCustomerById(UUID customerId, Customer customer);
}
