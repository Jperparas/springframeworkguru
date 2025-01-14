package guru.springframework.spring6restmvc.controllers;

import guru.springframework.spring6restmvc.entities.Customer;
import guru.springframework.spring6restmvc.mappers.CustomerMapper;
import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomerControllerIT {
    @Autowired
    private CustomerController customerController;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;


    @Test
    void testPatchCustomerByIdNotFound() {
        assertThrows(NotFoundException.class,()->{
            customerController.updateCustomerPatchByID(UUID.randomUUID(),CustomerDTO.builder().build());
        });

    }

    @Rollback
    @Transactional
    @Test
    void testPatchCustomerById(){

        Customer customer =customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        customerDTO.setCustomerId(null);
        customerDTO.setVersion(null);
        final String customerName = "UPDATED";
        customerDTO.setCustomerName(customerName);

        ResponseEntity responseEntity = customerController.updateCustomerPatchByID(customer.getCustomerId()
                ,customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer updatedCustomer = customerRepository.findById(customer.getCustomerId()).get();
        assertThat(updatedCustomer.getCustomerName()).isEqualTo(customerName);

    }



    @Test
    void DeleteByIdNotFound(){
        assertThrows(NotFoundException.class,()->{
            customerController.deleteCustomerById(UUID.randomUUID());
        });

    }


    @Rollback
    @Transactional
    @Test
    void deleteByIdFoundTest(){
        Customer customer = customerRepository.findAll().getFirst();

        ResponseEntity responseEntity = customerController.deleteCustomerById(customer.getCustomerId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        assertThat(customerRepository.findById(customer.getCustomerId())).isEmpty();

    }

    @Test
    void testCustomerUpdateNotFoundTest(){
        assertThrows(NotFoundException.class, () -> {
            customerController.updateCustomerById(UUID.randomUUID(),CustomerDTO.builder().build());
        });
    }

    @Rollback
    @Transactional
    @Test
    void updateExistingCustomerFoundTest() {
        Customer customer =customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        customerDTO.setCustomerId(null);
        customerDTO.setVersion(null);
        final String customerName = "UPDATED";
        customerDTO.setCustomerName(customerName);

        ResponseEntity responseEntity = customerController.updateCustomerById(customer.getCustomerId()
                ,customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer updatedCustomer = customerRepository.findById(customer.getCustomerId()).get();
        assertThat(updatedCustomer.getCustomerName()).isEqualTo(customerName);

    }
    @Rollback
    @Transactional
    @Test
    void addNewCustomerTest(){
        CustomerDTO customerDTO = CustomerDTO.builder()
                .customerName("New Customer")
                .build();
        ResponseEntity responseEntity = customerController.handlePost(customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");

        UUID savedUUID = UUID.fromString(locationUUID[4]);

        Customer customer = customerRepository.findById(savedUUID).get();
        assertThat(customer).isNotNull();
    }


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