package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private Map<UUID, Beer> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Larky Malarky")
                .beerStyle(BeerStyle.STOUT)
                .upc("1773")
                .price(new BigDecimal("8.99"))
                .quantityOnHand(95)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Pure Intention")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("23254")
                .price(new BigDecimal("6.99"))
                .quantityOnHand(240)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Name Dropper")
                .beerStyle(BeerStyle.IPA)
                .upc("3632")
                .price(new BigDecimal("7.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        Beer beer4 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Bring The Noise")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123456")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();


        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
        beerMap.put(beer4.getId(), beer4);

    }

    @Override
    public List<Beer> listBeers() {
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Beer getBeerbyId(UUID id) {

        log.debug("get Beer Id - in service. id : {}", id.toString());

        return beerMap.get(id);
    }

    @Override
    public Beer saveNewBeer(Beer beer) {

        Beer savedBeer = Beer.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .beerName(beer.getBeerName())
                .quantityOnHand(beer.getQuantityOnHand())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .beerStyle(beer.getBeerStyle())
                .version(beer.getVersion())
                .build();
        beerMap.put(savedBeer.getId(), savedBeer);

        return savedBeer;
    }

    @Override
    public void updateBeerById(UUID beerId, Beer beer) {
        Beer existing = beerMap.get(beerId);
        existing.setBeerName(beer.getBeerName());
        existing.setQuantityOnHand(beer.getQuantityOnHand());
        existing.setUpc(beer.getUpc());
        existing.setPrice(beer.getPrice());
        existing.setVersion(beer.getVersion());

    }

    @Override
    public void deleteBeerById(UUID beerId) {
        beerMap.remove(beerId);
    }

    @Override
    public void patchBeerById(UUID beerId, Beer beer) {
        Beer existing = beerMap.get(beerId);

        if (StringUtils.hasText(beer.getBeerName())){
            existing.setBeerName(beer.getBeerName());
        }
        if (beer.getBeerStyle()!=null){
            existing.setBeerStyle(beer.getBeerStyle());
        }
        if (beer.getPrice()!=null){
            existing.setPrice(beer.getPrice());
        }
        if (beer.getQuantityOnHand()!=null){
            existing.setQuantityOnHand(beer.getQuantityOnHand());
        }
        if (StringUtils.hasText(beer.getUpc())){
            existing.setUpc(beer.getUpc());
        }

    }
}
