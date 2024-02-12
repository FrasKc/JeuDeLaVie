package com.example.JeuDeLaVie.controller;

import com.example.JeuDeLaVie.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        gameService.nextGeneration();
        model.addAttribute("board", gameService.getBoard());
        return "home";
    }



    @PostMapping("/startGame")
    public String startGame() {
        gameService.initializeBoardWithRandomPattern();
        return "redirect:/home";
    }

    @PostMapping("/advance")
    @ResponseBody
    public boolean[][] advanceGame() {
        gameService.nextGeneration();
        return gameService.getBoard();
    }
}
