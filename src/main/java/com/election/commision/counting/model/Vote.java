package com.election.commision.counting.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    @JsonProperty("party_name")
    private String partyName;

    @JsonProperty("no_votes")
    private int voteCount;

//    public Vote(String partyName, int voteCount) {
//        this.partyName = partyName;
//        this.voteCount = voteCount;
//    }
//
//    public String getPartyName() {
//        return partyName;
//    }
//
//    public void setPartyName(String partyName) {
//        this.partyName = partyName;
//    }
//
//    public int getVoteCount() {
//        return voteCount;
//    }
//
//    public void setVoteCount(int voteCount) {
//        this.voteCount = voteCount;
//    }

    @Override
    public String toString() {
        return "Vote{" +
                "partyName='" + partyName + '\'' +
                ", voteCount=" + voteCount +
                '}';
    }
}
