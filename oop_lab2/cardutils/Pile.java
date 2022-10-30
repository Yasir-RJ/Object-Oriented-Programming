/**
 * Pile class implementation
 * Yasir Riyadh Jabbar 18/9/2021
 * TIDAA KTH
 */

package cardutils;

import java.util.ArrayList;
import java.util.List;

public class Pile {
    private List<Card> Pile5;

    public Pile(){
        Pile5=new ArrayList<Card>(5);
    }   // create a pile (list of 5 cards)

    public int getSize(){
        return this.Pile5.size();
    }  // how many cards remain in pile?

    public void clear(){
        this.Pile5.clear();
    } // let pile be empty

    public void add(Card c){      // add a card to a pile if it is not full
        if(this.getSize() < 5)
            this.Pile5.add(c);
    }
    public void add(List<Card> cards){
        if((Pile5.size()+cards.size()) < 6 )     // add list of cards to a pile
            Pile5.addAll(cards);
    }

    public Card get(int position){             // which card at this position in the pile?
        if(position > 0 && position <= Pile5.size())
            return this.Pile5.get(position-1);
        else return null;        // there is no such card in the pile
    }
    public List<Card> getCards(){
        return this.Pile5;
    }   // return all  cards in the pile

    public Card remove(int position){
        if(position > 0 && position <= Pile5.size()){
            Card c=Pile5.get(position-1);      // which card at this position in the pile?
            Pile5.remove(c);            // then remove it from pile
            return c;
        }
        else return null;     // there is no such card in the pile
    }
    public Boolean remove(Card c){      // if pile contains this card, then remove it
        if(Pile5.contains(c)) {
            Pile5.remove(c);
            return true;
        }
        else return false;   // there is no such card in the pile
    }

    public Boolean remove(List<Card> cards){     // if pile contain these cards, then remove them
        int oldSize=Pile5.size();
        for(Card c:cards) {
            if(Pile5.contains(c))
                Pile5.remove(c);
        }
        if(oldSize!=Pile5.size()) return true;
        else return false;    // there is no such cards in the pile
    }

    public Boolean contains(Card c){
        if(Pile5.contains(c)) return true;    // does pile contain this card?
        else return false;
    }

    public int noOfSuit(Suit suit){      // count number of suits in the pile for this suit
        int suitCount=0;
        for(Card c:Pile5)
            if(c.getSuit() == suit) suitCount++;
        return suitCount;
    }

    public int noOfRank(Rank rank){     // count number of ranks in the pile for this rank
        int rankCount=0;
        for(Card c:Pile5)
            if(c.getRank() == rank) rankCount++;
        return rankCount;
    }

    @Override
    public String toString(){
        String info=new String("[");
        for(Card x:Pile5)
            info=info+x.toShortString()+" ";   // print the content of the pile
        info=info+"]";
        return info;
    }
}
