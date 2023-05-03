package com.sudagoarth.springquasar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "complaint")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private String date;

    @Column(name = "type")
    private String type;

    @Column(name = "title")
    private String title;

    @Column(name = "answer")
    private String answer;

    @Column(name = "message")
    private String message;

    @Column(name = "attach_file")
    private String attach_file;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
