package by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto.User;

public interface IUserStorage {
    void add(User user);
    User get(String login);
    String getLogin(User user);
    int getCount();
}

