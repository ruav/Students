package ru.innopolis.uni.course3.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.innopolis.uni.course3.db.H2DB;
import ru.innopolis.uni.course3.model.Lecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Артем on 24.12.2016.
 */
@Component
public class LectureRepositoryImpl implements LectureRepository {

    private static final Logger logger = LoggerFactory.getLogger(LectureRepositoryImpl.class);

    public static final String SELECT_ALL_QUERY = "SELECT * FROM Lectures;";
    public static final String SELECT_BY_ID_QUERY = "SELECT * FROM Lectures s WHERE s.ID=?;";
    public static final String PREPARED_INSERT_QUERY =
            "INSERT INTO Lectures (TOPIC, DURATION) VALUES (?,?);";
    public static final String PREPARED_DELETE_QUERY = "DELETE FROM Lectures s WHERE s.ID=?";
    public static final String PREPARED_UPDATE_QUERY =
            "UPDATE Lectures s SET s.TOPIC=?, s.DURATION=? WHERE s.ID=?;";

    @Override
    public Lecture add(Lecture lecture) {
        Connection connection = H2DB.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(PREPARED_INSERT_QUERY);) {
            statement.setString(1, lecture.getTopic());
            statement.setInt(2, lecture.getDuration());
            statement.executeUpdate();
            logger.info("Lecture " + lecture + " successfully added to database");
        } catch (SQLException exception) {
            logger.warn("SQLException during Lecture update");
        }
        return lecture;
    }

    @Override
    public Lecture update(Lecture lecture) {
        Connection connection = H2DB.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(PREPARED_UPDATE_QUERY);) {
            statement.setString(1, lecture.getTopic());
            statement.setInt(2, lecture.getDuration());
            statement.setInt(3, lecture.getId());
            statement.executeUpdate();
            logger.info("Lecture " + lecture + " successfully saved to database");
        } catch (SQLException exception) {
            logger.warn("SQLException during Lecture update");
        }
        return lecture;
    }

    @Override
    public void delete(int id) {
        Connection connection = H2DB.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(PREPARED_DELETE_QUERY);) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            logger.warn("SQLException during Lecture delete");
        }
    }

    @Override
    public Lecture get(int id) {
        Connection connection = H2DB.getConnection();
        Lecture lecture = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                lecture = new Lecture(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3));
            }
        } catch (SQLException exception) {
            logger.warn("SQLException during Lecture get");
        }
        return lecture;
    }

    @Override
    public List<Lecture> getAll() {
        Connection connection = H2DB.getConnection();
        List<Lecture> lectures = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Lecture student = new Lecture(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3));
                lectures.add(student);
            }
        } catch (SQLException exception) {
            logger.warn("SQLException during Lecture get All");
        }
        return lectures;
    }
}
