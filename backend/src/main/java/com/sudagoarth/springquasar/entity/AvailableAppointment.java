package com.sudagoarth.springquasar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "available_appointment")
public class AvailableAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
