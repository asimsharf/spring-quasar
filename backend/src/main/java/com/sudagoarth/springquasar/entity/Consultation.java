package com.sudagoarth.springquasar.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "consultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "diagnoses")
    private String diagnoses;

    @Column(name = "doctor_instructions")
    private String doctor_instructions;

    @Column(name = "number_diagnoses_session")
    private Integer number_diagnoses_session;

    @Column(name = "expire_date")
    private String expire_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;


}
