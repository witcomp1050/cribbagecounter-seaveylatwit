import java.util.Scanner;
import java.util.Random;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;


public class Cribbage extends Application {
    private final Player human;
    private final Deck deck;
    private int handScored;
    private int correctGuesses;
    private double averageTime;
    String output;
    final String dealerName;
    private Text outputtext;

    public Cribbage() {
        this.human = new Player("Player");
        this.deck = new Deck();
        this.handScored = 0;
        this.correctGuesses = 0;
        this.averageTime = 0;
        dealerName = "Fredo";
    }

    public String getDealerName() {
        return dealerName;
    }

    private void calcAverageTime(long newTime) {
        double time = (double) newTime / 1000000000;

        if (this.averageTime == 0) {
            this.averageTime = time;
            this.handScored = 1;
        } else {
            this.averageTime = (this.averageTime * this.handScored + time) / (this.handScored++ + 1);
        }
    }

    public void deal() {
        for (int i = 0; i < this.human.countHands() - 1; i++) {
            this.human.deal(this.deck.deal(false));
        }
        this.human.deal(this.deck.deal(true));
    }

    public void unDeal() {
        for (int i = 0; i < this.human.countHands(); i++) {
            this.deck.unDeal(this.human.unDeal());
        }
    }

    public void driver() {
        String keepGoing;
        long startTime, endTime;
        double guessedPoints, actualPoints;
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        do {
            this.deck.shuffle();
            this.deal();
            this.human.showHand();
            actualPoints = this.human.countHands();

            //outputtext.setText(outputtext.getText() + "How many points do you have: ");

            startTime = System.nanoTime();
            guessedPoints = sc.nextInt();
            endTime = System.nanoTime();

            this.unDeal();
            this.calcAverageTime(endTime - startTime);
            //outputtext.setText(outputtext.getText() + "That took " + ((endTime - startTime) / 1000000000) + " seconds.");

            if (guessedPoints == actualPoints) {
                if (rand.nextInt(2) == 1) {
                    //outputtext.setText(outputtext.getText() +"Even " + this.getDealerName() + " is impressed!");
                } else {
                    //outputtext.setText(outputtext.getText() + "Nice! That is correct.");
                }

                this.correctGuesses++;
            } else {
                //outputtext.setText(outputtext.getText() + "That is incorrect. You had " + actualPoints + " points.");
            }

            //outputtext.setText(outputtext.getText() + "\nHands scored: " + this.handScored);
            //outputtext.setText(outputtext.getText() + "Correct: " + this.correctGuesses + " vs Incorrect: " + (this.handScored - this.correctGuesses));
            //outputtext.setText(outputtext.getText() + "Percent Correct: " + ((double) this.correctGuesses / (double) this.handScored) * 100 + "%.");
            //outputtext.setText(outputtext.getText() + "Average Time: " + this.averageTime + " seconds.");

            //outputtext.setText(outputtext.getText() + "Would you like to try again (Y/N): ");
            sc.nextLine();
            keepGoing = sc.nextLine();
        } while (Character.toUpperCase(keepGoing.charAt(0)) == 'Y');
        sc.close();
    }

    @Override public void start(Stage stage) {

        //this.driver();
        Text text = new Text(10, 40, "Let's play Cribbage!");
        text.setFont(new Font(40));
        //Scene scene = new Scene(new Group(text));

        this.outputtext = new Text(10, 40, "");
        this.outputtext.setFont(new Font(40));
        Scene scene = new Scene(new Group(text, this.outputtext));

        //text = new Text(10, 40, this.output);
        //text.setFont(new Font(40));
        //Scene scene = new Scene(new Group(text));

        stage.setTitle("Cribbage");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();


        Cribbage game = new Cribbage();
        game.driver();
    }

    public static void main(String[] args) {
        //Cribbage game = new Cribbage();
        // game.driver();

        launch(args);
    }
}

