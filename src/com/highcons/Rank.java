package com.highcons;

/**
 * Created by Mark on 16/05/2017.
 */
public enum Rank {
    Two('2'),
    Three('3'),
    Four('4'),
    Five('5'),
    Six('6'),
    Seven('7'),
    Eight('8'),
    Nine('9'),
    Ten('T'),
    Jack('J'),
    Queen('Q'),
    King('K'),
    Ace('A');

    private char rank;

    private Rank(char rankChar) {
        this.rank = rankChar;
    }

    public static Rank getRank(char rankChar){
        for (Rank each: Rank.values()) {
              if (each.rank == rankChar) return each;
        }
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(rank);
    }

}
