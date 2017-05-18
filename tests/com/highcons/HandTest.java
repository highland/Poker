package com.highcons;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

/**
 * Created by Mark on 16/05/2017.
 */
public class HandTest {

    @DataProvider(name = "hands-provider")
    public Object[][] testData() {
        return new Object[][]{
                {"2H 3D 5S 9C KD", "High card"},
        };
    }

    @Test(dataProvider = "hands-provider")
    public void testHand(String hand, String description) {
        Hand testHand = getHandfromString(hand);
        assertEquals(testHand.getDescription(), description);
    }

    private Hand getHandfromString(String hand) {
        String[] cardStrings = hand.split(" ");
        Card[] cards = new Card[5];
        for (int i = 0; i < 5; i++) {
            cards[i] = new Card(cardStrings[i]);
        }
        return new Hand(Arrays.asList(cards));
    }

    @Test
    public void TestStraightFlushPass() {
        Hand testHand = getHandfromString("2C 3C 4C 5C 6C");
        assertTrue(testHand.isStraightFlush());
        assertEquals(testHand.getScore(), 164);
    }

    @Test
    public void TestStraightFlushFailOnSuit() {
       Hand testHand = getHandfromString("2C 3C 4D 5C 6C");
        assertFalse(testHand.isStraightFlush());
    }

    @Test
    public void TestStraightFlushFailOnRank() {
        Card[] cards = {new Card("2C"), new Card("4C"), new Card("5C"), new Card("6C"), new Card("7C")};
        Hand testHand = getHandfromString("2C 4C 5C 6C 7C");
        assertFalse(testHand.isStraightFlush());
    }

    @Test
    public void TestFourOfAKind() {
        Hand testHand = getHandfromString("2C 2D 2S 2H 6C");
        assertTrue(testHand.isFourOfAKind());
        assertEquals(testHand.getScore(), 140);
    }

    @Test
    public void TestFullHouse() {
        Card[] cards = {new Card("2C"), new Card("2D"), new Card("2S"), new Card("4H"), new Card("4C")};
        Hand testHand = getHandfromString("2C 2D 2S 4H 4C");
        assertTrue(testHand.isFullHouse());
        assertEquals(testHand.getScore(), 120);
    }

    @Test
    public void TestFlush() {
        Card[] cards = {new Card("2C"), new Card("4C"), new Card("6C"), new Card("8C"), new Card("TC")};
        Hand testHand = getHandfromString("2C 4C 6C 8C TC");
        assertTrue(testHand.isFlush());
        assertEquals(testHand.getScore(), 108);
    }

    @Test
    public void TestStraight() {
        Hand testHand = getHandfromString("2C 3D 4S 5D 6H");
        assertTrue(testHand.isStraight());
        assertEquals(testHand.getScore(), 84);
    }

    @Test
    public void TestThreeOfAKind() {
        Hand testHand = getHandfromString("2C 3D 3S 3D 6H");
        assertTrue(testHand.isThreeOfAKind());
        assertEquals(testHand.getScore(), 61);
    }

    @Test
    public void TestTwoPairs() {
        Hand testHand = getHandfromString("2C 2D 3S 3D 6H");
        assertTrue(testHand.isTwoPairs());
        assertEquals(testHand.getScore(), 41);
    }

    @Test
    public void TestPair() {
        Card[] cards = {new Card("2C"), new Card("6D"), new Card("3S"), new Card("3D"), new Card("KH")};
        Hand testHand = getHandfromString("2C 6D 3S 3D KH");
        assertTrue(testHand.isPair());
        assertEquals(testHand.getScore(), 21);
    }

}