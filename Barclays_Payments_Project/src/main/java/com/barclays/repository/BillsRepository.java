package com.barclays.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.barclays.entity.Bills;
import com.barclays.entity.RegisteredBillers;

public interface BillsRepository extends CrudRepository<Bills, Integer> {
	
	List<Bills> findByBillerCode(Integer billerCode);
	
}
