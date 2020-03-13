package com.MitolGames.CubeMaster.service;

import com.MitolGames.CubeMaster.domain.ApplictaionUser;
import com.MitolGames.CubeMaster.domain.dto.CreateUserModel;
import com.MitolGames.CubeMaster.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class MainService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public MainService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ApplictaionUser create(CreateUserModel userModel) {

        ApplictaionUser applicationUser = new ApplictaionUser();

        applicationUser.setUserName(userModel.getUserName());
        applicationUser.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));

        return userRepo.save(applicationUser);
    }
}