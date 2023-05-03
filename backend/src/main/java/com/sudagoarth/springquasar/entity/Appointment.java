package com.sudagoarth.springquasar.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "type")
    private Integer type;

    @Column(name = "time")
    private String time;

    @Column(name = "date")
    private String date;

    @Column(name = "status")
    private Integer status;

    @Column(name = "number")
    private String number;

    @Column(name = "duration")
    private String duration;

    @Column(name = "reason")
    private String reason;

    @Column(name = "for_another_patient")
    private Integer forAnotherPatient;

    @Column(name = "cancel")
    private Integer cancel;

    @Column(name = "reject")
    private Integer reject;



}
