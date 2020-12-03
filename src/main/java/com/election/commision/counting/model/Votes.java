package com.election.commision.counting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Votes {
    private List<Vote> votes;

//    public List<Vote> getVotes() {
//        return votes;
//    }
//
//    public void setVotes(List<Vote> votes) {
//        this.votes = votes;
//    }
}
