package com.example.javareact.repository;

import com.example.javareact.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagingRepository extends PagingAndSortingRepository<Employee, Long> {

}
