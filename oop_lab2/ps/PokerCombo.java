/**
 * PokerCombo class implementation
 * Yasir Riyadh Jabbar 18/9/2021
 * TIDAA KTH
 */

package ps;
// English point system for selected poker hands

public enum PokerCombo {
    NONE(0),PAIR(1),TWO_PAIRS(3),FLUSH(5),THREE_KIND(6),FOUR_KIND(16);
    private final int score;
    private PokerCombo(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
}