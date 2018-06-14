package com.daniel.dao.implementations;

import com.daniel.dao.interfaces.CategoryDao;
import com.daniel.domain.Category;
import org.springframework.stereotype.Repository;


@Repository
public class CategoryDaoImpl extends AbstractGenericDao<Category> implements CategoryDao {

}
