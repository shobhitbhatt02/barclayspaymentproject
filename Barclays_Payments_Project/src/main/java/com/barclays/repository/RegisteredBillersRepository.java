package com.barclays.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.barclays.entity.RegisteredBillers;


public interface RegisteredBillersRepository extends CrudRepository<RegisteredBillers, Integer> {

	List<RegisteredBillers> findBySequenceId(Integer SequenceId);//QUERY METHODS
	//SELECT ALLL THINGS FROM REGISTEREDTABLE WHERE SEQUENCEID FROM API = SEQUENCE ID IN TABLE
}
