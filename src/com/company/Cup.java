package com.company;

import java.util.*;

public class Cup {
    Map <Integer, Integer> freq = new TreeMap<>();
    List<Die> dice = new ArrayList<>();
    List<Die> dice2 = new ArrayList<>();
    List<Player> players = new ArrayList<>();
    Player player2 = new Player("Albus");
    Player player = new Player("Merlin");
    Integer[] dieHolder;
    Player activePlayer = player;



    public Cup(int numOfDie) {

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

            for(Integer num : dieHolder)
                output += num + " ";

        } else if (activePlayer == player2){

            dieHolder = new Integer[dice2.size()];
            for (int i = 0; i < dice2.size(); i++){
                dieHolder[i] = dice2.get(i).faceUpValue;
            }
            Arrays.sort(dieHolder);

            for(Integer num : dieHolder)
                output += num + " ";

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
            System.out.println("Mana Pulled: " + manaPulled);
            System.out.println("Total Mana: " + player.mana);
        } else if (activePlayer == player2){
            player2.mana += manaPulled;
            System.out.println("Mana Pulled: " + manaPulled);
            System.out.println("Total Mana: " + player2.mana);
        }

    }
}
