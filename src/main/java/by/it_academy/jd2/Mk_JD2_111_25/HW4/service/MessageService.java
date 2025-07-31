package by.it_academy.jd2.Mk_JD2_111_25.HW4.service;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto.Message;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto.User;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.api.IMessageService;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api.IMessageStorage;

import java.util.List;

public class MessageService implements IMessageService {

    private final IMessageStorage storage;

    public MessageService(IMessageStorage storage) {
        this.storage = storage;
    }

    @Override
    public void send(Message message) {
        storage.add(message);
    }

    @Override
    public List<Message> getAll(User user) {
        return storage.getIncome(user);
    }

    @Override
    public int getCount() {
        return storage.getCount();
    }
}
