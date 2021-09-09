package ru.job4j.Store;

import ru.job4j.model.Item;
import ru.job4j.model.User;

import java.util.List;

public interface Store {
    void add(Item item);
    void update(int id);
    List<Item> getAll(User user);
    void add(User user);
    User findUserByLogin(String login);
}
