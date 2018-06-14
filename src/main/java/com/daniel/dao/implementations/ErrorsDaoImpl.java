package com.daniel.dao.implementations;

import com.daniel.dao.interfaces.ErrorsDao;
import com.daniel.domain.Errors;
import org.springframework.stereotype.Repository;


@Repository
public class ErrorsDaoImpl extends AbstractGenericDao<Errors> implements ErrorsDao {

}
