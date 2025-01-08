package guru.springframework.spring6restmvc.controllers;


import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PatchMapping("{customerId}")
    public ResponseEntity updateCustomerPatchByID(@PathVariable("customerId") UUID customerId,
                                                  @RequestBody Customer customer) {

        customerService.patchCustomerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("{customerId}")
    public ResponseEntity deleteCustomerById(@PathVariable("customerId") UUID customerId) {

        customerService.deleteCustomerByID(customerId);


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("{customerId}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {

        customerService.updateCustomerById(customerId, customer);


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody Customer customer) {

        Customer savedCustomer = customerService.addCustomer(customer);

        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.add("Location", "/api/v1/customer/" +
                savedCustomer.getCustomerId());

        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerService.listCustomers();
    }

    @RequestMapping(value = "{customerId}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {
        return customerService.getCustomerById(customerId);
    }
}
