package com.MitolGames.CubeMaster.repo;

import com.MitolGames.CubeMaster.domain.ApplictaionUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<ApplictaionUser, Long> {
    ApplictaionUser findByUserName(String username);
}
