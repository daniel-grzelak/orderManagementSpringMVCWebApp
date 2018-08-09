package com.daniel.dao.implementations;

import com.daniel.dao.interfaces.CustomerOrderDao;
import com.daniel.domain.CustomerOrder;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerOrderDaoImpl extends AbstractGenericDao<CustomerOrder> implements CustomerOrderDao {

}
