package com.highcons;

/**
 * Created by Mark on 16/05/2017.
 */
public class Main {
    public static void main(String[] args) {
        Deck theDeck = new Deck();
        Hand hand1 = theDeck.deal();
        System.out.println("Player 1");
        System.out.println("\t" + hand1);
        Hand hand2 = theDeck.deal();
        System.out.println("Player 2");
        System.out.println("\t" + hand2);
        System.out.println("*************");
        if (hand1.getScore() > hand2.getScore()) System.out.println("Player 1 wins");
        else if (hand1.getScore() == hand2.getScore())  {
            if (hand1.getTieBreaker() > hand2.getTieBreaker()) System.out.println("Player 1 wins");
            else System.out.println("Player 2 wins");
        }
        else System.out.println("Player 2 wins");


    }
}
