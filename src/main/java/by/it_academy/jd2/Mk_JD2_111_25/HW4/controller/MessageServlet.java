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

@WebServlet(urlPatterns = {"/api/message", "/ui/user/chats"})
public class MessageServlet extends HttpServlet {

    private final IUserService uService = ContextFactory.getBean(IUserService.class);
    private final IMessageService mService = ContextFactory.getBean(IMessageService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String recipient = req.getParameter("recipient");

        if (uService.check(recipient) == "") {

            mService.send(Message.builder()
                    .sendingTime(LocalDateTime.now())
                    .sender(user.getLogin())
                    .recipient(req.getParameter("recipient"))
                    .text(req.getParameter("text"))
                    .build());

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

        req.setAttribute("user", user.getLogin());
        req.setAttribute("mList", mService.getAll(user));
        req.getRequestDispatcher("/WEB-INF/ui/messages.jsp").forward(req, resp);
    }
}
