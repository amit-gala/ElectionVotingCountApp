package com.election.commision.counting.producer;

import com.election.commision.counting.model.Vote;
import com.election.commision.counting.model.Votes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VotingBooth implements Runnable {
    volatile private BlockingQueue<String> queue;

    public VotingBooth(BlockingQueue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            File votesFile;
            votesFile = new ClassPathResource("testData/votes.txt").getFile();

            //Function to extract the 3rd part of the string which is vote to a party
            Function<String,String> extractVoteTo = (line) -> {
                String[] splittedVoteLine = line.split(";");
                return splittedVoteLine[2];
            };

            try (Stream<String> votesFileStream = Files.lines(Paths.get(votesFile.getAbsolutePath()))) {
                //Split the file by ';' and extract the vote count in a list
                List<String> votes = votesFileStream.parallel().map(extractVoteTo).collect(Collectors.toList());

                //Aggregate the votes by party and store into a Map
                Map<String,Integer> voteCountMap = votes.stream().parallel().collect(Collectors.toMap(String::toLowerCase, count -> 1, Integer::sum));

                System.out.println(voteCountMap);

                //Convert the Map object having party wise aggregation into the Vote onject
                Votes v1 = createVotesObject(voteCountMap);

                //Serialize the object as JSON before adding to queue
                ObjectMapper obj = new ObjectMapper();
                String votesJson = obj.writeValueAsString(v1);

                //Adding a delay of 1 sec for mocking
                System.out.println("Sleeping for 2 sec");
                Thread.sleep(2000);

                System.out.println("Adding JSON to queue: "+votesJson);
                queue.add(votesJson);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Votes createVotesObject(Map<String, Integer> voteCountMap) {
        Votes v1 = new Votes();
        List<Vote> voteList = new LinkedList<>();
        v1.setVotes(voteList);

        for (Map.Entry<String, Integer> voteEntry : voteCountMap.entrySet()) {
            String partyName = voteEntry.getKey();
            int voteCount = voteEntry.getValue();
            Vote v = new Vote(partyName, voteCount);
            voteList.add(v);
        }
        return v1;
    }
}