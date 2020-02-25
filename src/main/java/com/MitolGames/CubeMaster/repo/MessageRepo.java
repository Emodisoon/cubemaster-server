package com.MitolGames.CubeMaster.repo;

import com.MitolGames.CubeMaster.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message,Long> {
}
