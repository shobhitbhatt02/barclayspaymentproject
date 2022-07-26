package com.barclays.repository;

import org.springframework.data.repository.CrudRepository;


import com.barclays.entity.Bills;

public interface BillsRepository extends CrudRepository<Bills, Integer> {

}
