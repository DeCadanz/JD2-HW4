package by.it_academy.jd2.Mk_JD2_111_25.HW4.controller;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.dto.User;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.CryptoService;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.UserService;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.api.IUserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet("/api/user")
public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        CryptoService crypto = new CryptoService();

        IUserService service = new UserService();
        try {
            String error = service.add(User.builder()
                    .login(req.getParameter("login"))
                    .password(crypto.doCrypt(req.getParameter("password")))
                    .fullName(req.getParameter("fullname"))
                    .birthDate(LocalDate.parse(req.getParameter("birth")))
                    .regDate(LocalDateTime.now())
                    .role_id(3)
                    .build());

            if(error != "") {
                req.setAttribute("error", error);
                req.getRequestDispatcher("/template/signup.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath().concat("/ui/signIn"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
