package com.MitolGames.CubeMaster.controller;


import com.MitolGames.CubeMaster.domain.ApplictaionUser;
import com.MitolGames.CubeMaster.domain.TimeRecord;
import com.MitolGames.CubeMaster.domain.Views;
import com.MitolGames.CubeMaster.repo.TimeRecordRepo;
import com.MitolGames.CubeMaster.repo.UserRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ApiController {
    private final TimeRecordRepo timeRecordRepo;
    private final UserRepo userRepo;
    @Autowired
    public  ApiController(TimeRecordRepo timeRecordRepo, UserRepo userRepo){
        this.timeRecordRepo = timeRecordRepo;
        this.userRepo = userRepo;
    }


    @GetMapping("/timeRecord")
    public List<TimeRecord> list(){
        return timeRecordRepo.findAll();
    }

    @PostMapping("/timeRecord")
    public TimeRecord create(@RequestBody TimeRecord timeRecord){
        timeRecord.setCreationDate(LocalDateTime.now());

        Optional<ApplictaionUser> usr = userRepo.findById((timeRecord.getUserID()));

        ApplictaionUser newusr = usr.get();
        timeRecord.setUser(newusr);
        return timeRecordRepo.save(timeRecord);
    }

    @GetMapping("/user/{username}")
    @JsonView({Views.PublicUserInfo.class})
    public ApplictaionUser getUser(@PathVariable("username") String uname){
        return userRepo.findByUserName(uname);
    }

    @GetMapping("/GetPersonalRecords/{name}")
    public List<TimeRecord> list(@PathVariable("name") String name){
        ApplictaionUser temp = userRepo.findByUserName(name);
        return timeRecordRepo.findAllByUserId(temp.getId());
    }

    @DeleteMapping("/timeRecord/{id}")
    public void DeleteTR(@PathVariable("id") String id){
        timeRecordRepo.delete(timeRecordRepo.findById(Long.parseLong(id)).get());
    }
}
