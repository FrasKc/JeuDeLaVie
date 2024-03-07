package com.example.JeuDeLaVie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RuleProposal {
    private Rule proposedRule;
    private Map<String, Boolean> votes;
    private int requiredVotes;
}
