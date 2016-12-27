package ru.innopolis.uni.course3.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.uni.course3.repository.JournalRepositoryImpl;
import ru.innopolis.uni.course3.repository.LectureRepositoryImpl;
import ru.innopolis.uni.course3.repository.StudentRepositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Артем on 24.12.2016.
 */
public class H2DBTest {

    private static final Logger logger = LoggerFactory.getLogger(H2DBTest.class);

    public static void main(String[] args) {

        try {
            new H2DB().getConnection();
            new H2DBTest().showRows();
        } catch (SQLException exception) {
            logger.warn("SQLException during Student update");
        }
    }

    public void showRows() throws SQLException {

        try(Statement statement = H2DB.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(StudentRepositoryImpl.SELECT_ALL_QUERY);
            while(resultSet.next()) {
                logger.info(new StringBuilder()
                        .append(resultSet.getInt(1)).append(" | ")
                        .append(resultSet.getString(2)).append(" | ")
                        .append(resultSet.getString(3)).append(" | ")
                        .append(resultSet.getString(4)).append(" | ")
                        .append(resultSet.getInt(5))
                        .toString());
            }

            resultSet = statement.executeQuery(LectureRepositoryImpl.SELECT_ALL_QUERY);
            while(resultSet.next()) {
                logger.info(new StringBuilder()
                        .append(resultSet.getInt(1)).append(" | ")
                        .append(resultSet.getString(2)).append(" | ")
                        .append(resultSet.getInt(3))
                        .toString());
            }

            resultSet = statement.executeQuery(JournalRepositoryImpl.SELECT_ALL_QUERY);
            while(resultSet.next()) {
                logger.info(new StringBuilder()
                        .append(resultSet.getInt(1)).append(" | ")
                        .append(resultSet.getString(5)).append(" | ")
                        .append(resultSet.getString(9)).append(" | ")
                        .append(resultSet.getDate(4))
                        .toString());
            }
        }
    }
}
