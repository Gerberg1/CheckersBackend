package com.example.checkersbackend;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userinput="a";
        BoardRepository br = new BoardRepository();
        AlgoRepository ar = new AlgoRepository(br);

        br.printBoard();

        while (!userinput.equals("e")){


            String aiMove = (ar.startAlgo(br.board, 12, true));
            System.out.println(aiMove);
            br.makeMove(aiMove);
            br.printBoard();
            userinput = scanner.next();
            br.makeMove(userinput);
            br.printBoard();

        }




    }
}
