package com.SBI.SbiBank.Repositories;

import com.SBI.SbiBank.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User ,Long> {
    User findByUsername(String username);
}
