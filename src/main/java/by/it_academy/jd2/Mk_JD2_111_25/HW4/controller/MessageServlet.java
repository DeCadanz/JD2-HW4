package by.it_academy.jd2.Mk_JD2_111_25.HW4.controller;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.dto.Message;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.dto.User;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.CryptoService;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.MessageStorageDB;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.UserStorageDB;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api.IMessageStorage;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api.IUserStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;


@WebServlet(urlPatterns = {"/api/message", "/ui/user/chats"})
public class MessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IMessageStorage mstorage = new MessageStorageDB();

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String sender = user.getLogin();

        String recipient = req.getParameter("recipient");
        String text = req.getParameter("text");

        LocalDateTime sendingTime = now();

        IUserStorage storage = new UserStorageDB();

        try {
            if (storage.get(recipient) == null) {
                req.setAttribute("error", "Пользователь не найден!");
                req.getRequestDispatcher("/template/sendmessage.jsp").forward(req, resp);
            } else {
                Message message = new Message();
                message.setSender(sender);
                message.setRecipient(recipient);
                message.setText(text);
                message.setSendingTime(sendingTime);

                try {
                    mstorage.add(message);
                    req.setAttribute("error", "Успешно отправлено!");
                    req.getRequestDispatcher("/template/sendmessage.jsp").forward(req, resp);
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IMessageStorage mstorage = new MessageStorageDB();

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Message> mList = null;
        try {
            mList = mstorage.getIncome(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("user", user.getLogin());
        req.setAttribute("mList", mList);
        req.getRequestDispatcher("/template/messages.jsp").forward(req, resp);
    }
}
