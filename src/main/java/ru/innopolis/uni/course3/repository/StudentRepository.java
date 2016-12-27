package ru.innopolis.uni.course3.repository;

import ru.innopolis.uni.course3.model.Student;

import java.util.List;

/**
 * Created by Артем on 22.12.2016.
 */
public interface StudentRepository {

    Student add(Student student);

    Student update(Student student);

    void delete(int id);

    Student get(int id);

    List<Student> getAll();
}
