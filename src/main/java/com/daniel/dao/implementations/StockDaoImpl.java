package com.daniel.dao.implementations;


import com.daniel.dao.interfaces.StockDao;
import com.daniel.domain.Stock;
import org.springframework.stereotype.Repository;

@Repository
public class StockDaoImpl extends AbstractGenericDao<Stock> implements StockDao {

}
