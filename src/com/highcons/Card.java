package com.highcons;

/**
 * Created by Mark on 16/05/2017.
 */
public class Card implements Comparable{
    Rank rank;
    Suit suit;

    // constructor for Deck
    Card(Rank aRank, Suit aSuit) {
        this.rank = aRank;
        this.suit = aSuit;
    }

    // Constructor for tests
    Card(String cardString) {
        this.rank = Rank.getRank(cardString.charAt(0));
        this.suit = Suit.getSuit(cardString.charAt(1));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        Card card = (Card) object;

        if (suit != card.suit) return false;
        return rank == card.rank;
    }

    @Override
    public String toString() {
        return  rank.name() + " of " + suit.name();
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + suit.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }

    @Override
    // sort by rank only
    public int compareTo(Object o) {
        Card other = (Card) o;
        return this.rank.compareTo(other.rank) ;
    }
}
