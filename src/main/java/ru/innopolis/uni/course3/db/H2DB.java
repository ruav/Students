package ru.innopolis.uni.course3.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.uni.course3.repository.JournalRepositoryImpl;
import ru.innopolis.uni.course3.repository.LectureRepositoryImpl;
import ru.innopolis.uni.course3.repository.StudentRepositoryImpl;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Артем on 22.12.2016.
 */
public class H2DB {

    private static final Logger logger = LoggerFactory.getLogger(H2DB.class);

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Can't load jdbc driver", e);
            throw new RuntimeException(e);
        }
    }

    private static Connection connection;

    //public static final String DATABASE_URL = "jdbc:h2:mem:students";
    public static final String DATABASE_URL = "jdbc:h2:~/students";
    public static final String CREATE_SCHEMA_QUERY = "CREATE SCHEMA IF NOT EXISTS Students;" +
            "SET SCHEMA Students";
    public static final String DROP_SCHEMA_QUERY  = "DROP SCHEMA IF EXISTS Students";

    public static final String CREATE_TABLE_STUDENTS_QUERY =
            "CREATE TABLE Students (ID INTEGER PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255), SURNAME VARCHAR(255), SEX VARCHAR(8), GROUPNUMBER INTEGER);";
    public static final String DROP_TABLE_STUDENTS_QUERY = "DROP TABLE IF EXISTS Students;";

    public static final String CREATE_TABLE_LECTURES_QUERY =
            "CREATE TABLE Lectures (ID INTEGER PRIMARY KEY AUTO_INCREMENT, TOPIC VARCHAR(255), DURATION INTEGER);";
    public static final String DROP_TABLE_LECTURES_QUERY = "DROP TABLE IF EXISTS Lectures;";

    public static final String CREATE_TABLE_JOURNAL_QUERY =
            "CREATE TABLE Journal (ID INTEGER PRIMARY KEY AUTO_INCREMENT, STUDENT_ID INTEGER, LECTURE_ID INTEGER, RECORD_DATE DATETIME,\n" +
                    "  FOREIGN KEY (STUDENT_ID) REFERENCES Students(ID) ON DELETE CASCADE,\n" +
                    "  FOREIGN KEY (LECTURE_ID) REFERENCES Lectures(ID) ON DELETE CASCADE);";
    public static final String DROP_TABLE_JOURNAL_QUERY = "DROP TABLE IF EXISTS Journal;";

    public static Connection getConnection() {

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(H2DB.DATABASE_URL);
                initialization();
            } catch (SQLException exception) {
                logger.error("It's impossible to obtain connection to H2DB", exception);
            }
        }
        return connection;
    }

    public void createSchema() throws SQLException {

        try(Statement statement = connection.createStatement()){
            statement.execute(DROP_SCHEMA_QUERY);
            statement.execute(CREATE_SCHEMA_QUERY);
            logger.info("Schema successfully created");
        }
    }

    public void createTable() throws SQLException {
        try(Statement statement = connection.createStatement()){
            statement.execute(DROP_TABLE_STUDENTS_QUERY);
            statement.execute(CREATE_TABLE_STUDENTS_QUERY);
            statement.execute(DROP_TABLE_LECTURES_QUERY);
            statement.execute(CREATE_TABLE_LECTURES_QUERY);
            statement.execute(DROP_TABLE_JOURNAL_QUERY);
            statement.execute(CREATE_TABLE_JOURNAL_QUERY);
            logger.info("Database successfully created");
        }
    }

    public void insertInitialRows() throws SQLException {

        try(PreparedStatement statement = connection.prepareStatement(StudentRepositoryImpl.PREPARED_INSERT_QUERY)){

            statement.setString(1, "Ivan");
            statement.setString(2, "Ivanov");
            statement.setString(3, "MALE");
            statement.setInt(4, 1);
            statement.executeUpdate();
            logger.info("Students: Ivanov row have inserted");

            statement.setString(1, "Peter");
            statement.setString(2, "Petrov");
            statement.setString(3, "MALE");
            statement.setInt(4, 1);
            statement.executeUpdate();
            logger.info("Students: Petrov row have inserted");

            statement.setString(1, "Sidor");
            statement.setString(2, "Sidorov");
            statement.setString(3, "MALE");
            statement.setInt(4, 1);
            statement.executeUpdate();
            logger.info("Students: Sidorov row have inserted");
        }

        try(PreparedStatement statement = connection.prepareStatement(LectureRepositoryImpl.PREPARED_INSERT_QUERY)){

            statement.setString(1, "Sampling analysis");
            statement.setInt(2, 90);
            statement.executeUpdate();
            logger.info("Lectures: Sampling analysis row have inserted");

            statement.setString(1, "Function analysis");
            statement.setInt(2, 90);
            statement.executeUpdate();
            logger.info("Lectures: Function analysis row have inserted");
        }

        try(PreparedStatement statement = connection.prepareStatement(JournalRepositoryImpl.PREPARED_INSERT_QUERY)){

            statement.setInt(1, 1);
            statement.setInt(2, 1);
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.executeUpdate();
            logger.info("Lectures: Sampling analysis row have inserted");
        }

    }

    public static void initialization() {
        try {
            H2DB h2 = new H2DB();
            H2DB.getConnection();
            h2.createSchema();
            h2.createTable();
            h2.insertInitialRows();
        } catch (SQLException exception) {
            logger.warn("SQLException during Student update");
        }
    }

}
