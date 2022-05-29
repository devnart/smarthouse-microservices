package com.devnart.historyservice.repository;

import com.devnart.historyservice.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History,Long> {

}
