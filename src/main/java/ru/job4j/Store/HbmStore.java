package ru.job4j.Store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Item;
import java.util.List;

public class HbmStore implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private final Logger log = LoggerFactory.getLogger(HbmStore.class);

    private HbmStore() {
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    private static final class Lazy {
        private static final Store INST = new HbmStore();
    }

    @Override
    public void add(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.error("Exception in SqlStore:", ex);
        }
    }

    @Override
    public void setDone(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Item item = session.get(Item.class, id);
            item.setDone(true);
            session.update(item);
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.error("Exception in SqlStore:", ex);
        }
    }

    @Override
    public List<Item> getAll() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            List<Item> items = session.createQuery("from Item order by done").list();
            session.getTransaction().commit();
            return items;
        } catch (Exception ex) {
            log.error("Exception in SqlStore:", ex);
        }
        return null;
    }
}
