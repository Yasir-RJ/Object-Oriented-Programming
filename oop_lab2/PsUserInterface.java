/*
 * Use scan.readLine() when reading menu alternatives. Use string.charAt(int index)
 * to get a specific character from the input string.
 */
import cardutils.Card;
import cardutils.Deck;
import cardutils.Pile;
import ps.PsLogic;
import java.util.Scanner;

public class PsUserInterface {

    private PsLogic psLogic;
    private Scanner input;

    public PsUserInterface() {
        this.psLogic=new PsLogic();
        this.input = new Scanner(System.in);
    }

    public void run() {
        char ch0 = ' ';
        String response;

        do {
            printMenu();
            response = input.next();
            response = response.toUpperCase();
            ch0 = response.charAt(0); // first character

            switch(ch0) {
                case 'A':	testDeck(); break;
                case 'B':	testPile(); break;
                case 'C':	newGame(); break;
                case 'X':	System.out.println("End Game!"); break;
                default: 	System.out.println("Illegal command");
            }

        } while(ch0 != 'X');
    }
    public void printMenu() {
        System.out.println("-----------------Select from Menu------------------");
        System.out.println("A [Del1] Deck Test... Reset Deck and shuffle cards");
        System.out.println("B [Del1] Pile Test... Make 5 piles");
        System.out.println("C [Del2] New Game");
        System.out.println("X Exit");
        System.out.println("----------------------------------------------------");
    }

    public void testDeck() {
        System.out.println("[Deck Test]... Reset Deck and shuffle cards");
        Deck deck=new Deck();
        deck.shuffleCards();
        System.out.println(deck.toString());
        System.out.println("Current deck size = " + deck.getSize());
    }

    public void testPile() {
        System.out.println("[Pile Test]... Make 5 piles from Deck");
        Pile[] pile= new Pile[5];
        for(int i=0;i<5;i++)
            pile[i]=new Pile();
        Deck deck=new Deck();
        deck.shuffleCards();
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++)
                pile[i].add(deck.dealCard());
            System.out.println("Pile (" + (i+1) + "): " + pile[i].toString());
        }
        System.out.println("Remaining Cards in Deck: "+deck.toString());
        System.out.println("Current Deck size = " + deck.getSize());
    }

    public void newGame() {
        System.out.println("Starting a new game...");
        psLogic.initNewGame();
        System.out.println("Game is Ready.");
        do{
            pickNewCard();
        }while (!psLogic.isGameOver());
        System.out.println(psLogic.toString());
        System.out.println("Game is over!");
        System.out.println("You got " + psLogic.getPoints() + " points.");
    }

    public void pickNewCard() {
        Card topCard= psLogic.pickNextCard();
        System.out.println(psLogic.toString());
        while(true){
            System.out.println("Next card: " + topCard.toShortString());
            System.out.print("Chose a pile [0..4]: ");
            String answer = input.next();
            int pos = Integer.parseInt(answer.trim());
            if(pos<0 || pos>4){     // continue scan if illegal pos
                System.out.print(">> Illegal Pile number! ... ");
                continue;
            }
            if(psLogic.getPiles().get(pos).getSize() == 5) {     // continue scan if pile(pos) is full
                System.out.print(">> Pile ("+pos+") is full! ... ");
                continue;
            }
            psLogic.addCardToPile(pos);
            break;
        }
    }
}
