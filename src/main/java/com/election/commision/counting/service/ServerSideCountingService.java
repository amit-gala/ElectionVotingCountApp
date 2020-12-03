package com.election.commision.counting.service;

import com.election.commision.counting.consumer.CountingMachine;
import com.election.commision.counting.producer.VotingBooth;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

@Service
public class ServerSideCountingService {
    @Autowired
    BlockingQueue<String> queue;

    @Autowired
    @Qualifier("mapper")
    private ObjectMapper mapper;

    @Autowired
    @Qualifier("map")
    private Map<String,Integer> map;

    public void createCountingMachines(int count){
        //Create N counting machines
        for (int i = 0; i < count; i++) {
            Thread t = new Thread(new CountingMachine(queue,mapper,map));
            t.start();
        }
    }
}
