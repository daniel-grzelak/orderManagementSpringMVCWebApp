package com.daniel.service;

import com.daniel.dao.interfaces.*;
import com.daniel.domain.*;
import com.daniel.dto.*;
import com.daniel.exceptions.CustomException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MyServiceImpl implements MyService {


    private ProducerDao producerDao;
    private CustomerDao customerDao;
    private ProductDao productDao;
    private Customer_OrderDao customer_OrderDao;
    private ShopDao shopDao;
    private StockDao stockDao;
    private CountryDao countryDao;
    private TradeDao tradeDao;
    private MyMapper myMapper;
    private CategoryDao categoryDao;

    public MyServiceImpl(ProducerDao producerDao, CustomerDao customerDao, ProductDao productDao, Customer_OrderDao customer_OrderDao, ShopDao shopDao, StockDao stockDao, CountryDao countryDao, TradeDao tradeDao, MyMapper myMapper, CategoryDao categoryDao) {
        this.producerDao = producerDao;
        this.customerDao = customerDao;
        this.productDao = productDao;
        this.customer_OrderDao = customer_OrderDao;
        this.shopDao = shopDao;
        this.stockDao = stockDao;
        this.countryDao = countryDao;
        this.tradeDao = tradeDao;
        this.myMapper = myMapper;
        this.categoryDao = categoryDao;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        try {
            return customerDao.getAll().stream().map(myMapper::fromCustomerToCustomerDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("service", "Can't download customers.");
        }
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        try {

            Customer customer = myMapper.fromCustomerDtoToCustomer(customerDto);
            List<CustomerDto> customerDtosFromDb = getAllCustomers();
            System.out.println(customerDtosFromDb.stream().filter(customerDto1 ->  new Long(customerDto1.getCountryDto().getId()).equals(new Long(customerDto.getCountryDto().getId()))).findAny().get());
            if (customerDtosFromDb.stream().filter(customerDto1 -> customerDto1.getName().matches(customerDto.getName()) && customerDto1.getSurname().matches(customerDto.getSurname()) && new Long(customerDto1.getCountryDto().getId()).equals(new Long(customerDto.getCountryDto().getId()))).findAny().equals(Optional.empty())) {
                return myMapper.fromCustomerToCustomerDto(customerDao.add(customer));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new CustomException("customer", "Such customer already exists.");
        }
    }

    @Override
    public List<CountryDto> getAllCountries() {
        try {
            return countryDao.getAll().stream().map(myMapper::fromCountryToCountryDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("service", "Can't download countries.");
        }
    }

    @Override
    public CountryDto addCountry(CountryDto countryDto) {
        try {
            Country country = myMapper.fromCountryDtoToCountry(countryDto);
            return myMapper.fromCountryToCountryDto(countryDao.add(country));
        } catch (Exception e) {
            throw new CustomException("service", "Can't add country.");
        }
    }

    @Override
    public ShopDto addShop(ShopDto shopDto) {
        try {
            Shop shop = myMapper.fromShopDtoToShop(shopDto);
            return myMapper.fromShopToShopDto(shopDao.add(shop));
        } catch (Exception e) {
            throw new CustomException("service", "Can't add shop.");
        }
    }

    @Override
    public List<ShopDto> getAllShops() {
        try {
            return shopDao.getAll().stream().map(myMapper::fromShopToShopDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("service", "Can't download shops.");
        }
    }

    @Override
    public List<TradeDto> getAllTrades() {
        try {
            return tradeDao.getAll().stream().map(myMapper::fromTradeToTradeDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("service", "Can't download trades.");
        }
    }

    @Override
    public ProducerDto addProducer(ProducerDto producerDto) {
        try {
            Producer producer = myMapper.fromProducerDtoToProducer(producerDto);
            return myMapper.fromProducerToProducerDto(producerDao.add(producer));
        } catch (Exception e) {
            throw new CustomException("service", "Can't add producer.");
        }
    }

    @Override
    public List<ProducerDto> getAllProducers() {

        try {
            return producerDao.getAll().stream().map(myMapper::fromProducerToProducerDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("service", "Can't download producers.");
        }
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        try {
            Product product = myMapper.fromProductDtoToProduct(productDto);
            return myMapper.fromProductToProductDto(productDao.add(product));
        } catch (Exception e) {
            throw new CustomException("service", "Can't add product.");
        }
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        try {
            return categoryDao.getAll().stream().map(myMapper::fromCategoryToCategoryDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("service", "Can't download categories.");
        }
    }

    @Override
    public List<ProductDto> getAllProducts() {
        try {
            return productDao.getAll().stream().map(myMapper::fromProductToProductDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("service", "Can't download products.");
        }
    }


}