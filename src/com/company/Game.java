package com.company;

import java.util.*;

public class Game {
    List<Die> dice = new ArrayList<>();
    List<Die> dice2 = new ArrayList<>();
    List<Player> players = new ArrayList<>();
    Player player2 = new Player("Albus");
    Player player = new Player("Merlin");
    Integer[] dieHolder;
    Player activePlayer = player;

    String[] output;
    public final String[] ONE_FACE = new String[]{
            "  ________    ",
            "|\\  o    o \\  ",
            "|o\\ ________\\ ",
            "| o|         |",
            "'o |    o    |",
            " \\o|         |",
            "   '---------'",
            "       (1)    "
    };
    public final String[] TWO_FACE = new String[]{
            "  ________    ",
            "|\\    o    \\  ",
            "|o\\ ________\\ ",
            "| o| o       |",
            "'o |         |",
            " \\o|       o |",
            "   '---------'",
            "       (2)    "
    };
    public final String[] THREE_FACE = new String[]{
            "  ________    ",
            "|\\    o    \\  ",
            "|o\\ ________\\ ",
            "|  | o       |",
            "' o|    o    |",
            " \\ |       o |",
            "   '---------'",
            "       (3)    "
    };
    public final String[] FOUR_FACE = new String[]{
            "  ________    ",
            "|\\    o    \\  ",
            "|o\\ ________\\ ",
            "|  | o     o |",
            "' o|         |",
            " \\ | o     o |",
            "   '---------'",
            "       (4)    "
    };
    public final String[] FIVE_FACE = new String[]{
            "  ________    ",
            "|\\    o    \\  ",
            "|o\\ ________\\ ",
            "| o| o     o |",
            "'o |    o    |",
            " \\o| o     o |",
            "   '---------'",
            "       (5)    "
    };
    public final String[] SIX_FACE = new String[]{
            "  ________    ",
            "|\\  o    o \\  ",
            "|o\\ ________\\ ",
            "| o| o     o |",
            "'o | o     o |",
            " \\o| o     o |",
            "   '---------'",
            "       (6)    "
    };


    public Game(int numOfDie) {

            while(dice.size() < numOfDie) {
                dice.add(new Die());
            }
            while(dice2.size() < numOfDie) {
                dice2.add(new Die());
            }
        }

    public void roll() {
        if(activePlayer == player){
            for (Die die : dice) {
                die.roll();
            }
        } else if (activePlayer == player2){
            for (Die die : dice2) {
                die.roll();
            }
        }

    }

    public String orderedDisplay(){
        String output = "";
        if(activePlayer == player){

            dieHolder = new Integer[dice.size()];
            for (int i = 0; i < dice.size(); i++){
                dieHolder[i] = dice.get(i).faceUpValue;
            }
            Arrays.sort(dieHolder);

            for(Integer num : dieHolder){
                setDice(num);
                for(int a = 0; a < 8; a++){
                    System.out.println(this.output[a]);
                }
            }

        } else if (activePlayer == player2){

            dieHolder = new Integer[dice2.size()];
            for (int i = 0; i < dice2.size(); i++){
                dieHolder[i] = dice2.get(i).faceUpValue;
            }
            Arrays.sort(dieHolder);

            for (Integer num : dieHolder) {
                setDice(num);
                for (int a = 0; a < 8; a++) {
                    System.out.println(this.output[a]);
                }
            }
        }
        return output;
    }

    public void calcMana(){
        int manaPulled = 1;
        for (int i = 0; i < dieHolder.length - 2; i++){
            if (dieHolder[i] == dieHolder[i + 1] && dieHolder[i] == dieHolder[i + 2]){
                manaPulled++;
            }
        }
        if(activePlayer == player){
            player.mana += manaPulled;
            System.out.println(player.name + " pulled " + manaPulled + " mana");
            System.out.println("\nTotal Mana: " + player.mana);
        } else if (activePlayer == player2){
            player2.mana += manaPulled;
            System.out.println(player2.name + " pulled " + manaPulled + " mana");
            System.out.println("\nTotal Mana: " + player2.mana);
        }

    }

    public void setDice(Integer num){

        if(num == 1){
            output =  ONE_FACE;
        }
        if(num == 2){
            output =  TWO_FACE;
        }
        if(num == 3){
            output =  THREE_FACE;
        }
        if(num == 4){
            output =  FOUR_FACE;
        }
        if(num == 5){
            output =  FIVE_FACE;
        }
        if(num == 6){
            output =  SIX_FACE;
        }
    }
}
