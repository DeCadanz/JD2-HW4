package by.it_academy.jd2.Mk_JD2_111_25.HW4.storage;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto.Message;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto.User;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api.IMessageStorage;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api.exceptions.StorageException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class MessageStorageDB implements IMessageStorage {
    private final DataSource dataSource;

    public MessageStorageDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Message message) {
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("""
                                         INSERT INTO messenger.messages(
                    	msg_text, msg_date, sender_id, recipient_id)
                    	VALUES (?, NOW(),
                    		(SELECT user_id FROM messenger.users WHERE login = ?),
                    		(SELECT user_id FROM messenger.users WHERE login = ?));
                    """);

            statement.setString(1, message.getText());
            statement.setString(2, message.getSender());
            statement.setString(3, message.getRecipient());

            statement.executeUpdate();
        } catch (Exception e) {
            throw new StorageException("Ошибка добавления сообщения в базу", e);
        }
    }

    @Override
    public List<Message> getIncome(User user) {

        List<Message> mList = new ArrayList();
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("""
                           SELECT msg_text, msg_date,
                            (SELECT login FROM messenger.users WHERE user_id = sender_id) as sender_id,
                            (SELECT login FROM messenger.users WHERE user_id = recipient_id) as recipient_id
                    FROM messenger.messages
                    WHERE recipient_id = (SELECT user_id FROM messenger.users WHERE login = ?)
                    """);

            statement.setString(1, user.getLogin());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                mList.add(Message.builder()
                        .sendingTime(resultSet.getObject("msg_date", LocalDateTime.class))
                        .sender(resultSet.getString("sender_id"))
                        .recipient(resultSet.getString("recipient_id"))
                        .text(resultSet.getString("msg_text"))
                        .build());
            }
            return mList;
        } catch (Exception e) {
            throw new StorageException("Ошибка чтения входящих сообщений", e);
        }
    }

    @Override
    public int getCount() {
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("""
                    SELECT COUNT(*)
                    FROM messenger.messages
                    """);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (Exception e) {
            throw new StorageException("Ошибка подсчета количества сообщений", e);
        }
    }
}



