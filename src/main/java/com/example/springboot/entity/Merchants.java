package com.example.springboot.entity;

import javax.persistence.*;

@Table(name = "merchants")
public class Merchants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String merchant_id;

    @Column(name = "merchant_name")
    private String merchant_name;

    @Column(name = "contact_person")
    private String contact_person;

    @Column(name = "contact_phone")
    private String contact_phone;

    @Column(name = "store_address")
    private String store_address;

    @Column(name = "business_license_number")
    private String business_license_number;

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getBusiness_license_number() {
        return business_license_number;
    }

    public void setBusiness_license_number(String business_license_number) {
        this.business_license_number = business_license_number;
    }
}
