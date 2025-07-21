package by.it_academy.jd2.Mk_JD2_111_25.HW4.storage;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.dto.Message;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.dto.User;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api.IMessageStorage;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class MessageStorageDB implements IMessageStorage {
    public static DataSource dataSource;

    static {
        ComboPooledDataSource cpds = new ComboPooledDataSource();

        try {
            cpds.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/messenger");
        cpds.setUser("postgres");
        cpds.setPassword("postgres");

        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);

        dataSource = cpds;
    }

    @Override
    public void add(Message message) throws SQLException {
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
    }

    @Override
    public List<Message> getIncome(User user) throws Exception {

        List<Message> mList = new ArrayList();
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
            Message message = new Message();
            message.setSendingTime(resultSet.getObject("msg_date", LocalDateTime.class));
            message.setText(resultSet.getString("msg_text"));
            message.setSender(resultSet.getString("sender_id"));
            message.setRecipient(resultSet.getString("recipient_id"));
            mList.add(message);
        }
        return mList;
    }

    @Override
    public int getMessagesCount() throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement statement = conn.prepareStatement("""
                SELECT COUNT(*)
                FROM messenger.messages
                """);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }
}


