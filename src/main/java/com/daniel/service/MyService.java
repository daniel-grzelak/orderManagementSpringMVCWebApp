package com.daniel.service;

import com.daniel.dto.*;

import java.util.List;


public interface MyService {
    List<CustomerDto> getAllCustomers();
    CustomerDto addCustomer(CustomerDto customerDto);
    List<CountryDto> getAllCountries();
    CountryDto addCountry(CountryDto countryDto);
    ShopDto addShop(ShopDto shopDto);
    List<ShopDto> getAllShops();
    List<TradeDto> getAllTrades();
    ProducerDto addProducer(ProducerDto producerDto);
    List<ProducerDto> getAllProducers();
    ProductDto addProduct(ProductDto productDto);
    List<CategoryDto> getAllCategories();
    List<ProductDto> getAllProducts();
    StockDto addStock(StockDto stockDto);

    CustomerOrderDto addCustomerOrder(CustomerOrderDto customerOrderDto);
    List<StockDto> getAllStocks();

    List<Long> getAllCustomerOrdersForCustomerDto(CustomerDto customerDto);

    List<StockDto> getAllStocksForShopDto(ShopDto shopDto);

    List<CustomerOrderDto> getAllCustomerOrders();
}
