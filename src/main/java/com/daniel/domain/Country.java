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

public class Country {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "country")
    private Set<Shop> shops = new HashSet<>();
    @OneToMany(mappedBy = "country")
    private Set<Customer> customers = new HashSet<>();
    @OneToMany(mappedBy = "country")
    private Set<Producer> producers = new HashSet<>();
}
