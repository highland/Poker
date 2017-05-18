package com.highcons;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Mark on 16/05/2017.
 */
public class DeckTest {
    private Deck testDeck;
    @BeforeMethod
    public void setUp() throws Exception {
        testDeck = new Deck();
    }

    @Test
    public void testDeal() throws Exception {
        Hand testHand = testDeck.deal();
        System.out.println(testHand);
    }

}