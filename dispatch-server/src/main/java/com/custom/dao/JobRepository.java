package com.custom.dao;

import com.custom.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by fuyd on 2020-01-06
 */
public interface JobRepository extends JpaRepository<Job, Integer> {

    Page<Job> findJobsByOrderByStatusAscCreateDateDesc(Pageable page);

    Job findJobByNameAndServiceType(String name, Integer serviceType);

    Job findJobByName(String name);
}
