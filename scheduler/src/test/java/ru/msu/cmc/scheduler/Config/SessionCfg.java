package ru.msu.cmc.scheduler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
class HibernateDatabaseConfigTest {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Test
    public void test() {
        SessionFactory sessionFactoryObject = sessionFactory.getObject();
        assertNotNull(sessionFactoryObject);
        Session session = sessionFactoryObject.openSession();
        assertNotNull(session);
    }
}
package ru.msu.cmc.scheduler;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.msu.video_hosting.model.*;

        import java.io.IOException;

@Configuration
public class SessionWire {
    @Bean(name="entityManagerFactory")
    public SessionFactory sessionFactory() throws IOException {

        SessionFactory sessionFactory;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClasses(Client.class,
                            Auditorium.class,
                            Course.class,
                            Schedule.class,
                            Student_courses.class,
                            Student.class,
                            Teacher.class,
                            Teacher_courses.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
        catch (Exception e) {
            throw e;
        }
        return sessionFactory;
    }
}