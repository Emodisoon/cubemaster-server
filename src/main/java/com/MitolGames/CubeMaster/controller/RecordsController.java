package com.MitolGames.CubeMaster.controller;


import com.MitolGames.CubeMaster.domain.Message;
import com.MitolGames.CubeMaster.domain.Views;
import com.MitolGames.CubeMaster.repo.MessageRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class RecordsController {

    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    private final MessageRepo messageRepo;
    @Autowired
    public RecordsController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/records")
    public String records(Model model){
        List<Message> msgs = messageRepo.findAll();

        model.addAttribute("records", msgs);

        return "records";
    }



}
