package com.highcons;

/**
 * Created by Mark on 16/05/2017.
 */
public enum Suit {
    Clubs('C'),
    Diamonds('D'),
    Hearts('H'),
    Spades('S');

    private char representation;

    private Suit(char suitChar) {
        this.representation = suitChar;
    }

    public static Suit getSuit(char suitChar){
        for (Suit each: Suit.values()) {
              if (each.representation == suitChar) return each;
        }
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(representation);
    }
}
