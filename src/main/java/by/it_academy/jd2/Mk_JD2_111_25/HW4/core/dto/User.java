package by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    private final String login;
    private final String password;
    private final String fullName;
    private final LocalDate birthDate;
    private final LocalDateTime regDate;
    private final int role_id;

    private User(String login, String password, String fullName, LocalDate birthDate, LocalDateTime regDate, int role_id) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.regDate = regDate;
        this.role_id = role_id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public int getRole() {
        return role_id;
    }

    public static class Builder {
        private String login;
        private String password;
        private String fullName;
        private LocalDate birthDate;
        private LocalDateTime regDate;
        private int role_id;

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder regDate(LocalDateTime regDate) {
            this.regDate = regDate;
            return this;
        }

        public Builder role_id(int role_id) {
            this.role_id = role_id;
            return this;
        }
        public User build() {
            return new User(login, password, fullName, birthDate, regDate, role_id);
        }
    }

}
