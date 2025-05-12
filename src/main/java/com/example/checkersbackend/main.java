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
            String aiMove = (ar.startAlgo(br.board, 10, true));
            br.makeMove(aiMove);
            br.printBoard();
            System.out.println(aiMove);
            br.printBoard();
            userinput = scanner.next();
            br.makeMove(userinput);
            br.printBoard();

        }




    }
}
