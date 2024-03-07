package com.example.JeuDeLaVie.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class VoteMessage {
    private boolean accept;
}
