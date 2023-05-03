package com.sudagoarth.springquasar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "place_of_study")
    private String placeOfStudy;

    @Column(name = "image")
    private String image;

    @Column(name = "certificates")
    private String certificates;

    @Column(name = "social_media")
    private String socialMedia;

    @Column(name = "about_the_doctor")
    private String aboutTheDoctor;

    @Column(name = "rating_percentage")
    private Float ratingPercentage;

    @Column(name = "consultation_price")
    private Float consultationPrice;

    @Column(name = "skills")
    private String skills;

    @Column(name = "iqama")
    private Long iqama;

    @Column(name = "work_experience")
    private String workExperience;

    @Column(name = "authority_card")
    private String authorityCard;

    @Column(name = "health_affairs_licensing")
    private String healthAffairsLicensing;

    @Column(name = "is_verified")
    private Integer isVerified;

    @Column(name = "is_receiving_appointments")
    private Integer isReceivingAppointments;

}
