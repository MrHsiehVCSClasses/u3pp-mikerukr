package u3pp;

import java.util.Random;

public class Deck{
    private int cardsDealt = 0;
    Random shuffle = new Random();
    private Card[] Cards = new Card[52];
    //creates deck
    public Deck(){
        int index = 0;
        for(int i = 0; i < Card.VALUES.length; i++){
            for(int a= 0;a<Card.SUITS.length; a++){
                Card createDeck = new Card(Card.SUITS[a], Card.VALUES[i]);
                Cards [index] = createDeck;
                index+=1;
            }
        }
    }
    //shuffles deck into temp deck
    public void shuffle(){
        cardsDealt = 0;
        for(int i=0;i<Cards.length;i++){
            int ran = shuffle.nextInt(Cards.length);
            Card temp = Cards[ran];
            Cards[ran]=Cards[i];
            Cards[i]=temp;
        }
        cardsDealt=0;
    }
    //chooses card from top of deck
    public Card deal(){
        if(cardsDealt==52){
            System.out.println("You have run out of cards in the deck. Shuffle deck now.");
            return null;
        }
        cardsDealt+=1;
        return Cards[51-(cardsDealt-1)];
    }
    //returns amount of cards in deck
    public int numLeft(){
        return (Cards.length-cardsDealt);
    }
}