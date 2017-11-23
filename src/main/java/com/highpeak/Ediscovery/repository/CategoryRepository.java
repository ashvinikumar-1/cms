package com.highpeak.Ediscovery.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.highpeak.Ediscovery.model.Category;

@Transactional
public interface CategoryRepository extends CrudRepository<Category,String> {

}
