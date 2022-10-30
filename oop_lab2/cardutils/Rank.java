package cardutils;

/**
 * Represents rank values for an ordinary playing card. The method getValue()
 * returns an integer, ACE considered as 1 and King as 13.
 */
public enum Rank {
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
    EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

    public int getValue() {
        return value;
    }

    private final int value;

    private Rank(int value) {
        this.value = value;
    }
}
