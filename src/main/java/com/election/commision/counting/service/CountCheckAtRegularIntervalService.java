package com.election.commision.counting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Service
public class CountCheckAtRegularIntervalService {
    @Autowired
    @Qualifier("map")
    private Map<String,Integer> map;

    @Autowired
    @Qualifier("fos")
    private FileOutputStream fos;

    public void countCheckAtRegularInterval() throws IOException {
        fos.write(("Result till now: " + new Date() + System.lineSeparator()).getBytes(StandardCharsets.UTF_8));
        for (Map.Entry<String, Integer> voteEntry : map.entrySet()) {
            String partyName = voteEntry.getKey();
            int voteCount = voteEntry.getValue();
            fos.write((partyName + ": " + voteCount + System.lineSeparator()).getBytes(StandardCharsets.UTF_8));
        }
    }


}
