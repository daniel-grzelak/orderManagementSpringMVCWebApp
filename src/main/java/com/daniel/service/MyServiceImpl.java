package com.daniel.service;

import com.daniel.dao.interfaces.*;
import com.daniel.domain.*;
import com.daniel.dto.*;
import com.daniel.exceptions.CustomException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class MyServiceImpl implements MyService {


    private ProducerDao producerDao;
    private CustomerDao customerDao;
    private ProductDao productDao;
    private CustomerOrderDao CustomerOrderDao;
    private ShopDao shopDao;
    private StockDao stockDao;
    private CountryDao countryDao;
    private TradeDao tradeDao;
    private MyMapper myMapper;
    private CategoryDao categoryDao;
    private PaymentDao paymentDao;

    public MyServiceImpl(ProducerDao producerDao, CustomerDao customerDao, ProductDao productDao, CustomerOrderDao CustomerOrderDao, ShopDao shopDao, StockDao stockDao, CountryDao countryDao, TradeDao tradeDao, MyMapper myMapper, CategoryDao categoryDao, PaymentDao paymentDao) {
        this.producerDao = producerDao;
        this.customerDao = customerDao;
        this.productDao = productDao;
        this.CustomerOrderDao = CustomerOrderDao;
        this.shopDao = shopDao;
        this.stockDao = stockDao;
        this.countryDao = countryDao;
        this.tradeDao = tradeDao;
        this.myMapper = myMapper;
        this.categoryDao = categoryDao;
        this.paymentDao = paymentDao;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        try {
            return customerDao.getAll().stream().map(myMapper::fromCustomerToCustomerDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("service", "Can't download customers. Error: " + e.getMessage());
        }
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        try {
            Customer customer = myMapper.fromCustomerDtoToCustomer(customerDto);
            List<CustomerDto> customerDtosFromDb = getAllCustomers();
            if (customerDtosFromDb
                    .stream()
                    .filter(customerDto1 -> customerDto1.getName().matches(customerDto.getName()) && customerDto1.getSurname().matches(customerDto.getSurname()) && new Long(customerDto1.getCountryDto().getId()).equals(new Long(customerDto.getCountryDto().getId())))
                    .findAny()
                    .equals(Optional.empty())) {
                return myMapper.fromCustomerToCustomerDto(customerDao.add(customer));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new CustomException("customer", "Such customer already exists. Error: " + e.getMessage());
        }
    }

    @Override
    public List<CountryDto> getAllCountries() {
        try {
            return countryDao.getAll().stream().map(myMapper::fromCountryToCountryDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("service", "Can't download countries. Error:" + e.getMessage());
        }
    }

    @Override
    public CountryDto addCountry(CountryDto countryDto) {
        try {
            Country country = myMapper.fromCountryDtoToCountry(countryDto);
            return myMapper.fromCountryToCountryDto(countryDao.add(country));
        } catch (Exception e) {
            throw new CustomException("service", "Can't add country. Error:" + e.getMessage());
        }
    }

    @Override
    public ShopDto addShop(ShopDto shopDto) {
        try {
            Shop shop = myMapper.fromShopDtoToShop(shopDto);
            List<ShopDto> shopDtosFromDb = getAllShops();
            if (shopDtosFromDb
                    .stream()
                    .filter(shopDto1 -> shopDto1.getName().matches(shopDto.getName()) && new Long(shopDto1.getCountryDto().getId()).equals(new Long(shopDto.getCountryDto().getId())))
                    .findAny()
                    .equals(Optional.empty())) {
                return myMapper.fromShopToShopDto(shopDao.add(shop));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new CustomException("service", "Can't add shop. Error: " + e.getMessage());
        }
    }

    @Override
    public List<ShopDto> getAllShops() {
        try {
            return shopDao.getAll().stream().map(myMapper::fromShopToShopDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("service", "Can't download shops. Error: " + e.getMessage());
        }
    }

    @Override
    public List<TradeDto> getAllTrades() {
        try {
            return tradeDao.getAll().stream().map(myMapper::fromTradeToTradeDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("service", "Can't download trades. Error: " + e.getMessage());
        }
    }

    @Override
    public ProducerDto addProducer(ProducerDto producerDto) {
        try {
            Producer producer = myMapper.fromProducerDtoToProducer(producerDto);
            List<ProducerDto> producerDtosFromDb = getAllProducers();
            if (producerDtosFromDb
                    .stream()
                    .filter(producerDto1 -> producerDto1.getName().matches(producerDto.getName()) && new Long(producerDto1.getCountryDto().getId()).equals(new Long(producerDto.getCountryDto().getId())))
                    .findAny()
                    .equals(Optional.empty())) {
                return myMapper.fromProducerToProducerDto(producerDao.add(producer));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new CustomException("producer", "Such producer already exists. Error: " + e.getMessage());
        }
    }

    @Override
    public List<ProducerDto> getAllProducers() {

        try {
            return producerDao.getAll().stream().map(myMapper::fromProducerToProducerDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("service", "Can't download producers. Error:" + e.getMessage());
        }
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        try {
            Product product = myMapper.fromProductDtoToProduct(productDto);
            List<ProductDto> productDtosFromDb = getAllProducts();
            if (productDtosFromDb
                    .stream()
                    .filter(productDto1 -> productDto1.getName().matches(productDto.getName()) && new Long(productDto1.getCategoryDto().getId()).equals(new Long(productDto.getCategoryDto().getId())) && new Long(productDto1.getProducerDto().getId()).equals(new Long(productDto.getProducerDto().getId())))
                    .findAny()
                    .equals(Optional.empty())) {
                return myMapper.fromProductToProductDto(productDao.add(product));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new CustomException("product", "Such product already exists. Error: " + e.getMessage());
        }
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        try {
            return categoryDao.getAll().stream().map(myMapper::fromCategoryToCategoryDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("service", "Can't download categories. Error: " + e.getMessage());
        }
    }

    @Override
    public List<ProductDto> getAllProducts() {
        try {
            return productDao.getAll().stream().map(myMapper::fromProductToProductDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("service", "Can't download products. Error: " + e.getMessage());
        }
    }

    @Override
    public StockDto addStock(StockDto stockDto) {
        try {
            Stock stock = myMapper.fromStockDtoToStock(stockDto);
            List<StockDto> stockDtosFromDb = getAllStocks();
            Optional<StockDto> sameStockDto = stockDtosFromDb
                    .stream()
                    .filter(stockDto1 -> new Long(stockDto1.getProductDto().getId()).equals(new Long(stockDto.getProductDto().getId())) && new Long(stockDto1.getShopDto().getId()).equals(new Long(stockDto.getShopDto().getId())))
                    .findAny();
            if (sameStockDto.isPresent()) {

                Stock sameStock = myMapper.fromStockDtoToStock(sameStockDto.get());

                sameStock.setQuantity(sameStock.getQuantity() + stock.getQuantity());

                Product product = productDao.getById(sameStock.getProduct().getId()).orElseThrow(NullPointerException::new);
                sameStock.setProduct(product);


                return myMapper.fromStockToStockDto(stockDao.modify(sameStock));
            } else {

                Product product = productDao.getById(stockDto.getProductDto().getId()).orElseThrow(NullPointerException::new);
                stock.setProduct(product);

                Shop shop = shopDao.getById(stockDto.getShopDto().getId()).orElseThrow(NullPointerException::new);
                stock.setShop(shop);

                return myMapper.fromStockToStockDto(stockDao.add(stock));
            }
        } catch (Exception e) {
            throw new CustomException("service", "Can't add stock. Error: " + e.getMessage());
        }
    }


    @Override
    public CustomerOrderDto addCustomerOrder(CustomerOrderDto customerOrderDto) {
        try {

            CustomerOrder customerOrder = myMapper.fromCustomerOrderDtoToCustomerOrder(customerOrderDto);
            customerOrder.setDate(LocalDateTime.now());
            paymentDao.getAll().stream().filter(p -> p.getPayment().name().matches(customerOrder.getPayment().getPayment().name())).findAny().ifPresent(customerOrder::setPayment);

            Optional<Stock> stockFromDb = stockDao.getById(customerOrderDto.getStockDto().getId());
            stockFromDb.ifPresent(s -> s.setQuantity(s.getQuantity() - customerOrderDto.getQuantity()));

            customerOrder.setProduct(stockFromDb.get().getProduct());


            return myMapper.fromCustomerOrderToCustomerOrderDto(CustomerOrderDao.add(customerOrder));
        } catch (Exception e) {
            throw new CustomException("service", "Can't add order. Error: " + e.getMessage());
        }
    }

    @Override
    public List<StockDto> getAllStocks() {
        try {
            return stockDao.getAll().stream().map(myMapper::fromStockToStockDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("service", "Can't download stocks. Error: " + e.getMessage());
        }
    }

    @Override
    public List<Long> getAllCustomerOrdersForCustomerDto(CustomerDto customerDto) {
        return CustomerOrderDao.getAll()
                .stream()
                .filter(c -> c.getCustomer().getId().equals(customerDto.getId()))
                .map(co -> co.getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<StockDto> getAllStocksForShopDto(ShopDto shopDto) {
        return stockDao
                .getAll()
                .stream()
                .filter(s -> s.getShop().getId().equals(shopDto.getId()))
                .map(s -> StockDto.builder().id(s.getId()).productDto(ProductDto.builder().name(s.getProduct().getName()).build()).quantity(s.getQuantity()).build()).collect(Collectors.toList());
    }

    @Override
    public List<CustomerOrderDto> getAllCustomerOrders() {
        try {
            return CustomerOrderDao.getAll().stream().map(myMapper::fromCustomerOrderToCustomerOrderDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("service", "Can't download orders. Error:" + e.getMessage());
        }
    }


}