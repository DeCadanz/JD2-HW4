package by.it_academy.jd2.Mk_JD2_111_25.HW4.controller;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.ContextFactory;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.ActiveUserListener;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.api.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {

    private final IUserService service = ContextFactory.getBean(IUserService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String error = service.authenticate(login, password);
        if (error != "") {
            req.setAttribute("error", error);
            req.getRequestDispatcher("/WEB-INF/ui/signin.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", service.getUser(login));
            ActiveUserListener.userLoggedIn(session);
            resp.sendRedirect(req.getContextPath().concat("/ui/user/chats"));
        }

    }
}

