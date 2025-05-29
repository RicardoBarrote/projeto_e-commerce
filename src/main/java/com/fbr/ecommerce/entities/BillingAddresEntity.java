package com.fbr.ecommerce.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_billing_address")
public class BillingAddresEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billind_addres_id")
    private Long billingAddressId;

    @Column(name = "addres")
    private String addres;

    @Column(name = "number")
    private String number;

    @Column(name = "complement")
    private String complement;

    @OneToOne(mappedBy = "billingAddresEntity")
    private UserEntity userEntity;

    public BillingAddresEntity() {
    }

    public Long getBillingAddressId() {
        return billingAddressId;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
