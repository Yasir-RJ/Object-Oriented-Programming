package ps;

import cardutils.Card;
import cardutils.Pile;
import java.util.List;

/**
 * Interface for classes implementing the logic for the game "poker solitaire"
 * And, yes, it is redundant to add "abstract" and "public" on the methods.
 */
public interface IPsLogic {
    /**
     * Init a round of the game, i.e. fill the deck, clear hands and more.
     */
    abstract public void initNewGame();

    /**
     * Pick the next card from the deck, save it internally and return it.
     * @return next card from the deck
     * @throws IllegalStateException if a card is already picked but not added to a pile.
     */
    abstract public Card pickNextCard() throws IllegalStateException;

    /**
     * Add the next card, previously picked by pickNextCard, to the indicated pile.
     * @param n the pile to add next card to (index).
     * @throws IllegalStateException if there is no next card (null), the selected pile
     * is full or the game is over.
     */
    abstract public void addCardToPile(int n) throws IllegalStateException;

    /**
     * Return whether this round of the game is over or not.
     * @return true if the game is over.
     */
    abstract public boolean isGameOver();

    /**
     * Return the number of cards drawn from the deck.
     * @return the number of cards drawn from the deck.
     */
    abstract public int getCardCount();

    /**
     * Return a copy of the internal list of piles, with copies of the piles in the list.
     * @return a copy of the internal list of piles.
     */
    abstract public List<Pile> getPiles();

    /**
     * Calculate the total points in the internal piles, using PokerHands.getPokerCombo.
     * @return the total points.
     */
    abstract public int getPoints();
}

