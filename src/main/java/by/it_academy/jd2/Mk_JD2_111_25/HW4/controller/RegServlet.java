package by.it_academy.jd2.Mk_JD2_111_25.HW4.controller;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.ContextFactory;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto.User;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.CryptoService;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.api.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet("/api/user")
public class RegServlet extends HttpServlet {

    private final IUserService service = ContextFactory.getBean(IUserService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        CryptoService crypto = new CryptoService();

        String error = null;
        try {
            error = service.add(User.builder()
                    .login(req.getParameter("login"))
                    .password(crypto.doCrypt(req.getParameter("password")))
                    .fullName(req.getParameter("fullname"))
                    .birthDate(LocalDate.parse(req.getParameter("birth")))
                    .regDate(LocalDateTime.now())
                    .role_id(3)
                    .build());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if (error != "") {
            req.setAttribute("error", error);
            req.getRequestDispatcher("/WEB-INF/ui/signup.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath().concat("/ui/signIn"));
        }

    }
}
