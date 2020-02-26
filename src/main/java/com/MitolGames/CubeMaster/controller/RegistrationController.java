package com.MitolGames.CubeMaster.controller;

import com.MitolGames.CubeMaster.domain.Role;
import com.MitolGames.CubeMaster.domain.User;
import com.MitolGames.CubeMaster.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(){
        return registration();
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb =  userRepo.findByUserName(user.getUserName());
        //TODO: в таймлифе найти условия и выводить ошибку не уникальности пользователя
        if(userFromDb!=null){
            model.put("message", "User exists");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
