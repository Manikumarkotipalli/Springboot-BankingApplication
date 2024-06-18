package com.SBI.SbiBank.Services;

import com.SBI.SbiBank.Entities.Account;
import com.SBI.SbiBank.Entities.Transcation;
import com.SBI.SbiBank.Repositories.TransactionRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@NoArgsConstructor
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;
    public Transcation deposit(Account account,double amount){
        account.setBalance(account.getBalance()+amount);
        Transcation transcation = new Transcation();
        transcation.setAccount(account);
        transcation.setType("deposit");
        transcation.setTimestamp(LocalDate.from(LocalDateTime.now()));
        transcation.setAmount(amount);
        return transactionRepo.save(transcation);
    }

   public Transcation withdraw(Account account,double ammount) {
       if (account.getBalance() < ammount) {
           throw new RuntimeException("Insufficent balance");

       }
       account.setBalance(account.getBalance()-ammount);
       Transcation transcation = new Transcation();
       transcation.setAmount(ammount);
       transcation.setType("withdraw");
       transcation.setTimestamp(LocalDate.from(LocalDateTime.now()));
       transcation.setAccount(account);
      return  transactionRepo.save(transcation);
   }

    public List<Transcation> getTransactions(Account account) {
        return transactionRepo.findByAccount(account);
    }

}
