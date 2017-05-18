package com.highcons;

/**
 * Created by Mark on 16/05/2017.
 */

import java.util.Collections;
import java.util.List;

public class Hand {

    private List<Card> hand;
    private int score;
    private int tieBreaker;
    private String description;

    Hand(List<Card> cards) {
        hand = cards;
        Collections.sort(hand);
        scoreHand();
    }

    private void scoreHand() {
        if (!isStraightFlush())
            if (!isFourOfAKind())
                if (!isFullHouse())
                    if (!isFlush())
                        if (!isStraight())
                            if (!isThreeOfAKind())
                                if (!isTwoPairs())
                                    if (!isPair()) {
                                        score = hand.get(4).rank.ordinal();
                                        description = "High card";
                                        tieBreaker = ((((((hand.get(3).rank.ordinal() * 20) +
                                                hand.get(2).rank.ordinal()) * 20) +
                                                hand.get(1).rank.ordinal()) * 20) +
                                                hand.get(0).rank.ordinal());
                                    }
    }

    public int getScore() {
        return score;
    }

    public int getTieBreaker() {
        return tieBreaker;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Hand: " + hand + " (" + description + ")";
    }

    boolean isStraightFlush() {
        if (!checkSuitsAllMatch()) return false;
        // check consecutive cards
        int value = hand.get(0).rank.ordinal() - 1;
        for (Card card : hand) {
            if (card.rank.ordinal() != value + 1) return false;
            value++;
        }
        score = 160 + value;
        description = "Straight flush";
        return true;
    }

    private boolean checkSuitsAllMatch() {
        Suit first = hand.get(0).suit;
        for (Card card : hand) {
            if (card.suit != first) return false;
        }
        return true;
    }

    boolean isFourOfAKind() {
        int check = hand.get(1).rank.ordinal();
        boolean centreThreeMatch = check == hand.get(2).rank.ordinal() &&
                check == hand.get(3).rank.ordinal();
        boolean eitherEndMatch = check == hand.get(0).rank.ordinal() ||
                check == hand.get(4).rank.ordinal();
        score = 140 + check;
        if (centreThreeMatch && eitherEndMatch) {
            description = "Four of a kind";
            return true;
        } else {
            return false;
        }
    }

    boolean isFullHouse() {
        int check = hand.get(2).rank.ordinal();
        boolean leftThreeMatch = check == hand.get(0).rank.ordinal() &&
                check == hand.get(1).rank.ordinal();
        boolean rightTwoMatch = hand.get(3).rank.ordinal() == hand.get(4).rank.ordinal();
        boolean rightThreeMatch = check == hand.get(3).rank.ordinal() &&
                check == hand.get(4).rank.ordinal();
        boolean leftTwoMatch = hand.get(0).rank.ordinal() == hand.get(1).rank.ordinal();
        if ((leftThreeMatch && rightTwoMatch) || (rightThreeMatch && leftTwoMatch)) {
            score = 120 + check;
            description = "Full house";
            return true;
        } else {
            return false;
        }
    }

    boolean isFlush() {
        if (!checkSuitsAllMatch()) return false;
        score = 100 + hand.get(4).rank.ordinal();
        description = "Flush";
        return true;
    }

    boolean isStraight() {
        int value = hand.get(0).rank.ordinal() - 1;
        for (Card card : hand) {
            if (card.rank.ordinal() != value + 1) return false;
            value++;
        }
        score = 80 + value;
        description = "Straight";
        return true;
    }

    boolean isThreeOfAKind() {
        int check = hand.get(2).rank.ordinal();
        boolean leftThreeMatch = check == hand.get(0).rank.ordinal() &&
                check == hand.get(1).rank.ordinal();
        boolean middleThreeMatch = check == hand.get(1).rank.ordinal() &&
                check == hand.get(3).rank.ordinal();
        boolean rightThreeMatch = check == hand.get(3).rank.ordinal() &&
                check == hand.get(4).rank.ordinal();
        if (leftThreeMatch || middleThreeMatch || rightThreeMatch) {
            score = 60 + check;
            description = "Three of a kind";
            return true;
        } else {
            return false;
        }
    }

    boolean isTwoPairs() {
        int checkl = hand.get(1).rank.ordinal();
        boolean leftTwoMatch = checkl == hand.get(0).rank.ordinal() ||
                checkl == hand.get(2).rank.ordinal();
        int checkr = hand.get(3).rank.ordinal();
        boolean rightTwoMatch = checkr == hand.get(2).rank.ordinal() ||
                checkr == hand.get(4).rank.ordinal();
        if (leftTwoMatch && rightTwoMatch) {
            int check = (checkl > checkr) ? checkl : checkr;
            tieBreaker = (checkl > checkr) ? checkr : checkl;   // still need last card considered
            score = 40 + check;
            description = "Two Pairs";
            return true;
        } else {
            return false;
        }
    }

    boolean isPair() {
        int check1 = hand.get(0).rank.ordinal();
        boolean pair1Match = check1 == hand.get(1).rank.ordinal();
        int check2 = hand.get(1).rank.ordinal();
        boolean pair2Match = check2 == hand.get(2).rank.ordinal();
        int check3 = hand.get(2).rank.ordinal();
        boolean pair3Match = check2 == hand.get(3).rank.ordinal();
        int check4 = hand.get(3).rank.ordinal();
        boolean pair4Match = check4 == hand.get(4).rank.ordinal();
        int rem1, rem2, rem3 = 0;
        if (pair1Match || pair2Match || pair3Match || pair4Match) {
            int check = 0;
            if (pair4Match) {
                check = check4;
                rem1 = check3;
                rem2 = check2;
                rem2 = check1;
            } else if (pair3Match) {
                check = check3;
                rem1 = check4;
                rem2 = check2;
                rem2 = check1;
            } else if (pair2Match) {
                check = check2;
                rem1 = check4;
                rem2 = check3;
                rem2 = check1;
            } else {
                check = check1;
                rem1 = check4;
                rem2 = check3;
                rem2 = check2;
            }
            score = 20 + check;
            tieBreaker = ((((rem1 * 20) + rem2) * 20) + rem3) * 20;
            description = "Pair";
            return true;
        } else {
            return false;
        }
    }
}
