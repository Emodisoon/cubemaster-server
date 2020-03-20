package com.MitolGames.CubeMaster.controller;

import com.MitolGames.CubeMaster.domain.ApplictaionUser;
import com.MitolGames.CubeMaster.domain.dto.CreateUserModel;
import com.MitolGames.CubeMaster.repo.UserRepo;
import com.MitolGames.CubeMaster.service.MainService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("main")
class MainController {

    private final MainService mainService;
    private final UserRepo userRepo;
    public MainController(MainService mainService, UserRepo userRepo) {
        this.mainService = mainService;
        this.userRepo = userRepo;
    }


    @PostMapping("registration")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody CreateUserModel userModel) {
        ApplictaionUser usr = userRepo.findByUserName(userModel.getUserName());
        if(usr==null) {
            mainService.create(userModel);
            return "success";
        }
        else
            return "failure";
    }
}