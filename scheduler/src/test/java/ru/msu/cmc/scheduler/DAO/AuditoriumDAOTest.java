package ru.msu.cmc.scheduler.DAO;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.scheduler.models.Auditorium;

import java.util.ArrayList;
import java.util.List;

//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class AuditoriumDAOTest {

    @Autowired
    private AuditoriumDAO auditoriumDAO;

    //    common
    @Test
//    @Transactional
    void testUpdateAuditorium() {
        Auditorium auditorium = auditoriumDAO.getById(5);
        auditorium.setCapacity(120);
        auditoriumDAO.update(auditorium);
        Auditorium updatedAuditorium = auditoriumDAO.getById(5);
        assertEquals(120, updatedAuditorium.getCapacity());
    }


    @Test
    @Transactional
    void testSimpleManipulations() {
        List<Auditorium> auditoriumList = new ArrayList<>();
        auditoriumList.add(new Auditorium(19, "семинарская", 20));
        auditoriumList.add(new Auditorium(20, "лекционная", 100));
        auditoriumDAO.saveCollection(auditoriumList);
        List<Auditorium> auditoriumListAll = (List<Auditorium>) auditoriumDAO.getAll();
        assertEquals(20, auditoriumListAll.size());

        Auditorium auditoriumNotExist = auditoriumDAO.getById(100);
        assertNull(auditoriumNotExist);

        Auditorium algo = auditoriumDAO.getById(4);
        assertEquals(149, algo.getCapacity());
    }
}