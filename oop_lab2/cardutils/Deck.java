/**
 * Deck class implementation
 * Yasir Riyadh Jabbar 18/9/2021
 * TIDAA KTH
 */

package cardutils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> List52;
    public Deck(){
        List52=new ArrayList<Card>(52); // create list of 52 cards
        List52.clear();
        this.fill(); // fill the list with 52 cards
    }

    public int getSize(){
        return List52.size();
    }  // how many cards remain in deck?

    public Card dealCard(){
        Card myCard=List52.get(0); // get the top card
        List52.remove(0);   // and remove it from deck
        return myCard;
    }

    public void shuffleCards(){
        Collections.shuffle(List52);
    }   // randomize the cards in the deck

    public void fill(){
        Suit [] suites = Suit.values ();
        Rank [] ranks = Rank.values ();
        for(Suit s:suites)              // for each suit
            for(Rank r:ranks)           // for each rank
                List52.add(new Card(r,s));     // add a card to the deck
    }
    @Override
    public String toString(){
        String info=new String("");
        for(Card x:List52)
            info=info+x.toShortString()+" ";    // print the content of deck (all cards)
        return info;
    }
}

