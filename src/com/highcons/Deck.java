package com.highcons;

/**
 * Created by Mark on 16/05/2017.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private List<Card> cards;

    public Deck() {
        cards = new LinkedList<Card>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
    }

    public Hand deal() {
        if (cards.size() < 5) throw new IllegalStateException();
        List<Card> dealt = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
              dealt.add(cards.remove(0));
        }
        return new Hand(dealt);
     }

 }
