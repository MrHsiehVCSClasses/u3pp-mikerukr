package u3pp;
import java.util.Scanner;
public class Blackjack {
    //instance variables needed
    String name;
    Deck myDeck = new Deck();
    Card[] userHand = new Card[11];
    Card[] dealerHand = new Card[11];
    //creates new deck
    public Blackjack(){
        myDeck.shuffle();
    }
    //determines if deck at 0 then shuffles
    private void shouldShuffle(){
        if (myDeck.numLeft() == 0) {
            myDeck.shuffle();
        }
    }
    //the amount of space left in deck from used cards
    private static int nullnum (Card[] hand){
        int nullnum= 0;
        for (int i = 0; i < hand.length; i++){
            if (hand[i] == null){
                nullnum= i-1;
                break;
            }
            if (hand[i] != null){
                nullnum+= 1;
            }
        }
        return nullnum;
    }
    //calculates points of hand
    public static int calcPoints(Card[] hand){
        int total = 0;
        int value = 0;
        for (int i = 0; i < hand.length; i++){
            if (hand[i] == null){
                continue;
            }
            if (hand[i].getValue() == "Ace"){
                value = 11;
            }
            else if (hand[i].getValue() == "Jack"){
                value = 10;
            }
            else if (hand[i].getValue() == "Queen"){
                value = 10;
            }
            else if (hand[i].getValue() == "King"){
                value = 10;
            }
            else {
                value = Integer.parseInt(hand[i].getValue());
            }
            total = value + total;
        }
        System.out.println("Total points: " + total);
        return total;
    }
    //checks whether they have blackjack
    public static boolean isBlackjack(Card[] hand){
        if (calcPoints(hand) == 21 &&nullnum(hand) == 2 && (isBust(hand) == false)){
            System.out.println( "Blackjack: " + true);
            return true;
        }
        System.out.println( "Blackjack: " + false);
        return false;
    }
    //checks whether dealer is at 16
    public static boolean shouldDealerKeepHitting(Card[] hand){
        if (calcPoints(hand) <= 16){
            System.out.println("Dealer hits again!");
            return true;
        }
        System.out.println("Dealer stays");
        return false;
    }
    //checks whether hand is over 21
    public static boolean isBust(Card[] hand){
        if (calcPoints(hand) > 21) {
            return true;
        }
        return false;
    }
    //resets the game
    private void reset(){
        for (int i = 0; i <=nullnum(userHand); i++){
            userHand[i].setValue("0");
        }
        for (int i = 0; i <=nullnum(dealerHand); i++){
            dealerHand[i].setValue("0");
        }
    }
    //starts the game by gving the random hand
    private void startGame(){
        System.out.println("Goodluck!");
        shouldShuffle();
        userHand[0] = myDeck.deal();
        userHand[1] = myDeck.deal();
        dealerHand[0] = myDeck.deal();
        dealerHand[1] = myDeck.deal();
        for (int i = 2; i < 11; i++){
            userHand[i] = null;
        }
        for (int i = 2; i < 11; i++){
            dealerHand[i] = null;
        }
    }
    //determines results of game
    public static String determineResult(Card[] userHand, Card[] dealerHand){
        if (calcPoints(userHand) == calcPoints(dealerHand)){
            return "User Pushes";
        }
        if ((calcPoints(userHand) < calcPoints(dealerHand) || isBust(userHand) == true) && isBust(dealerHand) == false){
            return "User Loses";
        }
        return "User Wins";
    }
    //gives cards in hand
    private void printNumInHand(Card[] userHand, Card[] dealerHand){
        System.out.println(name + "'s " + "hand: ");
        for (int i = 0; i <=nullnum(userHand); i++){
            System.out.println(userHand[i]);
        }
        System.out.println("\nDealer's hand: ");
        for (int i = 0; i <=nullnum(dealerHand); i++){
            System.out.println(dealerHand[i]);
        }
        System.out.println("Press enter to continue");
    }
    //play function for each frreaking variable man holy crud this is so amazeballs!
    public void play(Scanner scanner) {
        System.out.println("What's your name?");
        name = scanner.nextLine();
        System.out.println("Hello there " + name + " I'll be your dealer today. Please press enter to start.");
        scanner.nextLine();
        startGame();
        printNumInHand(userHand, dealerHand);
        scanner.nextLine();
        if (isBust(dealerHand) == true){
            System.out.println("dealer busts!");
            System.out.println("you won!");
            System.out.println("play again? (y/n)");
            if (scanner.nextLine().equals("y")){
                reset();
                play(scanner);
            }
            else if (scanner.nextLine().equals("n")){
                System.out.println("ggs!");
            }
            else {
                System.out.println("not y/n");
            }
        }
        if (isBlackjack(dealerHand) == true){
            System.out.println("dealer wins...");
            System.out.println("play again? (y/n)");
            if (scanner.nextLine().equals("y")){
                reset();
                play(scanner);
            }
            else if (scanner.nextLine().equals("n")){
                System.out.println("ggs!");
            }
            else {
                System.out.println("not y/n");
            }
        }
        if (isBlackjack(userHand) == true) {
            System.out.println("You won blackjack!");
            System.out.println("play again? (y/n)");
            if (scanner.nextLine().equals("y")){
                reset();
                play(scanner);
            }
            else if (scanner.nextLine().equals("n")){
                System.out.println("ggs!");
            }
            else {
                System.out.println("not y/n");
            }
        }
        else {
            System.out.println("would you like to hit or stay? (h/s)");
            if (scanner.nextLine().equals("h")){
                System.out.println("you have chosen to hit!");
                shouldShuffle();
                userHand[nullnum(userHand) + 1] = myDeck.deal();
                printNumInHand(userHand, dealerHand);
                if (calcPoints(userHand) == 21){
                    determineResult(userHand, dealerHand);
                    System.out.println("play again? (y/n)");
                    if (scanner.nextLine().equals("y")){
                        reset();
                        play(scanner);
                    }
                    else if (scanner.nextLine().equals("n")){
                        System.out.println("ggs!");
                    }
                    else {
                        System.out.println("not y/n");
                    }
                }
                scanner.nextLine();
                if (isBust(userHand) == true){
                    System.out.println("busted! nerddd!!!");
                    System.out.println("Dealerrrr wins!!!!");
                    System.out.println("play again? (y/n)");
                    if (scanner.nextLine().equals("y")){
                        reset();
                        play(scanner);
                    }
                    else if (scanner.nextLine().equals("n")){
                        System.out.println("ggs!");
                    }
                    else {
                        System.out.println("not y/n");
                    }   
                }
                else if (calcPoints(userHand) <= 21)
                {
                    System.out.println("would you like to hit or stay? (h/s)");
                    if (scanner.nextLine() .equals ("h")){
                        System.out.println("you chose to hit!");
                        shouldShuffle();
                        userHand[nullnum(userHand) + 1] = myDeck.deal();
                        printNumInHand(userHand, dealerHand);
                        if (calcPoints(userHand) == 21){
                            determineResult(userHand, dealerHand);
                            System.out.println("play again? (y/n)");
                            if (scanner.nextLine().equals("y")){
                                reset();
                                play(scanner);
                            }
                            else if (scanner.nextLine().equals("n")){
                                System.out.println("ggs!");
                            }
                            else {
                                System.out.println("not y/n");
                            }
                        }
            else if (scanner.nextLine().equals("s")){
                while (shouldDealerKeepHitting(dealerHand) == true) {
                    shouldShuffle();
                    dealerHand[nullnum(dealerHand) + 1] = myDeck.deal();
                    if (isBust(userHand) == true){
                        System.out.println("you busted!");
                        System.out.println("dealer wins!");
                        System.out.println("play again? (y/n)");
                        if (scanner.nextLine().equals("y")){
                            reset();
                            play(scanner);
                        }
                        else if (scanner.nextLine().equals("n")){
                            System.out.println("ggs!");
                        }
                        else {
                            System.out.println("not y/n");
                        }
                    }
                }
                printNumInHand(userHand, dealerHand);
                scanner.nextLine();
                System.out.println(determineResult(userHand, dealerHand));
                System.out.println("play again? (y/n)");
                if (scanner.nextLine().equals("y")){
                    reset();
                    play(scanner);
                }
                else if (scanner.nextLine().equals("n")){
                    System.out.println("ggs!");
                }
                else {
                    System.out.println("not y/n");
                }
            }
        }
    }
}
}
}
}