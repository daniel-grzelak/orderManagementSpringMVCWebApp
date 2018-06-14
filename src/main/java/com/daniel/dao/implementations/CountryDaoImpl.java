package com.daniel.dao.implementations;

import com.daniel.dao.interfaces.CountryDao;
import com.daniel.domain.Country;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDaoImpl extends AbstractGenericDao<Country> implements CountryDao {

}
