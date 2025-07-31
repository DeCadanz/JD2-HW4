package by.it_academy.jd2.Mk_JD2_111_25.HW4.controller;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.ContextFactory;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto.Message;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto.User;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.api.IMessageService;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.api.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;


@WebServlet(urlPatterns = {"/api/message", "/ui/user/chats"})
public class MessageServlet extends HttpServlet {

    private final IUserService uService = ContextFactory.getBean(IUserService.class);
    private final IMessageService mService = ContextFactory.getBean(IMessageService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String sender = user.getLogin();

        String recipient = req.getParameter("recipient");
        String text = req.getParameter("text");

        LocalDateTime sendingTime = now();

        if (uService.check(recipient) == "") {
            Message message = new Message();
            message.setSender(sender);
            message.setRecipient(recipient);
            message.setText(text);
            message.setSendingTime(sendingTime);

            mService.send(message);
            req.setAttribute("error", "Успешно отправлено!");
            req.getRequestDispatcher("/WEB-INF/ui/sendmessage.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", uService.check(recipient));
            req.getRequestDispatcher("/WEB-INF/ui/sendmessage.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Message> mList = null;

        mList = mService.getAll(user);

        req.setAttribute("user", user.getLogin());
        req.setAttribute("mList", mList);
        req.getRequestDispatcher("/WEB-INF/ui/messages.jsp").forward(req, resp);
    }
}
