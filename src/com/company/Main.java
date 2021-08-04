package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner scan = new Scanner(System.in);
        Game game = new Game(6);
        game.players.add(game.player);
        game.players.add(game.player2);
        int input2 = 0;
        boolean isGameActive = true;
        System.out.println("Press Enter To Start Game");
        playDiceMage(scan, game, input2, isGameActive);
    }

    private static void playDiceMage(Scanner scan, Game game, int input2, boolean isGameActive) {
        boolean hasAttacked;
        int i;
        int j;
        while (isGameActive) {
            String input = scan.nextLine();
            if (input == "") {
                for (i = 0; i < 1; i++) {
                    game.activePlayer = game.player;
                    if (game.player.health <= 0) {
                        System.out.println("Game Over " + game.player2.name + " wins!");
                        isGameActive = false;
                        break;
                    }
                    if (game.player2.health <= 0) {
                        isGameActive = false;
                        break;
                    }
                    hasAttacked = false;
                    beginTurn(game, game.player);
                    while (input2 != 5) {
                        displayActions(hasAttacked, game.player);
                        input2 = scan.nextInt();
                        if (input2 == 5) {
                            input2 = 0;
                            j = 0;
                            break;
                        } else if (input2 == 4) {
                            System.out.println("Power Up");
                            game.dice.add(new Die());
                            game.player.powerLevel++;
                            game.player.mana -= (game.player.powerLevel - 3);
                        } else if (input2 == 3) {
                            if (addMonster(scan, game.player)) break;

                        } else if (input2 == 2) {
                            game.player.attack();
                            game.player2.attack();
                            hasAttacked = true;
                            game.player.den.stream().sorted();
                            game.player2.den.stream().sorted();
                            for (int m = 0; m < game.player.den.size(); m++) {
                                if(game.player.den.size() == game.player2.den.size()){
                                    monstersAttack(m, game.player, game.player2);
                                } else if (game.player.den.size() > game.player2.den.size()){
                                    int tempNum = (game.player.den.size() - game.player2.den.size());
                                    if(game.player2.den.size() == 0){
                                        System.out.println(game.player.name + "'s monster attacked " + game.player2.name + " directly!!!");
                                        game.player2.health--;
                                        tempNum = 0;
                                    }
                                    moreMonstersAttack(game.player2, game.player);
                                    while(tempNum > 0) {
                                        System.out.println(game.player.name + "'s monster attacked " + game.player2.name + " directly!!!");
                                        game.player2.health--;
                                        tempNum--;
                                        m = game.player.den.size();
                                    }
                                }
                            }
                        } else if (input2 == 1) {
                            displayField(game);
                        } else {
                            break;
                        }
                    }
                }
                for (j = 0; j < 1; j++) {
                    game.activePlayer = game.player2;
                    if (game.player2.health <= 0) {
                        System.out.println("Game Over " + game.player.name + " wins!");
                        isGameActive = false;
                        break;
                    }
                    if (game.player.health <= 0) {
                        isGameActive = false;
                        break;
                    }
                    hasAttacked = false;
                    beginTurn(game, game.player2);
                    while (input2 != 5) {
                        displayActions(hasAttacked, game.player2);
                        input2 = scan.nextInt();
                        if (input2 == 5) {
                            input2 = 0;
                            i = 0;
                            break;
                        } else if (input2 == 4) {
                            System.out.println("Power Up");
                            game.dice2.add(new Die());
                            game.player2.powerLevel++;
                            game.player2.mana -= (game.player2.powerLevel - 3);
                        } else if (input2 == 3) {
                            if (addMonster(scan, game.player2)) break;
                        } else if (input2 == 2) {
                            game.player2.attack();
                            game.player.attack();
                            hasAttacked = true;
                            game.player2.den.stream().sorted();
                            game.player.den.stream().sorted();
                            for (int m = 0; m < game.player2.den.size(); m++) {
                                if(game.player2.den.size() == game.player.den.size()){
                                    monstersAttack(m, game.player2, game.player);
                                } else if (game.player2.den.size() > game.player.den.size()){
                                    int tempNum = (game.player2.den.size() - game.player.den.size());
                                    if(game.player.den.size() == 0){
                                        System.out.println(game.player2.name + "'s monster attacked " + game.player.name + " directly!!!");
                                        game.player.health--;
                                        tempNum = 0;
                                    }
                                    moreMonstersAttack(game.player, game.player2);
                                    while(tempNum > 0) {
                                        System.out.println(game.player2.name + "'s monster attacked " + game.player.name + " directly!!!");
                                        game.player.health--;
                                        tempNum--;
                                        m = game.player.den.size();
                                    }
                                }
                            }
                        } else if (input2 == 1) {
                            displayField(game);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void monstersAttack(int m, Player player, Player player2) {
        if (player.den.get(m).faceUpValue > player2.den.get(m).faceUpValue) {
            System.out.println(player.name + "'s Attack was stronger, " + player2.name + "'s monster was killed.");
            player2.den.remove(m);
        } else if (player.den.get(m).faceUpValue < player2.den.get(m).faceUpValue) {
            System.out.println(player2.name + "'s Monster defended and killed " + player.name + "'s Monster");
            player.den.remove(m);
        } else {
            System.out.println("Monsters attacks had equal strength");
        }
    }

    private static void moreMonstersAttack(Player player, Player player2) {
        for (int n = 0; n < player.den.size(); n++) {
            monstersAttack(n, player2, player);
        }
    }

    private static void beginTurn(Game game, Player player2) {
        game.roll();
        System.out.println(player2.name + "'s turn");
        System.out.println("Mana is in the air...");
        System.out.println(game.orderedDisplay());
        game.calcMana();
    }

    private static void displayField(Game game) {
        for (Player play : game.players) {
            System.out.println(play);
            System.out.println("Monsters: " + play.den.size());
            System.out.println("\n");
        }
    }

    private static boolean addMonster(Scanner scan, Player player) {
        if (player.mana < 13)
            System.out.println("Choose Monster 3-" + (player.mana - 3));
        else
            System.out.println("Choose Monster 3-10");
        int numberInput = scan.nextInt();
        if ((numberInput >= 3 && numberInput <= 10) && (numberInput <= (player.mana - 3))) {
            player.den.add(new Die(numberInput));
            player.mana -= (numberInput + 1);
            System.out.println("Monster Added to Den");
            return true;
        }
        return false;
    }

    private static void displayActions(boolean hasAttacked, Player player) {
        System.out.println(player);
        System.out.println("Monsters : " + player.den.size());
        System.out.println("(1) to view field");
        if (player.den.size() > 0 && !hasAttacked)
            System.out.println("(2) to attack");
        if (player.mana >= 6) {
            System.out.println("(3) to summon a monster (and end turn)");
        }
        if (player.mana >= (player.powerLevel - 2)) {
            System.out.println("(4) to increase power for " + (player.powerLevel - 2) + " mana");
        }
        System.out.println("(5) to end turn");
    }
}