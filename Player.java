import java.util.ArrayList;

public class Player {
    private int count;
    private final String name;
    private final ArrayList<Card> hand;
    private final int HAND_SIZE = 5;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    // Pairs, 3 of Kind, or Four of a Kind
    private void countSameCards() {
        int sameFace = 1;
        int temp;

        for (int i = 0; i < this.hand.size() - 1; i++) {
            temp = 1;
            for (int j = i + 1; j < this.hand.size(); j++) {
                if (this.hand.get(i).getFace().equals(this.hand.get(j).getFace())) {
                    temp++;
                }
            }

            if (temp > sameFace) {
                sameFace = temp;
            }
        }
        switch (sameFace) {
            case 2: {
                this.count += 2;
                break;
            } case 3: {
                this.count += 6;
                break;
            } case 4: {
                this.count += 12;
            }
        }
    }

    private void sortHandByValue() {
        Card temp;

        for (int i = 0; i < this.hand.size() - 1; i++) {
            for (int j = i + 1; j < this.hand.size(); j++) {
                if (this.hand.get(i).getValue() > this.hand.get(j).getValue()) {
                    temp = this.hand.get(i);
                    this.hand.set(i, this.hand.get(j));
                    this.hand.set(j, temp);
                }
            }
        }
    }

    private void countStraights() {
        if (this.hand.size() == 0){
         return;
        }
        sortHandByValue();
        int previousVal = this.hand.get(0).getValue();
        int straight = 1;
        int temp = 1;

        for (int i = 1; i < this.hand.size(); i++) {
            if (previousVal + 1 == this.hand.get(i).getValue()) {
                temp++;
            } else if (previousVal != this.hand.get(i).getValue()) {
                if (straight < temp) {
                    straight = temp;
                }
                temp = 1;
            }
            previousVal = this.hand.get(i).getValue();
        }

        if (straight < temp) {
            straight = temp;
        }

        switch (straight) {
            case 3:
                this.count += 3;
                break;
            case 4:
                this.count += 4;
                break;
            case 5:
                this.count += 5;
                break;
        }
    }

    private void count15() {
        int sum;
        int temp;

        for (int i = 2; i < this.hand.size(); i++) {
            for (int j = 0; j < this.hand.size(); j++) {
                sum = this.hand.get(j).getValue();
                temp = 0;
                for (int k = j + 1; k < this.hand.size(); k++) {
                    temp += this.hand.get(k).getValue();
                    if (k + 1 == j + i) {
                        if (sum + temp == 15) {
                            this.count += 2;
                        }
                    }
                }
            }
        }
    }

    public void deal(Card card) {
        hand.add(card);
    }

    public Card unDeal(){
        Card card = hand.get(0);
        hand.remove(0);
        return card;
    }

    public int countHands() {
        this.count = 0;
        this.countSameCards();
        this.countStraights();
        this.count15();
        return this.count;
    }


    public void showHand() {
        System.out.println(this.countHands());
        for (int i = 0; i < this.hand.size(); i++) {
            System.out.println(hand.get(i));
        }
    }
}
