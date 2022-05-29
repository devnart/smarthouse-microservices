package com.devnart.historyservice.controller;

import com.devnart.historyservice.entity.History;
import com.devnart.historyservice.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping
    public List<History> getAllHistory(){
        return historyService.getHistory();
    }

    @PostMapping
    public void createHistory(@RequestBody History history){
        System.out.println("history: " + history);
        historyService.saveHistory(history);
    }
}
