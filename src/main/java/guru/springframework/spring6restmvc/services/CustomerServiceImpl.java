package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class CustomerServiceImpl implements CustomerService {

    Map<UUID,Customer> customerMap;
   public CustomerServiceImpl() {
        customerMap = new HashMap<>();

        Customer Customer1 = Customer.builder().customerName("Jeffrey Perparas")
                .customerId(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer Customer2 = Customer.builder().customerName("Vanessa Gonzalez")
                .customerId(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer Customer3 = Customer.builder().customerName("Kang ho Dong")
                .customerId(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(Customer1.getCustomerId(), Customer1);
        customerMap.put(Customer2.getCustomerId(), Customer2);
        customerMap.put(Customer3.getCustomerId(), Customer3);

    }
    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<Customer> getCustomerById(UUID id) {

        return Optional.of(customerMap.get(id));
    }

    @Override
    public Customer addCustomer(Customer customer) {
        Customer savedCustomer = Customer.builder()
                .customerId(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .customerName(customer.getCustomerName())
                .version(customer.getVersion())
                .build();

        customerMap.put(savedCustomer.getCustomerId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public void updateCustomerById(UUID customerId, Customer customer) {
        Customer existing = customerMap.get(customerId);
        existing.setCustomerName(customer.getCustomerName());


    }

    @Override
    public void deleteCustomerByID(UUID customerId) {

        customerMap.remove(customerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, Customer customer) {
        Customer existing = customerMap.get(customerId);
        if(StringUtils.hasText(customer.getCustomerName())) {
            existing.setCustomerName(customer.getCustomerName());
        }
    }
}
