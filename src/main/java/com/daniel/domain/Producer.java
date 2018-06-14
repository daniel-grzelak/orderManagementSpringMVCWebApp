package com.daniel.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "country_id")
    private Country country;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "trade_id", unique = true)
    private Trade trade;
    @OneToMany(mappedBy = "producer")
    private Set<Product> products = new HashSet<>();


}
