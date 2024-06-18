package com.SBI.SbiBank.Services;

import com.SBI.SbiBank.Entities.Account;
import com.SBI.SbiBank.Entities.User;
import com.SBI.SbiBank.Repositories.AccountRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;

    public Account createAccount(User user){
        Account account = new Account();
        account.setUser(user);
        account.setBalance(0.0);
       return  accountRepo.save(account);
    }
    public Account findByUser(User user){
        return accountRepo.findByUser(user);

    }}
