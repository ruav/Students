package ru.innopolis.uni.course3.model;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Артем on 22.12.2016.
 */
public class Journal {

    private Integer id;
    private Student student;
    private Lecture lecture;
    private LocalDate date;

    public Journal(Integer id, Student student, Lecture lecture, LocalDate date) {
        this.id = id;
        this.student = student;
        this.lecture = lecture;
        this.date = date;
    }

    public Journal(Integer id, Student student, Lecture lecture, Date date) {
        this.id = id;
        this.student = student;
        this.lecture = lecture;
        this.date = date.toLocalDate();
    }

    public Journal(Student student, Lecture lecture, LocalDate date) {
        this(null, student, lecture, date);
    }

    public Journal(Student student, Lecture lecture, Date date) {
        this(null, student, lecture, date);
    }

    public Integer getId() {
        return id;
    }

    public boolean isNew() {
        return id == null;
    }

    public Student getStudent() {
        return student;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public LocalDate getDate() {
        return date;
    }

    public Date getSQLDate() {
        return Date.valueOf(this.date);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDateFromSQL(Date date) {
        this.date = date.toLocalDate();
    }
}
