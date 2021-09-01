package ru.job4j.Store;

import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Item;
import java.util.List;
import java.util.function.Function;

public class HbmStore implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private final Logger log = LoggerFactory.getLogger(HbmStore.class);

    private HbmStore() {
    }

    private<T> T  tx(Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            transaction.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            log.error("Exception in store:", e);
            throw e;
        } finally {
            session.close();
        }
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
        this.tx(session -> session.save(item));
    }

    @Override
    public void update(int id) {
        this.tx(session -> {
            Item item = session.get(Item.class, id);
            item.setDone(true);
            return null;
        });
    }

    @Override
    public List<Item> getAll() {
        return this.tx(session -> session.createQuery("from Item order by done").list());
    }
}
