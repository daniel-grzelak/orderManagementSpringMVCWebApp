package com.daniel.domain;

import com.daniel.domain.enums.EPayment;
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
public class Payment {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private EPayment payment;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "payment")
    private Set<Customer_Order> customer_orders = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment1 = (Payment) o;
        return Objects.equals(id, payment1.id) &&
                payment == payment1.payment &&
                Objects.equals(customer_orders, payment1.customer_orders);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, payment, customer_orders);
    }
}
