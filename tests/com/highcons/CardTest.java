package com.highcons;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Mark on 16/05/2017.
 */
public class CardTest {
    @Test
    public void testToString() throws Exception {
        Card testCard =  new Card("3C");
        assertEquals(testCard.toString(), "Three of Clubs");
    }

}