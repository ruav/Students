package ru.innopolis.uni.course3;

import ru.innopolis.uni.course3.model.Student;
import ru.innopolis.uni.course3.repository.StudentRepository;
import ru.innopolis.uni.course3.repository.StudentRepositoryImpl;

import java.util.List;

/**
 * Created by Артем on 22.12.2016.
 */
public class MainTest {

    public static void main(String[] args) {

        StudentRepository service = new StudentRepositoryImpl();
        List<Student> students = service.getAll();
        for(Student student: students) {
            System.out.println(student);
        }

    }
}
