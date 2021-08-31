package ru.job4j.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.Store.HbmStore;
import ru.job4j.Store.Store;
import ru.job4j.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(value = "/item.do")
public class IndexServlet extends HttpServlet {
    private final static Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> items = HbmStore.instOf().getAll();
        String itemsJson = GSON.toJson(items);
        resp.setContentType("application/json; charset=utf-8");
        try(PrintWriter writer = resp.getWriter()) {
            writer.write(itemsJson);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description");
       Store store = HbmStore.instOf();
       store.add(new Item(0, description, new Timestamp(System.currentTimeMillis()), false));
       resp.setContentType("text/plain; charset=utf-8");
       try(OutputStream out = resp.getOutputStream()) {
           out.write("Описание добавлено.".getBytes(StandardCharsets.UTF_8));
       }
    }
}
