package by.it_academy.jd2.Mk_JD2_111_25.HW4.controller;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.core.ContextFactory;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.ActiveUserListener;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.api.IMessageService;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.api.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebServlet("/api/admin/statistics")
@WebServlet(urlPatterns = {"/api/admin/statistics", "/ui/admin/statistics"})
public class StatsServlet extends HttpServlet {
    private final IUserService uService = ContextFactory.getBean(IUserService.class);
    private final IMessageService mService = ContextFactory.getBean(IMessageService.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("sessions", ActiveUserListener.getActiveSessions());
        req.setAttribute("messages", mService.getCount());
        req.setAttribute("users", uService.getCount());

        req.getRequestDispatcher("/WEB-INF/ui/statistics.jsp").forward(req, resp);
    }
}
