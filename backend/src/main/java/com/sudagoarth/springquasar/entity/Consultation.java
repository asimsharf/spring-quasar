package com.sudagoarth.springquasar.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "consultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
