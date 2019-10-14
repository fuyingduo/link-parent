package com.custom.dao;

import com.custom.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * created by fuyd on 2019-07-10
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByIdIn(Collection<Integer> ids);
}
