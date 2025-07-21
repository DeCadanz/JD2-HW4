package by.it_academy.jd2.Mk_JD2_111_25.HW4.service;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.dto.User;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.UserStorageDB;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api.IUserStorage;

import java.util.Objects;


public class UserService implements IUserService {
    @Override
    public String add(User user) throws Exception {
        IUserStorage storage = new UserStorageDB();
        if (storage.get(user.getLogin()) == null) {
            storage.add(user);
            return "";
        }
        return "Такой пользователь уже существует!";
    }

    @Override
    public String authenticate(String login, String password) throws Exception {
        IUserStorage storage = new UserStorageDB();
        CryptoService crypto = new CryptoService();

        if (storage.get(login) == null) {
            return "Пользователь не найден!";
        }

        if (!Objects.equals(crypto.doCrypt(password), storage.get(login).getPassword())) {
            return "Неверный пароль!";
        } else {
            return "";
        }
    }
}
