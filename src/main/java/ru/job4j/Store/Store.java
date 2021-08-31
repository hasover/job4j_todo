package ru.job4j.Store;

import ru.job4j.model.Item;

import java.util.List;

public interface Store {
    void add(Item item);
    void setDone(int id);
    List<Item> getAll();
}
