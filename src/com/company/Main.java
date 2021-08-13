package com.company;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Game game = new Game(6);
        game.players.add(game.player);
        game.players.add(game.player2);
        int input2 = 0;
        boolean isGameActive = true;
        GamePlay gamePlay = new GamePlay();
        Art art = new Art();
        art.intro();
        gamePlay.playDiceMage(scan, game, input2, isGameActive);
    }
}