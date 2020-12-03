package com.election.commision.counting.service;

import com.election.commision.counting.model.Votes;
import com.election.commision.counting.producer.VotingBooth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class VotingBoothService {
    @Autowired
    BlockingQueue<String> queue;

    public void createVotingBooths(int count){
        //Create N producers
        for (int i = 0; i < count; i++) {
            Thread t = new Thread(new VotingBooth(queue));
            t.start();
        }
    }
}
