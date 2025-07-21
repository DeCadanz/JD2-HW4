package by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.dto.Message;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.dto.User;

import java.sql.SQLException;
import java.util.List;

public interface IMessageStorage {
    void add(Message message) throws ClassNotFoundException, SQLException;
    List<Message> getIncome(User user) throws Exception;
    int getMessagesCount() throws SQLException;
}

