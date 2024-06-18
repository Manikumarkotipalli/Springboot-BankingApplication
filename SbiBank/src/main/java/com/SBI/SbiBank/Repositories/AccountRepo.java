package com.SBI.SbiBank.Repositories;

import com.SBI.SbiBank.Entities.Account;
import com.SBI.SbiBank.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account ,Long> {
   Account findByUser(User user);
}
