import model.SlidingPuzzle;

import java.util.Scanner;

import static model.SlidingPuzzle.Dimension;

public class Main {
    public static void main(String[] args) {
        //Test(); // default test (@author Anders Lindström, anderslm@kth.se)
        // my tests (Yasir Riyadh Jabbar KTH/TIDAA)
        myTest_3();
        //myTest_8();
        //myTest_15();

    }

    public static void Test(){
        SlidingPuzzle puzzle = new SlidingPuzzle(Dimension.FIFTEEN); // also try other dimensions
        System.out.println("Default Test ...");
        System.out.println(puzzle.toString());
        for(int val = 0; val < 16; val++) {
            System.out.println("" + val + " is legal: " + puzzle.isLegalMove(val));
        }
        int[] moves = new int[]{ 8, 5, 4, 1, 2 }; // a list of moves
        for(int i = 0; i < moves.length; i++) {
            System.out.println("try move " + moves[i]);
            puzzle.makeMove(moves[i]);
            System.out.println(puzzle.toString());
        }
        System.out.println("Number of moves: " + puzzle.getNoOfMoves());
        System.out.println("reset puzzle");
        puzzle.reset();
        System.out.println(puzzle.toString());
    }

    public static void myTest_3(){
        SlidingPuzzle puzzle = new SlidingPuzzle(Dimension.THREE);
        System.out.println("My Test Puzzle (2x2) ...");
        int move;
        Scanner input=new Scanner(System.in);
        while(!puzzle.isSolved()) {
            System.out.println(puzzle.toString());
            System.out.print("Allowed Legal moves are: ");
            for (int val = 0; val < 4; val++)
                if (puzzle.isLegalMove(val)) System.out.print(val + " ");
            System.out.print("\n");
            do {
                System.out.print(">> Please, enter your move: ");
                move = input.nextInt();
            } while (!puzzle.isLegalMove(move));
            puzzle.makeMove(move);
        };
        System.out.println(puzzle.toString());
        System.out.println(">> Puzzle is solved!");
        System.out.println(">> Number of moves: " + puzzle.getNoOfMoves());
    }

    public static void myTest_8(){
        SlidingPuzzle puzzle = new SlidingPuzzle(Dimension.EIGHT);
        System.out.println("My Test Puzzle (3x3) ...");
        int move;
        Scanner input=new Scanner(System.in);
        while(!puzzle.isSolved()) {
            System.out.println(puzzle.toString());
            System.out.print("Allowed Legal moves are: ");
            for (int val = 0; val < 9; val++)
                if (puzzle.isLegalMove(val)) System.out.print(val + " ");
            System.out.print("\n");
            do {
                System.out.print(">> Please, enter your move: ");
                move = input.nextInt();
            } while (!puzzle.isLegalMove(move));
            puzzle.makeMove(move);
        };
        System.out.println(puzzle.toString());
        System.out.println(">> Puzzle is solved!");
        System.out.println(">> Number of moves: " + puzzle.getNoOfMoves());
    }

    public static void myTest_15(){
        SlidingPuzzle puzzle = new SlidingPuzzle(Dimension.FIFTEEN);
        System.out.println("My Test Puzzle (4x4) ...");
        int move;
        Scanner input=new Scanner(System.in);
        while(!puzzle.isSolved()) {
            System.out.println(puzzle.toString());
            System.out.print("Allowed Legal moves are: ");
            for (int val = 0; val < 16; val++)
                if (puzzle.isLegalMove(val)) System.out.print(val + " ");
            System.out.print("\n");
            do {
                System.out.print(">> Please, enter your move: ");
                move = input.nextInt();
            } while (!puzzle.isLegalMove(move));
            puzzle.makeMove(move);
        };
        System.out.println(puzzle.toString());
        System.out.println(">> Puzzle is solved!");
        System.out.println(">> Number of moves: " + puzzle.getNoOfMoves());
    }
}
