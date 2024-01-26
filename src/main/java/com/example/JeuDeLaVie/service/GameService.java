package com.example.JeuDeLaVie.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {

    @Getter
    private boolean[][] board;
    private final int rows = 20;
    private final int cols = 20;

    public GameService() {
        this.board = new boolean[rows][cols];
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
                if (board[row][col]) {
                    // Une cellule vivante reste vivante avec 2 ou 3 voisins vivants, sinon elle meurt.
                    newBoard[row][col] = (liveNeighbors == 2 || liveNeighbors == 3);
                } else {
                    // Une cellule morte devient vivante avec exactement 3 voisins vivants.
                    newBoard[row][col] = liveNeighbors == 3;
                }
            }
        }
        board = newBoard;
    }

    private int countLiveNeighbors(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // On saute la cellule elle-même
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    count += board[newRow][newCol] ? 1 : 0;
                }
            }
        }
        return count;
    }
}
