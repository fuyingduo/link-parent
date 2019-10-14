package com.custom.dao;

import com.custom.entity.ProductSpecifications;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by fuyd on 2019-07-10
 */
public interface ProductSpecificationsRepository extends JpaRepository<ProductSpecifications, Integer> {
}
