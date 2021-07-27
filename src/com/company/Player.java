package com.company;

import java.util.ArrayList;
import java.util.List;

public class Player  {
    String name = "";
    int mana = 0;
    int health = 5;
    int powerLevel = 6;
    List<Die> den = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }
    public String toString(){
        return "Wizard " + name + "   Health: " + health + " Power: " + powerLevel + " Mana: " + mana;
    }

    public void increaseMana(int amount){
        mana += amount;
    }

    public void attack(){
        for(Die die : den){
            die.roll();
            System.out.println("Attack Strength: " + die.faceUpValue);
        }
    }
}
