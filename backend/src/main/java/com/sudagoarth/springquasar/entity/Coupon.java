package com.sudagoarth.springquasar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "coupon")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Column(name = "code")
    private String code;

    @Column(name = "is_enabled")
    private Integer isEnabled;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "discount_description")
    private String discountDescription;

    @Column(name = "discount_type")
    private String discountType;

    @Column(name = "times_usage")
    private Integer timesUsage;

    @Column(name = "expire_date")
    private String expireDate;


}
