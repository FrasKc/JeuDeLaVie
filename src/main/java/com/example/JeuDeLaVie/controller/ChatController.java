package com.example.JeuDeLaVie.controller;

import com.example.JeuDeLaVie.model.ChatMessage;
import com.example.JeuDeLaVie.model.Rule;
import com.example.JeuDeLaVie.model.RuleProposal;
import com.example.JeuDeLaVie.model.VoteMessage;
import com.example.JeuDeLaVie.service.GameService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final GameService gameService;
    private RuleProposal currentProposal;


    @MessageMapping("/message")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        if (chatMessage.getContent().startsWith("rule:")) {
            String[] parts = chatMessage.getContent().substring(5).split(",");
            int aliveToStayAlive = Integer.parseInt(parts[0]);
            int deadToBecomeAlive = Integer.parseInt(parts[1]);
            Rule newRule = new Rule(aliveToStayAlive, deadToBecomeAlive);
            gameService.updateRules(newRule);
            chatMessage.setContent("Les règles ont été mises à jour : Reste en vie avec " + aliveToStayAlive + " voisins, devient vivante avec " + deadToBecomeAlive + " voisins.");
        }
        return chatMessage;
    }

    @MessageMapping("/requestCurrentRule")
    @SendTo("/topic/public")
    public ChatMessage sendCurrentRule() {
        String rule = gameService.getCurrentRuleAsString();
        return new ChatMessage(rule, "Système");
    }

    @MessageMapping("/vote")
    @SendTo("/topic/public")
    public ChatMessage handleVote(@Payload VoteMessage voteMessage, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("Received vote: " + voteMessage);
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (currentProposal != null) {
            currentProposal.getVotes().put(username, voteMessage.isAccept());
            if (checkIfVotingComplete()) {
                boolean isAccepted = calculateResult();
                if (isAccepted) {
                    gameService.updateRules(currentProposal.getProposedRule());
                    currentProposal = null; // Reset pour la prochaine proposition
                    return new ChatMessage("Système", "La nouvelle règle a été acceptée et est maintenant active.");
                } else {
                    currentProposal = null; // Reset pour la prochaine proposition
                    return new ChatMessage("Système", "La proposition de règle a été rejetée.");
                }
            }
        }
        return null;
    }

    private boolean checkIfVotingComplete() {
        return currentProposal.getVotes().size() >= currentProposal.getRequiredVotes();
    }

    private boolean calculateResult() {
        long acceptCount = currentProposal.getVotes().values().stream().filter(vote -> vote).count();
        return acceptCount > currentProposal.getVotes().size() / 2;
    }
}
