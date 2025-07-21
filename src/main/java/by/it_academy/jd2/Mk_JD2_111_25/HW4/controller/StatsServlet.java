package by.it_academy.jd2.Mk_JD2_111_25.HW4.controller;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.ActiveUserListener;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.MessageStorageDB;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.UserStorageDB;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api.IMessageStorage;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api.IUserStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/api/admin/statistics")
public class StatsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IMessageStorage mStorage = new MessageStorageDB();
        IUserStorage uStorage = new UserStorageDB();

        req.setAttribute("sessions", ActiveUserListener.getActiveSessions());
        try {
            req.setAttribute("messages", mStorage.getMessagesCount());
            req.setAttribute("users", uStorage.getUsersCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/WEB-INF/ui/statistics.jsp").forward(req, resp);
    }
}
