package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner scan = new Scanner(System.in);
        Cup myCup = new Cup(6);
        myCup.players.add(myCup.player);
        myCup.players.add(myCup.player2);
        int input2 = 0;
        int j = 0;
        int i = 0;

        boolean hasAttacked;
        boolean isGameActive = true;
        System.out.println("Press Enter To Start Game");
        while (isGameActive) {
            String input = scan.nextLine();
            if (input == "") {
                for (i = 0; i < 1; i++) {
                    myCup.activePlayer = myCup.player;
                    if (myCup.player.health <= 0) {
                        System.out.println("Game Over " + myCup.player2.name + " wins!");
                        isGameActive = false;
                        break;
                    }
                    if (myCup.player2.health <= 0) {
//                        System.out.println("Game Over " + myCup.player.name + " wins!");
                        isGameActive = false;
                        break;
                    }
                    hasAttacked = false;
                    myCup.roll();
                    System.out.println(myCup.player.name + "'s turn");
                    System.out.println("Mana is in the air...");
                    System.out.println(myCup.orderedDisplay());
                    myCup.calcMana();
                    while (input2 != 5) {
                        System.out.println(myCup.player);
                        System.out.println("Monsters : " + myCup.player.den.size());
                        System.out.println("(1) to view field");
                        if (myCup.player.den.size() > 0 && !hasAttacked)
                            System.out.println("(2) to attack");
                        if (myCup.player.mana >= 4) {
                            if (myCup.player.mana >= 6) {
                                System.out.println("(3) to summon a monster (and end turn)");
                            }
                            System.out.println("(4) to increase power");
                        }
                        System.out.println("(5) to end turn");
                        input2 = scan.nextInt();
                        if (input2 == 5) {
                            input2 = 0;
                            j = 0;
                            break;
                        } else if (input2 == 4) {
                            System.out.println("Power Up");
                            myCup.dice.add(new Die());
                            myCup.player.powerLevel++;
                            myCup.player.mana -= 4;
                            continue;
                        } else if (input2 == 3) {
                            if (myCup.player.mana < 13)
                            System.out.println("Choose Monster 3-" + (myCup.player.mana - 3));
                            else
                                System.out.println("Choose Monster 3-10");
                            int numberInput = scan.nextInt();
                            if((numberInput >= 3 && numberInput <= 10) && (numberInput <= (myCup.player.mana - 3))){
                                myCup.player.den.add(new Die(numberInput));
                                myCup.player.mana -= (numberInput + 1);
                                System.out.println("Monster Added to Den");
                                break;
                            }

                        } else if (input2 == 2) {
                            myCup.player.attack();
                            myCup.player2.attack();
                            hasAttacked = true;
                             myCup.player.den.stream().sorted();
                            myCup.player2.den.stream().sorted();
                            for (int m = 0; m < myCup.player.den.size(); m++) {
                                if(myCup.player.den.size() == myCup.player2.den.size()){
                                    if (myCup.player.den.get(m).faceUpValue > myCup.player2.den.get(m).faceUpValue) {
                                        System.out.println(myCup.player.name + "'s Attack was stronger, " + myCup.player2.name + "'s monster was killed.");
                                        myCup.player2.den.remove(m);
                                    } else if (myCup.player.den.get(m).faceUpValue < myCup.player2.den.get(m).faceUpValue){
                                        System.out.println(myCup.player2.name + "'s Monster defended and killed " + myCup.player.name + "'s Monster");
                                        myCup.player.den.remove(m);
                                    } else {
                                        System.out.println("Monsters attacks had equal strength");
                                    }
                                } else if (myCup.player.den.size() > myCup.player2.den.size()){
                                    int tempNum = (myCup.player.den.size() - myCup.player2.den.size());
                                    if(myCup.player2.den.size() == 0){
                                        System.out.println(myCup.player.name + "'s monster attacked " + myCup.player2.name + " directly!!!");
                                        myCup.player2.health--;
                                        tempNum = 0;
                                    }
                                    for (int n = 0; n < tempNum; n++){
                                        if (myCup.player.den.get(n).faceUpValue > myCup.player2.den.get(n).faceUpValue) {
                                            System.out.println(myCup.player.name + "'s Attack was stronger, " + myCup.player2.name + "'s monster was killed.");
                                            myCup.player2.den.remove(n);
                                        } else if (myCup.player.den.get(n).faceUpValue < myCup.player2.den.get(n).faceUpValue){
                                            System.out.println(myCup.player2.name + "'s Monster defended and killed " + myCup.player.name + "'s Monster");
                                            myCup.player.den.remove(n);
                                        } else {
                                            System.out.println("Monsters attacks had equal strength");
                                        }
                                    }
                                    while(tempNum > 0) {
                                        System.out.println(myCup.player.name + "'s monster attacked " + myCup.player2.name + " directly!!!");
                                        myCup.player2.health--;
                                        tempNum--;
                                    }
                                }

                            }
                        } else if (input2 == 1) {
                            for (Player play : myCup.players) {
                                System.out.println(play);
                                System.out.println("Monsters: " + play.den.size());
                                System.out.println("\n");
                            }
                        } else {
                            break;
                        }
                    }
                }

                for (j = 0; j < 1; j++) {
                    myCup.activePlayer = myCup.player2;
                    if (myCup.player2.health <= 0) {
                        System.out.println("Game Over " + myCup.player.name + " wins!");
                        isGameActive = false;
                        break;
                    }
                    if (myCup.player.health <= 0) {
//                        System.out.println("Game Over " + myCup.player2.name + " wins!");
                        isGameActive = false;
                        break;
                    }

                    hasAttacked = false;
                    myCup.roll();
                    System.out.println(myCup.player2.name + "'s turn");
                    System.out.println("Mana is in the air...");
                    System.out.println(myCup.orderedDisplay());
                    myCup.calcMana();
                    while (input2 != 5) {
                        System.out.println(myCup.player2);
                        System.out.println("Monsters : " + myCup.player2.den.size());
                        System.out.println("(1) to view field");
                        if (myCup.player2.den.size() > 0 && !hasAttacked)
                            System.out.println("(2) to attack");
                        if (myCup.player2.mana >= 4) {
                            if (myCup.player2.mana >= 6) {
                                System.out.println("(3) to summon a monster (and end turn)");
                            }
                            System.out.println("(4) to increase power");
                        }
                        System.out.println("(5) to end turn");
                        input2 = scan.nextInt();
                        if (input2 == 5) {
                            input2 = 0;
                            i = 0;
                            break;
                        } else if (input2 == 4) {
                            System.out.println("Power Up");
                            myCup.dice2.add(new Die());
                            myCup.player2.powerLevel++;
                            myCup.player2.mana -= 4;
                            continue;
                        } else if (input2 == 3) {
                            if (myCup.player2.mana < 13)
                                System.out.println("Choose Monster 3-" + (myCup.player2.mana - 3));
                            else
                                System.out.println("Choose Monster 3-10");
                            int numberInput = scan.nextInt();
                            if((numberInput >= 3 && numberInput <= 10) && (numberInput <= (myCup.player2.mana - 3))){
                                myCup.player2.den.add(new Die(numberInput));
                                myCup.player2.mana -= (numberInput + 1);
                                System.out.println("Monster Added to Den");
                                break;
                            }
                        } else if (input2 == 2) {
                            myCup.player2.attack();
                            myCup.player.attack();
                            hasAttacked = true;
                            myCup.player2.den.stream().sorted();
                            myCup.player.den.stream().sorted();
                            for (int m = 0; m < myCup.player2.den.size(); m++) {
                                if(myCup.player2.den.size() == myCup.player.den.size()){
                                    if (myCup.player2.den.get(m).faceUpValue > myCup.player.den.get(m).faceUpValue) {
                                        System.out.println(myCup.player2.name + "'s Attack was stronger, " + myCup.player.name + "'s monster was killed.");
                                        myCup.player.den.remove(m);
                                    } else if (myCup.player2.den.get(m).faceUpValue < myCup.player.den.get(m).faceUpValue){
                                        System.out.println(myCup.player.name + "'s Monster defended and killed " + myCup.player2.name + "'s Monster");
                                        myCup.player2.den.remove(m);
                                    } else {
                                        System.out.println("Monsters attacks had equal strength");
                                    }
                                } else if (myCup.player2.den.size() > myCup.player.den.size()){
                                    int tempNum = (myCup.player2.den.size() - myCup.player.den.size());
                                    if(myCup.player.den.size() == 0){
                                        System.out.println(myCup.player2.name + "'s monster attacked " + myCup.player.name + " directly!!!");
                                        myCup.player.health--;
                                        tempNum = 0;
                                    }
                                    for (int n = 0; n < tempNum; n++){
                                        if (myCup.player2.den.get(n).faceUpValue > myCup.player.den.get(n).faceUpValue) {
                                            System.out.println(myCup.player2.name + "'s Attack was stronger, " + myCup.player.name + "'s monster was killed.");
                                            myCup.player.den.remove(n);
                                        } else if (myCup.player2.den.get(n).faceUpValue < myCup.player.den.get(n).faceUpValue){
                                            System.out.println(myCup.player.name + "'s Monster defended and killed " + myCup.player2.name + "'s Monster");
                                            myCup.player2.den.remove(n);
                                        } else {
                                            System.out.println("Monsters attacks had equal strength");
                                        }
                                    }
                                    while(tempNum > 0) {
                                        System.out.println(myCup.player2.name + "'s monster attacked " + myCup.player.name + " directly!!!");
                                        myCup.player.health--;
                                        tempNum--;
                                    }
                                }

                            }

                        } else if (input2 == 1) {
                            for (Player play : myCup.players) {
                                System.out.println(play);
                                System.out.println("Monsters: " + play.den.size());
                                System.out.println("\n");
                            }
                        } else {
                            break;
                        }
                    }
                }
            }

        }
    }
}
