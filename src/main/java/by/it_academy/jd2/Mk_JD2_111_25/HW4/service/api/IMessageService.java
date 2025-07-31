package by.it_academy.jd2.Mk_JD2_111_25.HW4.service.api;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto.Message;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto.User;

import java.util.List;

public interface IMessageService {
    void send(Message message);
    List<Message> getAll(User user);
    int getCount();
}
