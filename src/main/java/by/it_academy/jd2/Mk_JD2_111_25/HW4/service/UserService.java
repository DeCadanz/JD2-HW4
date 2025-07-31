package by.it_academy.jd2.Mk_JD2_111_25.HW4.service;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.ContextFactory;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto.User;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api.IUserStorage;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;


public class UserService implements IUserService {

    private final IUserStorage storage;

    public UserService(IUserStorage storage) {
        this.storage = storage;
    }

    @Override
    public String add(User user) {
        if (storage.get(user.getLogin()) == null) {
            storage.add(user);
            return "";
        }
        return "Такой пользователь уже существует!";
    }

    @Override
    public String authenticate(String login, String password) {
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

    @Override
    public User getUser(String login) {
        return storage.get(login);
    }

    @Override
    public String check(String login) {
        if (storage.get(login) == null) {
            return "Пользователь не найден!";
        } else {
            return "";
        }
    }

    @Override
    public int getCount() {
        return storage.getCount();
    }
}
