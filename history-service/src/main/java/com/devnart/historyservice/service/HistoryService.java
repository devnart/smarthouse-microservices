package com.devnart.historyservice.service;

import com.devnart.historyservice.entity.History;
import com.devnart.historyservice.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    public List<History> getHistory(){
        return historyRepository.findAll();
    }

    public void saveHistory(History history) {
        historyRepository.save(history);
    }
}
