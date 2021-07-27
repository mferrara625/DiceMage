package com.company;

import java.util.*;

public class Cup {
    Map <Integer, Integer> freq = new TreeMap<>();
    List<Die> dice = new ArrayList<>();
    Player player = new Player("Merlin");
    Integer[] dieHolder;


    public Cup(int numOfDie) {
        while(dice.size() < numOfDie) {
            dice.add(new Die());
        }
    }

    public void roll() {
        for (Die die : dice) {
            die.roll();
        }
    }
    public String orderedDisplay(){
        String output = "";
        dieHolder = new Integer[dice.size()];
        for (int i = 0; i < dice.size(); i++){
            dieHolder[i] = dice.get(i).faceUpValue;
        }
        Arrays.sort(dieHolder);

        for(Integer num : dieHolder)
            output += num + " ";
        return output;
    }

    public void calcMana(){
        int manaPulled = 1;
        for (int i = 0; i < dieHolder.length - 2; i++){
            if (dieHolder[i] == dieHolder[i + 1] && dieHolder[i] == dieHolder[i + 2]){
                manaPulled++;
            }
        }
        player.mana += manaPulled;
        System.out.println("Mana Pulled: " + manaPulled);
        System.out.println("Total Mana: " + player.mana);
    }
}
