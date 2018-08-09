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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(id, producer.id) &&
                Objects.equals(name, producer.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
