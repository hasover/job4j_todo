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

@WebServlet(value = "/reg.do")
public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = new User(name, login, password);
            HbmStore.instOf().add(user);
            req.getSession().setAttribute("user", user);
        } catch (Exception ex) {
            try (OutputStream out = resp.getOutputStream()) {
                resp.setStatus(403);
                out.write("Пользователь существует".getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
