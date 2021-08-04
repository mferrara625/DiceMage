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
                            break;
                        } else if (input2 == 4) {
                            powerUp (game.dice, game.player);
                        } else if (input2 == 3) {
                            if (addMonster(scan, game.player)) break;
                        } else if (input2 == 2) {
                            hasAttacked = combat(game, game.player, game.player2);
                        } else if (input2 == 1) {
                            displayField(game);
                        } else if (input2 == 6) {
                            healCondition(game.dice, game.player);
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
                            break;
                        } else if (input2 == 4) {
                            powerUp(game.dice2, game.player2);
                        } else if (input2 == 3) {
                            if (addMonster(scan, game.player2)) break;
                        } else if (input2 == 2) {
                            hasAttacked = combat(game, game.player2, game.player);
                        } else if (input2 == 1) {
                            displayField(game);
                        } else if (input2 == 6) {
                            healCondition(game.dice2, game.player2);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void healCondition(List<Die> dice, Player player) {
        if (dice.size() > 0)
            heal(player, dice);
        else
            System.out.println(player.name + " has no mana dice");
    }

    private static void heal(Player player, List<Die> dice) {
        int healAmt = (int) ((Math.random() * 3) + 1);
        player.health += healAmt;
        dice.remove((dice.size() - 1));
        System.out.println(player.name + " restored " + healAmt + " health");
    }

    private static boolean combat(Game game, Player player, Player player2) {
        boolean hasAttacked;
        player.attack();
        player2.attack();
        hasAttacked = true;
        player.den.stream().sorted();
        player2.den.stream().sorted();
        for (int m = 0; m < player.den.size(); m++) {
            if (player.den.size() == player2.den.size()) {
                monstersAttack(m, player, player2);
            } else if (player.den.size() > player2.den.size()) {
                int tempNum = (player.den.size() - player2.den.size());
                if (player2.den.size() == 0) {
                    System.out.println(player.name + "'s monster attacked " + player2.name + " directly!!!");
                    player2.health--;
                    tempNum = 0;
                }
                moreMonstersAttack(player2, player);
                while (tempNum > 0) {
                    System.out.println(player.name + "'s monster attacked " + player2.name + " directly!!!");
                    player2.health--;
                    tempNum--;
                    m = game.player.den.size();
                }
            }
        }
        return hasAttacked;
    }

    private static void powerUp(List<Die> dice, Player player) {
        System.out.println("Power Up");
        dice.add(new Die());
        player.powerLevel++;
        player.mana -= (player.powerLevel - 3);
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
            player.mana -= (numberInput + 3);
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
        if (player.health < 3)
            System.out.println("(6) to heal (costs 1 mana die)");
    }
}