package guru.springframework.spring6restmvc.controllers;

import guru.springframework.spring6restmvc.entities.Customer;
import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import guru.springframework.spring6restmvc.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerIT {
    @Autowired
    private CustomerController customerController;
    @Autowired
    private CustomerRepository customerRepository;


    @Test
    void testCustomerIdNotFound(){
        assertThrows(NotFoundException.class,()->{
            customerController.getCustomerById(UUID.randomUUID());
        });
    }


    @Test
    void testGetCustomerById (){
        Customer customer= customerRepository.findAll().get(0);

        CustomerDTO dto = customerController.getCustomerById(customer.getCustomerId());
        assertThat(dto).isNotNull();

    }
    @Rollback
    @Transactional
    @Test
    void testGetEmptylist() {
        customerRepository.deleteAll();

         List<CustomerDTO> dtos=customerController.getAllCustomers();

         assertThat(dtos.size()).isEqualTo(0);
    }

    @Test
    void testGetAllCustomers() {

        List<CustomerDTO> dtos = customerController.getAllCustomers();

        assertThat(dtos.size()).isEqualTo(3);

    }

}