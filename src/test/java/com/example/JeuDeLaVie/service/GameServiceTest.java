package com.example.JeuDeLaVie.service;

import com.example.JeuDeLaVie.model.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameService();
    }

    @Test
    void testInitializeBoardWithRandomPattern() {
        gameService.initializeBoardWithRandomPattern();
        assertNotNull(gameService.getBoard());
    }

    @Test
    void testNextGeneration() {
        gameService.initializeBoardWithRandomPattern();
        boolean[][] initialBoard = gameService.getBoard().clone();
        gameService.nextGeneration();
        assertNotEquals(initialBoard, gameService.getBoard());
    }

    @Test
    void testUpdateRules() {
        Rule newRule = new Rule(3, 3);
        gameService.updateRules(newRule);
        assertEquals("Reste en vie avec 3 voisins, devient vivante avec 3 voisins.", gameService.getCurrentRuleAsString());
    }
}