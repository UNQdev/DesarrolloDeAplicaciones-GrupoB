package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unq.desapp.grupob.model.CardType;

public class CardTypeTest {
	
	@Test
    public void testCardTypeDebit () {
        CardType cardType = CardType.Debit;
        
        assertTrue(cardType.isDebit());
    }
	
	@Test
    public void testCardTypeCredit () {
        CardType cardType = CardType.Credit;
        
        assertFalse(cardType.isDebit());
    }
}
