package ru.innopolis.uni.course3.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.innopolis.uni.course3.db.H2DB;
import ru.innopolis.uni.course3.model.Journal;
import ru.innopolis.uni.course3.model.Lecture;
import ru.innopolis.uni.course3.model.Student;

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
public class JournalRepositoryImpl implements JournalRepository {

    private static final Logger logger = LoggerFactory.getLogger(JournalRepositoryImpl.class);

    public static final String SELECT_ALL_QUERY = "SELECT j.ID, j.STUDENT_ID, j.LECTURE_ID, j.RECORD_DATE,\n" +
            "  s.NAME, s.SURNAME, s.SEX, s.GROUPNUMBER,\n" +
            "  l.TOPIC, l.DURATION\n" +
            "FROM Journal j\n" +
            "  INNER JOIN Students s ON j.STUDENT_ID = s.ID\n" +
            "  INNER JOIN Lectures l ON j.LECTURE_ID = l.ID;";
    public static final String SELECT_BY_ID_QUERY = "SELECT j.ID, j.STUDENT_ID, j.LECTURE_ID, j.RECORD_DATE,\n" +
            "  s.NAME, s.SURNAME, s.SEX, s.GROUPNUMBER,\n" +
            "  l.TOPIC, l.DURATION\n" +
            "FROM Journal j\n" +
            "  INNER JOIN Students s ON j.STUDENT_ID = s.ID\n" +
            "  INNER JOIN Lectures l ON j.LECTURE_ID = l.ID\n" +
            "\n" +
            "WHERE s.ID=?";
    public static final String PREPARED_INSERT_QUERY =
            "INSERT INTO Journal (STUDENT_ID, LECTURE_ID, RECORD_DATE) VALUES (?,?,?);";
    public static final String PREPARED_DELETE_QUERY = "DELETE FROM Journal s WHERE s.ID=?";
    public static final String PREPARED_UPDATE_QUERY =
            "UPDATE Journal s SET s.STUDENT_ID=?, s.LECTURE_ID=?, s.RECORD_DATE=? WHERE s.ID=?;";

    @Override
    public Journal add(Journal journal) {
        Connection connection = H2DB.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(PREPARED_INSERT_QUERY);) {
            statement.setInt(1, journal.getStudent().getId());
            statement.setInt(2, journal.getLecture().getId());
            statement.setDate(3, journal.getSQLDate());
            statement.executeUpdate();
            logger.info("Journal " + journal + " successfully added to database");
        } catch (SQLException exception) {
            logger.warn("SQLException during Journal update");
        }
        return journal;
    }

    @Override
    public Journal update(Journal journal) {
        Connection connection = H2DB.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(PREPARED_UPDATE_QUERY);) {
            statement.setInt(1, journal.getStudent().getId());
            statement.setInt(2, journal.getLecture().getId());
            statement.setDate(3, journal.getSQLDate());
            statement.setInt(4, journal.getId());
            statement.executeUpdate();
            logger.info("Journal " + journal + " successfully saved to database");
        } catch (SQLException exception) {
            logger.warn("SQLException during Journal update");
        }
        return journal;
    }

    @Override
    public void delete(int id) {
        Connection connection = H2DB.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(PREPARED_DELETE_QUERY);) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            logger.warn("SQLException during Journal delete");
        }
    }

    @Override
    public Journal get(int id) {
        Connection connection = H2DB.getConnection();
        Journal record = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                record = new Journal(resultSet.getInt(1),
                        new Student(resultSet.getInt(2),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                resultSet.getInt(8)),
                        new Lecture(resultSet.getInt(3),
                                resultSet.getString(9),
                                resultSet.getInt(10)),
                        resultSet.getDate(4));
            }
        } catch (SQLException exception) {
            logger.warn("SQLException during Journal get");
        }
        return record;
    }

    @Override
    public List<Journal> getAll() {
        Connection connection = H2DB.getConnection();
        List<Journal> records = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Journal record = new Journal(resultSet.getInt(1),
                        new Student(resultSet.getInt(2),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                resultSet.getInt(8)),
                        new Lecture(resultSet.getInt(3),
                                resultSet.getString(9),
                                resultSet.getInt(10)),
                        resultSet.getDate(4));
                records.add(record);
            }
        } catch (SQLException exception) {
            logger.warn("SQLException during Journal get All");
        }
        return records;
    }
}
