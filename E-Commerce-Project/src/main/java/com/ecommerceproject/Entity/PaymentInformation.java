package com.ecommerceproject.Entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInformation {

    @Column(name = "Card_Holder_Name")
    private String cardHolderName;
    @Column(name = "Card_Number")
    private String cardNumber;
    @Column(name = "Expiration_Date")
    private LocalDate expirationDate;
    private String CVV;
}
