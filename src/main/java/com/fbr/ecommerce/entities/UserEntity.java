package com.fbr.ecommerce.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_addres_id")
    private BillingAddresEntity billingAddresEntity;


    public UserEntity() {
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BillingAddresEntity getBillingAddresEntity() {
        return billingAddresEntity;
    }

    public void setBillingAddresEntity(BillingAddresEntity billingAddresEntity) {
        this.billingAddresEntity = billingAddresEntity;
    }
}
