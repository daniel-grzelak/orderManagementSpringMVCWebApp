package com.daniel.dao.implementations;


import com.daniel.dao.interfaces.PaymentDao;
import com.daniel.domain.Payment;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDaoImpl extends AbstractGenericDao<Payment> implements PaymentDao{

}
