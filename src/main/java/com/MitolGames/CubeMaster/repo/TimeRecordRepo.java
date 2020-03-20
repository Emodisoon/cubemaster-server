package com.MitolGames.CubeMaster.repo;

import com.MitolGames.CubeMaster.domain.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeRecordRepo extends JpaRepository<TimeRecord,Long> {
    List<TimeRecord> findAllByUserId(long id);
}

