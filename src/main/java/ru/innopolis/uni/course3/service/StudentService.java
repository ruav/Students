package ru.innopolis.uni.course3.service;

import ru.innopolis.uni.course3.model.Student;

import java.util.List;

/**
 * Created by Артем on 24.12.2016.
 */
public interface StudentService {

    Student add(Student student);

    Student update(Student student);

    void delete(int id);

    Student get(int id);

    List<Student> getAll();
}
