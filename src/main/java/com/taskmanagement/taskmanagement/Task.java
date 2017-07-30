package com.taskmanagement.taskmanagement;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by georgekankava on 29.07.17.
 */
@Entity
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "addedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;

    @Column(name = "completedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completedDate;
}