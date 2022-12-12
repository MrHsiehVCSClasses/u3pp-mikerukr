package u3pp;
import java.util.Scanner;
public class Main 
{
    public static void main(String[] args) {
        
        // Use this space to test your code, or actually run your project
        Deck deck = new Deck();
        Blackjack blackjack = new Blackjack();
        Scanner scanner = new Scanner(System.in);
        deck.shuffle();
        blackjack.play(scanner);
    }

}
