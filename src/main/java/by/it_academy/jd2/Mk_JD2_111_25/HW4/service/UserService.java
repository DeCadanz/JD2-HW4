package by.it_academy.jd2.Mk_JD2_111_25.HW4.service;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.ContextFactory;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto.User;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.api.ICryptoService;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api.IUserStorage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserService implements IUserService {

    private final IUserStorage storage;
    final int ROLE_ID = 3;

    public UserService(IUserStorage storage) {
        this.storage = storage;
    }

    @Override
    public String add(User user) {
        CryptoService crypto = new CryptoService();

        if (storage.get(user.getLogin()) != null) {
            return "Такой пользователь уже существует!";
        }
        if (user.getLogin().trim().isBlank()) {
            return "Логин неправильный!";
        }
        if (user.getPassword().trim().isBlank()) {
            return "Пароль пустой!";
        }
        if (user.getPassword().trim().length() < 8) {
            return "Пароль короткий!";
        }

        storage.add(User.builder()
                .login(user.getLogin())
                .password(crypto.doCrypt(user.getPassword()))
                .fullName(user.getFullName())
                .birthDate(user.getBirthDate())
                .regDate(LocalDateTime.now())
                .role_id(ROLE_ID)
                .build());
        return "";
    }

    @Override
    public String authenticate(String login, String password) {
        ICryptoService crypto = new CryptoService();

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
