package edu.uoc.epcsd.user.controllers;

import edu.uoc.epcsd.user.entities.User;
import edu.uoc.epcsd.user.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        log.trace("getAllUsers");

        return userRepository.findAll();
    }

    // add the code for the missing system operations here

    /*
    ● alta de usuario
    ● alta de alerta
    ● consulta de las alertas de un usuario para un intervalo concreto de fechas
    ● consulta de las alertas por producto y fecha
    ● consulta de usuarios a alertar para un producto y fecha (operación getUsersToAlert)
    ● consulta del detalle de una alerta
     */
}
