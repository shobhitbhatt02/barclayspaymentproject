package com.barclays.repository;

import org.springframework.data.repository.CrudRepository;


import com.barclays.entity.Accounts_Transaction;

public interface AccountTransactionRepository extends CrudRepository<Accounts_Transaction, Integer>{

}
