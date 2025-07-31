package by.it_academy.jd2.Mk_JD2_111_25.HW4.storage;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto.User;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api.IUserStorage;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api.exceptions.StorageException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserStorageDB implements IUserStorage {
    private final DataSource dataSource;

    public UserStorageDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) {
        try {
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
        } catch (Exception e) {
            throw new StorageException("Ошибка добавления пользователя в базу", e);
        }
    }

    @Override
    public User get(String login) {

        try {
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
        } catch (Exception e) {
            throw new StorageException("Ошибка запроса данных пользователя", e);
        }
    }

    @Override
    public String getLogin(User user) {
        return "";
    }

    @Override
    public int getCount() {
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("""
                    SELECT COUNT(*)
                    FROM messenger.users
                    """);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (Exception e) {
            throw new StorageException("Ошибка подсчета количества пользователей", e);
        }
    }
}
