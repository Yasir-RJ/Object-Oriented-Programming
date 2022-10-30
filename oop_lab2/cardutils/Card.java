package cardutils;

/**
 * Represents an immutable playing card, with rank and suit.
 */
public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getRankValue() {
        return rank.getValue();
    }

    public int getSuitValue() {
        return suit.ordinal(); // default values, 0, 1, 2, 3
    }

    public boolean equals(Card other) {
        return this.rank == other.rank && this.suit == other.suit;
    }

    @Override
    public String toString() {
        String info = rank + " of " + suit;
        return info;
    }

    public String toShortString() {
        String info = "";
        switch (rank) {
            case ACE:
                info += 'A';
                break;
            case KING:
                info += 'K';
                break;
            case QUEEN:
                info += 'Q';
                break;
            case JACK:
                info += 'J';
                break;
            default:
                info += getRankValue();
        }
        switch (suit) {
            case SPADES:
                info += '\u2660';
                break;
            case HEARTS:
                info += '\u2764';
                break;
            case CLUBS:
                info += '\u2663';
                break;
            case DIAMONDS:
                info += '\u2666';
                break;
            default:
                ;
        }
        return info;
    }

}