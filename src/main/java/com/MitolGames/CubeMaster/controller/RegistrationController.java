package com.MitolGames.CubeMaster.controller;

import com.MitolGames.CubeMaster.domain.ApplictaionUser;
import com.MitolGames.CubeMaster.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("new_user", new ApplictaionUser());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(ApplictaionUser applictaionUser, Map<String, Object> model){
        ApplictaionUser applictaionUserFromDb =  userRepo.findByUserName(applictaionUser.getUserName());
        if(applictaionUserFromDb !=null){
            model.put("message", "User exists");
            return "registration";
        }

        userRepo.save(applictaionUser);

        return "redirect:/login";
    }
}
