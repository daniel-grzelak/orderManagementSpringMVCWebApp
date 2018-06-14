package com.daniel.dao.implementations;

import com.daniel.dao.interfaces.CustomerDao;
import com.daniel.domain.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl extends AbstractGenericDao<Customer> implements CustomerDao {

}
