package by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api;


import by.it_academy.jd2.Mk_JD2_111_25.HW4.dto.User;

import java.sql.SQLException;

public interface IUserStorage {
    void add(User user) throws ClassNotFoundException, SQLException;
    User get(String login) throws Exception;
    int getUsersCount() throws SQLException;
}

