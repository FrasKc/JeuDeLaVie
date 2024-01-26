package com.example.JeuDeLaVie.model;

import lombok.Data;

@Data
public class Cell {
    private int row;
    private int col;
    private boolean alive;
}
