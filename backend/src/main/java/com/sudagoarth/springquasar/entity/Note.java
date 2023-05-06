package com.sudagoarth.springquasar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "note_date")
    private String noteDate;

    @Column(name = "note_time")
    private String noteDime;

    @Column(name = "note_title")
    private String noteTitle;

    @Column(name = "note_description")
    private String noteDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

}
