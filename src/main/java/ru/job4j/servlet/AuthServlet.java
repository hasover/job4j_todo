package ru.job4j.servlet;

import ru.job4j.Store.HbmStore;
import ru.job4j.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/auth.do")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = HbmStore.instOf().findUserByLogin(login);
        if (user != null && password.equals(user.getPassword())) {
            req.getSession().setAttribute("user", user);
        } else {
            try (OutputStream out = resp.getOutputStream()) {
                resp.setStatus(403);
                out.write(("Неверное имя пользователя или пароль").getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
