package com.ecommerceproject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String mobile;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;


}
