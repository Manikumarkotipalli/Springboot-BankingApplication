package com.SBI.SbiBank.Controllers;

import com.SBI.SbiBank.Entities.Account;
import com.SBI.SbiBank.Entities.Transcation;
import com.SBI.SbiBank.Entities.User;
import com.SBI.SbiBank.Services.AccountService;
import com.SBI.SbiBank.Services.CustomUserDetails;
import com.SBI.SbiBank.Services.TransactionService;
import com.SBI.SbiBank.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class BankingController {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user",new User());
        return  "register.html";
    }
    @PostMapping("/register")
    public  String registerUser(@ModelAttribute User user){
        userService.save(user);
        accountService.createAccount(user);
        return "redirect:/login.html";
    }
    @GetMapping("/login")
    public String showLoginForm(){
        return "login.html";
    }


    @GetMapping("/account")
    public String showAccount(Model model, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        Account account = accountService.findByUser(user);

        model.addAttribute("account", account);
        return "account.html";
    }

    @PostMapping("/account/deposit")
    public String deposit(@RequestParam double amount, Authentication authentication,Model model) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        Account account = accountService.findByUser(user);
        transactionService.deposit(account, amount);
        model.addAttribute("message", "Deposit successful. New balance: " + account.getBalance());
        model.addAttribute("account", account);
        return "account";

    }

    @PostMapping("/account/withdraw")
    public String withdraw(@RequestParam double amount, Authentication authentication,Model model) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        Account account = accountService.findByUser(user);

        try {
            transactionService.withdraw(account, amount);
            model.addAttribute("message", "Withdrawal successful. New balance: " + account.getBalance());
        } catch (RuntimeException e) {
            model.addAttribute("message", "Withdrawal failed: " + e.getMessage());
        }
        model.addAttribute("account", account);
        return "account";
    }

    @GetMapping("/transactions")
    public String showTransactions(Model model, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        Account account = accountService.findByUser(user);

        List<Transcation> transactions = transactionService.getTransactions(account);
        model.addAttribute("transactions", transactions);
        return "transactions.html";
    }

}
