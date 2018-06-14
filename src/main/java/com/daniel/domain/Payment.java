package com.daniel.domain;

import com.daniel.domain.enums.EPayment;
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
public class Payment {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private EPayment payment;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "payment")
    private Set<Customer_Order> customer_orders = new HashSet<>();
}
