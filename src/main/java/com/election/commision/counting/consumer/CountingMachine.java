package com.election.commision.counting.consumer;

import com.election.commision.counting.model.Vote;
import com.election.commision.counting.model.Votes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountingMachine implements Runnable {
    volatile private BlockingQueue<String> queue;

    private ObjectMapper mapper;

    volatile private Map<String,Integer> map;

    public CountingMachine(BlockingQueue<String> queue, ObjectMapper mapper, Map<String,Integer> map){
        this.queue = queue;
        this.mapper = mapper;
        this.map = map;
    }

    @Override
    public void run() {
        try
        {
            while (true)
            {
                String votesJson = queue.take();
                System.out.println("Consumed resource: " + votesJson);
                System.out.println("Queue size now: "  + queue.size());

                Votes v1 = convertJsonToObj(votesJson);
                List<Vote> votes = v1.getVotes();

                for (Vote v: votes) {
                    String partyName = v.getPartyName();
                    int voteCount = v.getVoteCount();
                    synchronized (map) {
                        map.put(partyName, map.containsKey(partyName) ? map.get(partyName) + voteCount : voteCount);
                    }
                }


            }
        }
        catch (InterruptedException | JsonProcessingException ex)
        {
            ex.printStackTrace();
        }
    }

    private Votes convertJsonToObj(String votesJson) throws JsonProcessingException {
        return mapper.readValue(votesJson,Votes.class);
    }
}