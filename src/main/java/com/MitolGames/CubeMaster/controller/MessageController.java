package com.MitolGames.CubeMaster.controller;


import com.MitolGames.CubeMaster.domain.Message;
import com.MitolGames.CubeMaster.domain.Views;
import com.MitolGames.CubeMaster.repo.MessageRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {
    private int counter = 4;

    /*
    private List<Map<String, String>> messages = new ArrayList<Map<String,String>>(){{
        add(new HashMap<String,String>() {{ put("id", "1"); put("text","Govno"); }});
        add(new HashMap<String,String>() {{ put("id", "2"); put("text","Gov31no"); }});
        add(new HashMap<String,String>() {{ put("id", "3"); put("text","Go23vno"); }});
        add(new HashMap<String,String>() {{ put("id", "4"); put("text","G1ovno"); }});
    }};
    */


    private final MessageRepo messageRepo;
    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }


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
}
