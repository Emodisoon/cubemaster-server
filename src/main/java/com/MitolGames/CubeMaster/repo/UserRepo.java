package com.MitolGames.CubeMaster.repo;

import com.MitolGames.CubeMaster.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByusername(String username);
}
