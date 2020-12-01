public class Card {
    private final int value;
    private final String suit;
    private final String face;

    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
        this.face = Integer.toString(value);
    }

    public Card(int value, String suit, String face) {
        this.value = value;
        this.suit = suit;
        this.face = face;
    }

    public String getFace() {
        return face;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return this.face + " of " + this.suit;
    }
}
