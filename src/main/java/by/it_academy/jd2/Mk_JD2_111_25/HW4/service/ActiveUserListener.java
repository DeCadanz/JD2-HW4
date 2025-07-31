package by.it_academy.jd2.Mk_JD2_111_25.HW4.service;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class ActiveUserListener implements HttpSessionListener {

    private static final AtomicInteger activeSessions = new AtomicInteger();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        if (se.getSession().getAttribute("user") != null) {
            activeSessions.incrementAndGet();
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (se.getSession().getAttribute("user") != null) {
            activeSessions.decrementAndGet();
        }
    }

    public static void userLoggedIn(HttpSession session) {
        if (session.getAttribute("user") != null) {
            activeSessions.incrementAndGet();
        }
    }

    public static void userLoggedOut(HttpSession session) {
        if (session.getAttribute("user") != null) {
            activeSessions.decrementAndGet();
        }
    }

    public static int getActiveSessions() {
        return activeSessions.get();
    }
}
