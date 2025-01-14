package guru.springframework.spring6restmvc.bootstrap;

import guru.springframework.spring6restmvc.entities.Beer;

import guru.springframework.spring6restmvc.entities.Customer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.repositories.BeerRepository;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;


    @Override
    public void run(String... args) throws Exception {
        loadBeerData();
        loadCustomerData();

    }

    private void loadBeerData(){
        if(beerRepository.count() == 0) {
            Beer beer1 = Beer.builder()
                    .beerName("Larky Malarky")
                    .beerStyle(BeerStyle.STOUT)
                    .upc("1773")
                    .price(new BigDecimal("8.99"))
                    .quantityOnHand(95)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

            Beer beer2 = Beer.builder()
                    .beerName("Pure Intention")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("23254")
                    .price(new BigDecimal("6.99"))
                    .quantityOnHand(240)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

            Beer beer3 = Beer.builder()
                    .beerName("Name Dropper")
                    .beerStyle(BeerStyle.IPA)
                    .upc("3632")
                    .price(new BigDecimal("7.99"))
                    .quantityOnHand(144)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

            Beer beer4 = Beer.builder()
                    .beerName("Bring The Noise")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("123456")
                    .price(new BigDecimal("12.99"))
                    .quantityOnHand(122)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

            beerRepository.saveAll(Arrays.asList(beer1, beer2, beer3, beer4));
        }
    }

    private void loadCustomerData(){
        if(customerRepository.count() == 0) {

            Customer Customer1 = Customer.builder().customerName("Jeffrey Perparas")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            Customer Customer2 = Customer.builder().customerName("Vanessa Gonzalez")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            Customer Customer3 = Customer.builder().customerName("Kang ho Dong")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();


            customerRepository.saveAll(Arrays.asList(Customer1, Customer2, Customer3));
        }
    }




}
