package com.example.JeuDeLaVie.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@RequiredArgsConstructor
@Data
public class Rule {
    @Id
    private String id;
    private int aliveNeighborsToStayAlive;
    private int deadNeighborsToBecomeAlive;


}
