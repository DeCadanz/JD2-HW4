package by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto.Message;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto.User;
import java.util.List;

public interface IMessageStorage {
    void add(Message message);
    List<Message> getIncome(User user);
    int getCount();
}

