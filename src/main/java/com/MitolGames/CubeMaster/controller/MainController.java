package com.MitolGames.CubeMaster.controller;

import com.MitolGames.CubeMaster.domain.ApplictaionUser;
import com.MitolGames.CubeMaster.domain.dto.CreateUserModel;
import com.MitolGames.CubeMaster.service.MainService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("main")
class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("greeting")
    public String getGreeting() {

        return mainService.getGreeting();
    }

    @PostMapping("registration")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplictaionUser create(@RequestBody CreateUserModel userModel) {

        return mainService.create(userModel);
    }
}