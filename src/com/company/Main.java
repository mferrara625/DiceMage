package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scan = new Scanner(System.in);
        Cup myCup = new Cup(6);
        int input2 = 0;
        boolean hasAttacked;
        System.out.println("Press Enter To Start Your Turn");
        String input = scan.nextLine();
        if (input == "") {
        for(int i = 0; i < 10; i++) {
                hasAttacked = false;
                myCup.roll();
                System.out.println("Mana is in the air...");
                System.out.println(myCup.orderedDisplay());
                myCup.calcMana();
                while(input2 != 5) {
                    System.out.println(myCup.player);
                    System.out.println("Monsters : " + myCup.player.den.size());
                    System.out.println("(1) to view field");
                    if (myCup.player.den.size() > 0 && !hasAttacked)
                        System.out.println("(2) to attack");
                    if (myCup.player.mana >= 4) {
                        if (myCup.player.mana >= 6) {
                            System.out.println("(3) to summon a monster");
                        }
                        System.out.println("(4) to increase power");
                    }
                    System.out.println("(5) to end turn");
                    input2 = scan.nextInt();
                    if (input2 == 5) {
                        input2 = 0;
                        break;
                    } else if (input2 == 4) {
                        System.out.println("Power Up");
                        myCup.dice.add(new Die());
                        myCup.player.powerLevel++;
                        myCup.player.mana -= 4;
                        continue;
                    } else if (input2 == 3) {
                        System.out.println("Choose Monster 3-3");
                        int numberInput = scan.nextInt();
                        myCup.player.den.add(new Die(numberInput));
                        myCup.player.mana -= 4;
                        System.out.println("Monster Added to Den");

                    } else if (input2 == 2) {
                        myCup.player.attack();
                        hasAttacked = true;
                    } else if (input2 == 1) {
                        System.out.println(myCup.player);
                        System.out.println("Monsters: " + myCup.player.den.size());
                        System.out.println("\n");
                    } else {
                        break;
                    }
                }
            }
        }

    }
}
