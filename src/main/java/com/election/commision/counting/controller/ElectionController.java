package com.election.commision.counting.controller;

import com.election.commision.counting.service.CountCheckAtRegularIntervalService;
import com.election.commision.counting.service.ServerSideCountingService;
import com.election.commision.counting.service.VotingBoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ElectionController {
    @Autowired
    public VotingBoothService votingBoothService;

    @Autowired
    private ServerSideCountingService countingService;

    @Autowired
    private CountCheckAtRegularIntervalService countCheckAtRegularIntervalService;

    //Pass the number of voting booths to be created in path variable
    @GetMapping("/producer/{count}")
    public void producer(@PathVariable("count") int count){
        votingBoothService.createVotingBooths(count);
    }

    //Pass the number of counting machines to be created in path variable
    @GetMapping("/consumer/{count}")
    public void consumer(@PathVariable("count") int count){
        countingService.createCountingMachines(count);
    }

    @Scheduled(fixedRate = 5000L)
    public void countCheckAtRegularInterval() throws IOException {
        countCheckAtRegularIntervalService.countCheckAtRegularInterval();
    }
}
