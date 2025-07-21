package by.it_academy.jd2.Mk_JD2_111_25.HW4.storage;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.dto.User;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api.IUserStorage;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserStorageDB implements IUserStorage {
    public static DataSource dataSource;

    static {
        ComboPooledDataSource cpds = new ComboPooledDataSource();

        try {
            cpds.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/messenger");
        cpds.setUser("postgres");
        cpds.setPassword("postgres");

        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);

        dataSource = cpds;
    }

    @Override
    public void add(User user) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement statement = conn.prepareStatement("""
                    INSERT INTO messenger.users
                        (login, password, full_name, birth_date, reg_date, role_id)
                    	VALUES (?, ?, ?, ?, ?, ?);
                """);

        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getFullName());
        statement.setObject(4, user.getBirthDate());
        statement.setObject(5, user.getRegDate());
        statement.setInt(6, user.getRole());

        statement.executeUpdate();
    }

    @Override
    public User get(String login) throws Exception {

        Connection conn = dataSource.getConnection();
        PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM messenger.users
                    WHERE
                        login = ?;
                """);

        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet);
            return (User.builder()
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password"))
                    .fullName(resultSet.getString("full_name"))
                    .birthDate(resultSet.getObject("birth_date", LocalDate.class))
                    .regDate(resultSet.getObject("reg_date", LocalDateTime.class))
                    .role_id(resultSet.getInt("role_id"))
                    .build());
        } else {
            return null;
        }
    }

    @Override
    public int getUsersCount() throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement statement = conn.prepareStatement("""
                SELECT COUNT(*)
                FROM messenger.users
                """);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }
}
