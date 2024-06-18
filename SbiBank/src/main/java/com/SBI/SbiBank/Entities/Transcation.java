package com.SBI.SbiBank.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Transcation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate timestamp;
    private String type;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
