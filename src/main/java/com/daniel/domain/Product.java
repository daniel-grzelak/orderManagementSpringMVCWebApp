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
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "producer_id")
    private Producer producer;
    @ElementCollection
    @CollectionTable(name = "guarantee_components", joinColumns = {@JoinColumn(name = "product_id")})
    @Column(name = "guarantee_component", unique = true)
    @Enumerated(EnumType.STRING)
    private List<EGuarantee> guarantee_components;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "product")
    private Set<Stock> stocks = new HashSet<>();


}
