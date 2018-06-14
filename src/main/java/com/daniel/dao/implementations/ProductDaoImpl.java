package com.daniel.dao.implementations;


import com.daniel.dao.interfaces.ProductDao;
import com.daniel.domain.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl extends AbstractGenericDao<Product> implements ProductDao {

}
