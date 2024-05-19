package ru.msu.cmc.scheduler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.scheduler.DAO.AuditoriumDAO;
import ru.msu.cmc.scheduler.DAO.impl.AuditoriumDAOImpl;
import ru.msu.cmc.scheduler.models.Auditorium;
import ru.msu.cmc.scheduler.models.Auditorium;

import java.util.List;

@Controller
public class AuditoriumController {

    @Autowired
    private final AuditoriumDAO auditoriumDAO = new AuditoriumDAOImpl();

    @GetMapping("/auditorium")
    public String auditoriumPage(@RequestParam(name = "auditoriumId") Integer auditoriumId, Model model) {
        Auditorium auditorium = auditoriumDAO.getById(auditoriumId);

        if (auditorium == null) {
            model.addAttribute("error_msg", "В базе нет аудитории с ID = " + auditoriumId);
            return "error";
        }

        model.addAttribute("auditorium", auditorium);
        model.addAttribute("auditoriumService", auditoriumDAO);
        return "auditorium";
    }
    
    @GetMapping("/auditoriums")
    public String auditoriumlistPage(Model model) {
        List<Auditorium> auditoriums = (List<Auditorium>) auditoriumDAO.getAll();
        model.addAttribute("auditoriums", auditoriums);
        model.addAttribute("auditoriumService", auditoriumDAO);
        return "auditoriums";
    }

    @GetMapping("/editAuditorium")
    public String editAuditoriumPage(@RequestParam(name = "auditoriumId", required = false) Integer auditoriumId, Model model) {
        if (auditoriumId == null) {
            model.addAttribute("auditorium", new Auditorium());
            model.addAttribute("auditoriumService", auditoriumDAO);
            return "editAuditorium";
        }

        Auditorium auditorium = auditoriumDAO.getById(auditoriumId);

        if (auditorium == null) {
            model.addAttribute("error_msg", "В базе нет аудитории с ID = " + auditoriumId);
            return "error";
        }

        model.addAttribute("auditorium", auditorium);
        model.addAttribute("auditoriumService", auditoriumDAO);
        return "editAuditorium";
    }

    @PostMapping("/saveAuditorium")
    public String saveAuditoriumPage(@RequestParam(name = "auditoriumId") Integer auditoriumId,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "capacity") Integer capacity,
                                     Model model) {
        Auditorium auditorium = auditoriumDAO.getById(auditoriumId);
        boolean changeIsSuccessful = false;

        if (auditorium != null) {
            auditorium.setName(name);
            auditorium.setCapacity(capacity);
        } else {
            auditorium = new Auditorium(auditoriumId, name, capacity);
        }

        model.addAttribute("error_msg", "Данные не сохранены");
        return "error";
    }

//    @PostMapping("/removeAuditorium")
//    public String removeAuditoriumPage(@RequestParam(name = "auditoriumId") Long auditoriumId) {
//        auditoriumDAO.deleteById(auditoriumId);
//        return "redirect:/auditoriums";
//      }
}