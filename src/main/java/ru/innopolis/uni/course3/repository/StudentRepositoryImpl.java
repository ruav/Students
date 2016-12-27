package ru.innopolis.uni.course3.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.innopolis.uni.course3.db.H2DB;
import ru.innopolis.uni.course3.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Артем on 22.12.2016.
 */
@Component
public class StudentRepositoryImpl implements StudentRepository {

    private static final Logger logger = LoggerFactory.getLogger(StudentRepositoryImpl.class);

    public static final String SELECT_ALL_QUERY = "SELECT * FROM Students;";
    public static final String SELECT_BY_ID_QUERY = "SELECT * FROM Students s WHERE s.ID=?;";
//    public static final String INSERT_QUERY_PREFIX =
//            "INSERT INTO Students (ID, NAME, SURNAME, SEX, GROUPNUMBER) VALUES (";
//    public static final String INSERT_QUERY_SUFFIX = ");";
    public static final String PREPARED_DELETE_QUERY = "DELETE FROM Students s WHERE s.ID=?";
    public static final String PREPARED_INSERT_QUERY =
            "INSERT INTO Students (NAME, SURNAME, SEX, GROUPNUMBER) VALUES (?,?,?,?);";
    public static final String PREPARED_UPDATE_QUERY =
            "UPDATE Students s SET s.NAME=?, s.SURNAME=?, s.SEX=?, s.GROUPNUMBER=? WHERE s.ID=?;";

    @Override
    public Student add(Student student) {
        Connection connection = H2DB.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(PREPARED_INSERT_QUERY);) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setString(3, student.getSex());
            statement.setInt(4, student.getGroupNumber());
            statement.executeUpdate();
            logger.info("Student " + student + " successfully added to database");
        } catch (SQLException exception) {
            logger.warn("SQLException during Student update");
        }
        return student;
    }

    @Override
    public Student update(Student student) {

        Connection connection = H2DB.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(PREPARED_UPDATE_QUERY);) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setString(3, student.getSex());
            statement.setInt(4, student.getGroupNumber());
            statement.setInt(5, student.getId());
            statement.executeUpdate();
            logger.info("Student " + student + " successfully saved to database");
        } catch (SQLException exception) {
            logger.warn("SQLException during Student update");
        }
        return student;
    }

    @Override
    public void delete(int id) {
        Connection connection = H2DB.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(PREPARED_DELETE_QUERY);) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            logger.warn("SQLException during Student delete");
        }
    }

    @Override
    public Student get(int id) {
        Connection connection = H2DB.getConnection();
        Student student = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                student = new Student(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
            }
        } catch (SQLException exception) {
            logger.warn("SQLException during Student get");
        }
        return student;
    }

    @Override
    public List<Student> getAll()  {
        Connection connection = H2DB.getConnection();
        List<Student> students = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
                students.add(student);
             }
        } catch (SQLException exception) {
            logger.warn("SQLException during Student get All");
        }
        return students;
    }
}
