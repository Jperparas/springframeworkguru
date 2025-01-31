package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class CustomerServiceImpl implements CustomerService {

  private Map<UUID, CustomerDTO> customerMap;
   public CustomerServiceImpl() {
       customerMap = new HashMap<>();
       CustomerDTO Customer1 = CustomerDTO.builder().customerName("Jeffrey Perparas")
               .customerId(UUID.randomUUID())
               .version(1)
               .createdDate(LocalDateTime.now())
               .lastModifiedDate(LocalDateTime.now())
               .build();

       CustomerDTO Customer2 = CustomerDTO.builder().customerName("Vanessa Gonzalez")
               .customerId(UUID.randomUUID())
               .version(1)
               .createdDate(LocalDateTime.now())
               .lastModifiedDate(LocalDateTime.now())
               .build();

       CustomerDTO Customer3 = CustomerDTO.builder().customerName("Kang ho Dong")
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
    public List<CustomerDTO> listCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {

        return Optional.of(customerMap.get(id));
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customer) {
        CustomerDTO savedCustomer = CustomerDTO.builder()
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
    public Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer) {
        CustomerDTO existing = customerMap.get(customerId);
        existing.setCustomerName(customer.getCustomerName());

        return Optional.of(existing);

    }

    @Override
    public Boolean deleteCustomerByID(UUID customerId) {
       customerMap.remove(customerId);
       return true;
    }

    @Override
    public Optional<CustomerDTO> patchCustomerById(UUID customerId, CustomerDTO customer) {
        CustomerDTO existing = customerMap.get(customerId);
        if(StringUtils.hasText(customer.getCustomerName())) {
            existing.setCustomerName(customer.getCustomerName());
        }
        return Optional.of(existing);
    }
}
