/**
 * IPsLogic class implementation
 * Yasir Riyadh Jabbar 18/9/2021
 * TIDAA KTH
 */

package ps;

import cardutils.Card;
import cardutils.Deck;
import cardutils.Pile;
import java.util.ArrayList;
import java.util.List;

public class PsLogic implements IPsLogic{
    private Card nextCard;
    private Deck deck;
    private List<Pile> piles= new ArrayList<>(5);

    @Override
    public void initNewGame() {
        deck=new Deck(); // create deck with 52 cars
        deck.shuffleCards(); // randomize it
        piles.clear();
        for(int i=0;i<5;i++) {  // create list of 5 piles
            piles.add(new Pile());
            piles.get(i).clear();
        }
    }

    @Override
    public Card pickNextCard() throws IllegalStateException {
        if(deck.getSize()>0){
            nextCard=deck.dealCard();  // get a top card from deck
            return nextCard;
        }
        else return null;
    }

    @Override
    public void addCardToPile(int n) throws IllegalStateException {
        Pile p=piles.get(n);  // which pile we are dealing with?
        if(p.getSize() < 5) p.add(nextCard); // add new Card to it if its size < 5
    }

    @Override
    public boolean isGameOver() {
        if(getCardCount()==25) return true;  // have 25 Cards be drawn?
        else return false;
    }

    @Override
    public int getCardCount() {
        return (52 - deck.getSize());  // how many Cards were drawn?
    }

    @Override
    public List<Pile> getPiles() {
        return piles;   // return List of 5 piles .. piles ={pile0, pile1, .., pile4}
    }

    @Override
    public int getPoints() {  // test all piles for specific combination hands
        int points=0;
        for(Pile x:piles) {
            PokerCombo pc = PokerHands.getPokerCombo(x);
            points = points + pc.getScore();
        }
        return points;
    }

    public String toString(){   // print all 5 piles
        String info=new String("");
        for(Pile x:getPiles())   // for each pile
            info=info + x.toString() + "\n";   // print it in a row
        return info;
    }
}
