package com.example.JeuDeLaVie.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import com.example.JeuDeLaVie.model.Rule;

import java.util.Random;

@Service
public class GameService {

    @Getter
    private boolean[][] board;
    private final int rows = 20;
    private final int cols = 20;
    private Rule currentRule;

    public GameService() {
        this.board = new boolean[rows][cols];
        this.currentRule = new Rule(2, 3);
    }

    public void updateRules(Rule newRule) {
        this.currentRule = newRule;
    }

    public void initializeBoardWithRandomPattern() {
        clearBoard();
        Random rand = new Random();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = rand.nextBoolean();
            }
        }
    }

    private void clearBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = false;
            }
        }
    }

    public void nextGeneration() {
        boolean[][] newBoard = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveNeighbors = countLiveNeighbors(row, col);
                boolean staysAlive = liveNeighbors >= currentRule.getAliveNeighborsToStayAlive() && liveNeighbors <= currentRule.getDeadNeighborsToBecomeAlive();
                boolean becomesAlive = liveNeighbors == currentRule.getDeadNeighborsToBecomeAlive();
                if (board[row][col]) {
                    newBoard[row][col] = staysAlive;
                } else {
                    newBoard[row][col] = becomesAlive;
                }
            }
        }
        board = newBoard;
    }

    private int countLiveNeighbors(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    count += board[newRow][newCol] ? 1 : 0;
                }
            }
        }
        return count;
    }

    public String getCurrentRuleAsString() {
        return "Reste en vie avec " + currentRule.getAliveNeighborsToStayAlive() +
                " voisins, devient vivante avec " + currentRule.getDeadNeighborsToBecomeAlive() + " voisins.";
    }
}
