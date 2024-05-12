package ru.msu.cmc.scheduler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.msu.cmc.scheduler.DAO.StudentDAO;

/**
 * Created by ytati
 * on 11.05.2024.
 */
@RestController
public class TestController {
    @Autowired
    StudentDAO studentDAO;

    @GetMapping("/test")
    @Transactional
    public String testEndpoint(){
        return studentDAO.getById(1).getName();
    }
}
