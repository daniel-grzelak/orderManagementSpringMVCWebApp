package com.daniel.domain;

import com.daniel.domain.enums.EGuarantee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal price;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "producer_id")
    private Producer producer;
    @ElementCollection
    @CollectionTable(name = "guarantee_components", joinColumns = {@JoinColumn(name = "product_id")})
    @Column(name = "guarantee_component", unique = true)
    @Enumerated(EnumType.STRING)
    private List<EGuarantee> guarantee_components;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "product")
    private Set<Stock> stocks = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(category, product.category) &&
                Objects.equals(producer, product.producer) &&
                Objects.equals(guarantee_components, product.guarantee_components) &&
                Objects.equals(stocks, product.stocks);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price, category, producer, guarantee_components, stocks);
    }
}
