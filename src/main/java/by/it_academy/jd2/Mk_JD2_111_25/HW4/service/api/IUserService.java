package by.it_academy.jd2.Mk_JD2_111_25.HW4.service.api;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto.User;

public interface IUserService {
    String add(User user);
    String authenticate(String login, String password);
    User getUser(String login);
    String check(String login);
    int getCount();
}
