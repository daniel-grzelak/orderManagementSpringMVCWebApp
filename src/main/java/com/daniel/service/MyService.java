package com.daniel.service;

import com.daniel.dto.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface MyService {
    List<CustomerDto> getAllCustomers();
    CustomerDto addCustomer(CustomerDto customerDto);
    List<CountryDto> getAllCountries();
    CountryDto addCountry(CountryDto countryDto);
    ShopDto addShop(ShopDto shopDto);
    List<ShopDto> getAllShops();
    List<TradeDto> getAllTrades();
    ProducerDto addProducer(ProducerDto producerDto);
}