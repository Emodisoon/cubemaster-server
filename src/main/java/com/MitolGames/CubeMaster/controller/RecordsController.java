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

        String message = "Sosi";
        model.addAttribute("message", message);
        model.addAttribute("records", msgs);

        return "records";
    }

    /*

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list(){
        return messageRepo.findAll();
    }
    @GetMapping("{id}")
    public Message getOne(@PathVariable("id") Message message){
        return message;
    }

    @PostMapping
    @JsonView({Views.IdName.class})
    public Message create(@RequestBody Message message){
        message.setCreationDate(LocalDateTime.now());
        System.out.println(message.toString());
        return messageRepo.save(message);

    }



    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDb, @RequestBody Message message){

        System.out.println("Got put request");
        BeanUtils.copyProperties(message, messageFromDb, "id");
        return messageRepo.save(messageFromDb);
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message){
        messageRepo.delete(message);
    }


     */
}
