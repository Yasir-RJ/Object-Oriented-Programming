/**
 * PokerHands class implementation
 * Yasir Riyadh Jabbar 18/9/2021
 * TIDAA KTH
 */

package ps;

import cardutils.Pile;
import cardutils.Rank;
import cardutils.Suit;

public class PokerHands {
    private PokerHands(){
    }

    public static PokerCombo getPokerCombo(Pile p){        // scan through cards combinations
        if (is4Kinds(p))        return PokerCombo.FOUR_KIND;
        else if (is3Kinds(p))   return PokerCombo.THREE_KIND;
        else if (isFlush(p))    return PokerCombo.FLUSH;
        else if (is2Pairs(p))   return PokerCombo.TWO_PAIRS;
        else if (is1Pair(p))    return PokerCombo.PAIR;
        else                    return PokerCombo.NONE;
    }

    private static boolean is4Kinds (Pile p) { // check if we have 4 kinds
        Rank [] ranks = Rank.values ();
        for(Rank r :ranks)
            if(p.noOfRank(r) >=4 ) return true;  // Four cards of one rank?
        return false;

    }
    private static boolean is3Kinds (Pile p) { // check if we have 3 kinds
        Rank [] ranks = Rank.values ();
        for(Rank r:ranks)
            if(p.noOfRank(r) >=3 ) return true;  // Three cards of one rank?
        return false;
    }
    private static boolean isFlush (Pile p) { // check if we have a flush
        Suit [] suits = Suit.values ();
        for(Suit s:suits)
            if(p.noOfSuit(s) ==5 ) return true; // Five cards of the same suit?
        return false;
    }
    private static boolean is2Pairs (Pile p) { // check if we have 2 pairs
        int noOfPair=0;
        Rank [] ranks = Rank.values ();
        for(Rank r:ranks)
            if(p.noOfRank(r) >=2 ) noOfPair++;   // Two cards of one rank with two cards of another rank?
        if(noOfPair >= 2) return true;
        else return false;
    }
    private static boolean is1Pair (Pile p) { // check if we have 1 pair
        Rank [] ranks = Rank.values ();
            for(Rank r:ranks)
                if(p.noOfRank(r) >=2 ) return true; // Two cards of one rank?
        return false;
    }

}
