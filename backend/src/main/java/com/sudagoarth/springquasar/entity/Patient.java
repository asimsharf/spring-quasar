package com.sudagoarth.springquasar.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "patient_number")
    private String patientNumber;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "family_member_phone")
    private String familyMemberPhone;

    @Column(name = "psychiatrist")
    private Integer psychiatrist;

    @Column(name = "psychiatrist_description")
    private String psychiatristDescription;

    @Column(name = "disability")
    private Integer disability;

    @Column(name = "disability_description")
    private String disabilityDescription;

    @Column(name = "health_problem")
    private Integer healthProblem;

    @Column(name = "health_problem_description")
    private String healthProblemDescription;

    @Column(name = "medication")
    private Integer medication;

    @Column(name = "medication_description")
    private String medicationDescription;

    @Column(name = "habits")
    private String habits;

    @Column(name = "habits_other_details")
    private String habitsOtherDetails;

    @Column(name = "diseases")
    private Integer diseases;

    @Column(name = "diseases_other_details")
    private String diseasesOtherDetails;

}
