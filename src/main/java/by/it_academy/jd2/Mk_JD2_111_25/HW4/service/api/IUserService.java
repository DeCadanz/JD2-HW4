package by.it_academy.jd2.Mk_JD2_111_25.HW4.service.api;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.dto.User;

import java.sql.SQLException;

public interface IUserService {
    String add(User user) throws Exception;
    String authenticate(String login, String password) throws Exception;
}
