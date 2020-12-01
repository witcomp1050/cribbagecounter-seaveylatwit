import java.util.Random;
import java.util.ArrayList;

public class Deck {
    private final ArrayList<Card> cards;
    private final int DECK_SIZE = 52;

    public Deck() {
        String[] suits = {"Spades", "Clubs", "Diamonds", "Hearts"};
        String[] faces = {"Jack", "Queen", "King"};

        this.cards = new ArrayList<>();

        for (int i = 0; i < DECK_SIZE / 4; i++) {
            for (String suit : suits) {
                if (i < 9) {
                    this.cards.add(new Card(i + 2, suit));
                } else if (i + 1 == DECK_SIZE / 4) {
                    this.cards.add(new Card(1, suit, "Ace"));
                } else {
                    this.cards.add(new Card(i + 2, suit, faces[i - 9]));
                }
            }
        }
    }

    public void shuffle() {
        Random rand = new Random();
        int randIndex;

        for (int i = 0; i < this.cards.size(); i++) {
            randIndex = i + rand.nextInt(this.cards.size() - i);

            Card temp = this.cards.get(randIndex);
            this.cards.set(randIndex, this.cards.get(i));
            this.cards.set(i, temp);
        }
    }

    public Card deal(boolean cutCards) {
        if (cutCards) {
            return this.cards.remove((DECK_SIZE - 4) / 2);
        }
        return this.cards.remove(0);
    }

    public void unDeal(Card card) {
        this.cards.add(card);
    }
    public void viewDeck() {
        for (Card card : this.cards) {
            System.out.println(card);
        }
    }
}
