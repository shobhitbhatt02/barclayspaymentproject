package com.barclays.repository;

import org.springframework.data.repository.CrudRepository;

import com.barclays.entity.Accounts;


public interface AccountsRepository extends CrudRepository<Accounts, Integer> {

}
