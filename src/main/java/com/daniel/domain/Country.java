package com.daniel.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(id, country.id) &&
                Objects.equals(name, country.name) &&
                Objects.equals(shops, country.shops) &&
                Objects.equals(customers, country.customers) &&
                Objects.equals(producers, country.producers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, shops, customers, producers);
    }
}
