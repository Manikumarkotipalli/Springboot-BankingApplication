package com.SBI.SbiBank.Repositories;

import com.SBI.SbiBank.Entities.Account;
import com.SBI.SbiBank.Entities.Transcation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transcation,Long> {
    List<Transcation> findByAccount(Account account);
}
