package ru.innopolis.uni.course3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.uni.course3.model.Student;
import ru.innopolis.uni.course3.repository.StudentRepository;

import java.util.List;

/**
 * Created by Артем on 24.12.2016.
 */
@Service
public class StudentServiceImpl implements StudentService{

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository repository;

    @Override
    public Student add(Student student) {
        return repository.add(student);
    }

    @Override
    public Student update(Student student) {
        return repository.update(student);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Student get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Student> getAll() {
        return repository.getAll();
    }
}
