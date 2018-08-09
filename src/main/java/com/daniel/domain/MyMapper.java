package com.daniel.domain;



import com.daniel.dto.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedList;

@Component
public class MyMapper {
    public ProducerDto fromProducerToProducerDto(Producer producer) {
        return producer == null ? null : ProducerDto.builder()
                .id(producer.getId())
                .name(producer.getName())
                .tradeDto(producer.getTrade() == null ? null : fromTradeToTradeDto(producer.getTrade()))
                .countryDto(producer.getCountry() == null ? null : fromCountryToCountryDto(producer.getCountry()))
                .build();
    }

    public Producer fromProducerDtoToProducer(ProducerDto producerDto) {
        return producerDto == null ? null : Producer.builder()
                .id(producerDto.getId())
                .name(producerDto.getName())
                .country(producerDto.getCountryDto() == null ? null : fromCountryDtoToCountry(producerDto.getCountryDto()))
                .trade(producerDto.getTradeDto() == null ? null : fromTradeDtoToTrade(producerDto.getTradeDto()))
                .products(new HashSet<>())
                .build();
    }


    public ProductDto fromProductToProductDto(Product product) {
        return product == null ? null : ProductDto.builder()
                .id(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                .categoryDto(product.getCategory() == null ? null : fromCategoryToCategoryDto(product.getCategory()))
                .producerDto(product.getProducer() == null ? null : fromProducerToProducerDto(product.getProducer()))
                .guarantee_components(new LinkedList<>())
                .build();
    }

    public Product fromProductDtoToProduct(ProductDto productDto) {
        return productDto == null ? null : Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .category(productDto.getCategoryDto() == null ? null : fromCategoryDtoToCategory(productDto.getCategoryDto()))
                .producer(productDto.getProducerDto() == null ? null : fromProducerDtoToProducer(productDto.getProducerDto()))
                .guarantee_components(new LinkedList<>())
                .stocks(new HashSet<>())
                .build();
    }

    public CategoryDto fromCategoryToCategoryDto(Category category) {
        return category == null ? null : CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category fromCategoryDtoToCategory(CategoryDto categoryDto) {
        return categoryDto == null ? null : Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .products(new HashSet<>())
                .build();

    }

    public CountryDto fromCountryToCountryDto(Country country) {
        return country == null ? null : CountryDto.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }

    public Country fromCountryDtoToCountry(CountryDto countryDto) {
        return countryDto == null ? null : Country.builder()
                .id(countryDto.getId())
                .name(countryDto.getName())
                .customers(new HashSet<>())
                .producers(new HashSet<>())
                .shops(new HashSet<>())
                .build();
    }

    public CustomerOrderDto fromCustomerOrderToCustomerOrderDto(CustomerOrder customer_Order) {
        return customer_Order == null ? null : CustomerOrderDto.builder()
                .id(customer_Order.getId())
                .date(customer_Order.getDate())
                .discount(customer_Order.getDiscount())
                .quantity(customer_Order.getQuantity())
                .customerDto(customer_Order.getCustomer() == null ? null : fromCustomerToCustomerDto(customer_Order.getCustomer()))
                .paymentDto(customer_Order.getPayment() == null ? null : fromPaymentToPaymentDto(customer_Order.getPayment()))
                .productDto(customer_Order.getProduct() == null ? null : fromProductToProductDto(customer_Order.getProduct()))
                .build();
    }

    public CustomerOrder fromCustomerOrderDtoToCustomerOrder(CustomerOrderDto customer_OrderDto) {
        return customer_OrderDto == null ? null : CustomerOrder.builder()
                .id(customer_OrderDto.getId())
                .date(customer_OrderDto.getDate())
                .discount(customer_OrderDto.getDiscount())
                .quantity(customer_OrderDto.getQuantity())
                .customer(customer_OrderDto.getCustomerDto() == null ? null : fromCustomerDtoToCustomer(customer_OrderDto.getCustomerDto()))
                .payment(customer_OrderDto.getPaymentDto() == null ? null : fromPaymentDtoToPayment(customer_OrderDto.getPaymentDto()))
                .product(customer_OrderDto.getProductDto() == null ? null : fromProductDtoToProduct(customer_OrderDto.getProductDto()))
                .build();
    }

    public CustomerDto fromCustomerToCustomerDto(Customer customer) {
        return customer == null ? null : CustomerDto.builder()
                .id(customer.getId())
                .age(customer.getAge())
                .name(customer.getName())
                .surname(customer.getSurname())
                .countryDto(customer.getCountry() == null ? null : fromCountryToCountryDto(customer.getCountry()))
                .build();
    }

    public Customer fromCustomerDtoToCustomer(CustomerDto customerDto) {
        return customerDto == null ? null : Customer.builder()
                .id(customerDto.getId())
                .age(customerDto.getAge())
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .country(customerDto.getCountryDto() == null ? null : fromCountryDtoToCountry(customerDto.getCountryDto()))
                .customer_orders(new HashSet<>())
                .build();
    }

    public PaymentDto fromPaymentToPaymentDto(Payment payment) {
        return payment == null ? null : PaymentDto.builder()
                .id(payment.getId())
                .payment(payment.getPayment())
                .build();
    }

    public Payment fromPaymentDtoToPayment(PaymentDto paymentDto) {
        return paymentDto == null ? null : Payment.builder()
                .id(paymentDto.getId())
                .payment(paymentDto.getPayment())
                .customer_orders(new HashSet<>())
                .build();
    }

    public ShopDto fromShopToShopDto(Shop shop) {
        return shop == null ? null : ShopDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .countryDto(shop.getCountry() == null ? null : fromCountryToCountryDto(shop.getCountry()))
                .build();
    }

    public Shop fromShopDtoToShop(ShopDto shopDto) {
        return shopDto == null ? null : Shop.builder()
                .id(shopDto.getId())
                .name(shopDto.getName())
                .country(shopDto.getCountryDto() == null ? null : fromCountryDtoToCountry(shopDto.getCountryDto()))
                .stocks(new HashSet<>())
                .build();
    }

    public StockDto fromStockToStockDto(Stock stock) {
        return stock == null ? null : StockDto.builder()
                .id(stock.getId())
                .quantity(stock.getQuantity())
                .productDto(stock.getProduct() == null ? null : fromProductToProductDto(stock.getProduct()))
                .shopDto(stock.getShop() == null ? null : fromShopToShopDto(stock.getShop()))
                .build();
    }

    public Stock fromStockDtoToStock(StockDto stockDto) {
        return stockDto == null ? null : Stock.builder()
                .id(stockDto.getId())
                .quantity(stockDto.getQuantity())
                .product(stockDto.getProductDto() == null ? null : fromProductDtoToProduct(stockDto.getProductDto()))
                .shop(stockDto.getShopDto() == null ? null : fromShopDtoToShop(stockDto.getShopDto()))
                .build();
    }

    public TradeDto fromTradeToTradeDto(Trade trade) {
        return  trade == null ? null : TradeDto.builder()
                .id(trade.getId())
                .name(trade.getName())
                .build();
    }

    public Trade fromTradeDtoToTrade(TradeDto tradeDto) {
        return tradeDto == null ? null : Trade.builder()
                .id(tradeDto.getId())
                .name(tradeDto.getName())
                .producers(new HashSet<>())
                .build();
    }
}
