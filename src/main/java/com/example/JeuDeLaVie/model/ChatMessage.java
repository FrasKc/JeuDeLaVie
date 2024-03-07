package com.example.JeuDeLaVie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String content;
    private String sender;
}
