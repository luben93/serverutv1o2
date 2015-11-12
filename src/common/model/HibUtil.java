package common.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
/**
 * Created by sirena on 2015-11-03.
 */
public class HibUtil {

    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration config = getConfiguration();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(
                    config.getProperties()).buildServiceRegistry();
            config.setSessionFactoryObserver(new SessionFactoryObserver() {
                private static final long  serialVersionUID = 1L;

                @Override
                public void sessionFactoryCreated(SessionFactory factory) {
                }

                @Override
                public void sessionFactoryClosed(SessionFactory factory) {
                    ServiceRegistryBuilder.destroy(serviceRegistry);
                }
            });
            ourSessionFactory = config.buildSessionFactory(serviceRegistry);

           /* Configuration config = getConfiguration();
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);*/
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static  Session openSession() {
        return ourSessionFactory.openSession();
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    private static  Configuration getConfiguration() {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(User.class );
        cfg.addAnnotatedClass(Profile.class );
        cfg.addAnnotatedClass(WallPost.class );
        cfg.addAnnotatedClass(ChatMessage.class );

        cfg.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
        cfg.setProperty("hibernate.connection.url","jdbc:mysql://us-cdbr-iron-east-03.cleardb.net/heroku_1b33dad8f5f8d1c");
        cfg.setProperty("hibernate.show_sql", "true");
        cfg.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        cfg.setProperty("hibernate.hbm2ddl.auto", "update");
        cfg.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");
        cfg.setProperty("hibernate.current_session_context_class", "thread");
        return cfg;
    }

    public static SessionFactory getSessionFactory() {
        return ourSessionFactory;
    }
}
