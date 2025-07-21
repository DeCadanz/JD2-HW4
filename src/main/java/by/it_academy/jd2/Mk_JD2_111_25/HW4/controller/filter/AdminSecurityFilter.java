package by.it_academy.jd2.Mk_JD2_111_25.HW4.controller.filter;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.dto.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/ui/admin/*", "/api/admin/*"})
public class AdminSecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();
        if ((session != null) && (session.getAttribute("user") != null)) {
            User user = (User) session.getAttribute("user");
            if (user.getRole() == 2) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(contextPath + "/ui/signIn");
            }
        } else {
            res.sendRedirect(contextPath + "/ui/signIn");
        }
    }
}
