package com.example.checkersbackend;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Repository

public class BoardRepository {

    Moves moves = new Moves();

    int[][] board = {

            {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,3,0,3,0,3,0,3},
            {3,0,3,0,3,0,3,0},
            {0,3,0,3,0,3,0,3},
    };


    public void printBoard(){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i=7;i>-1;i--){
            for (int j=0;j<8;j++){
                stringBuilder.append(board[i][j]);
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder);
        System.out.println("-------------------");
    }

    boolean isBlack = true;

    boolean hasJumped = false;
    boolean hasJumpedKing = false;
    String hasJumpedTo = "";
    HashMap<String, List<String>> legalMovesBlack = moves.legalMovesBlack;
    HashMap<String, List<String>> legalJumpsBlack = moves.legalJumpsBlack;
    HashMap<String, List<String>> legalMovesWhite = moves.legalMovesWhite;
    HashMap<String, List<String>> legalJumpsWhite = moves.legalJumpsWhite;

    HashMap<String, List<String>> legalMovesKing = moves.legalMovesKing;
    HashMap<String, List<String>> legalJumpsKing = moves.legalJumpsKing;

    public void resetHasJumped(){
        hasJumped = false;
        hasJumpedKing = false;
        hasJumpedTo = "";
    }

    public int transformLetterToNumber(char letter) {
        int number = 0;
        switch (letter) {
            case 'b' -> number = 1;
            case 'c' -> number = 2;
            case 'd' -> number = 3;
            case 'e' -> number = 4;
            case 'f' -> number = 5;
            case 'g' -> number = 6;
            case 'h' -> number = 7;
        }
        return number;
    }

    public char transformNumberToLetter(int number) {
        char letter = 'a';
        switch (number) {
            case 1 -> letter = 'b';
            case 2 -> letter = 'c';
            case 3 -> letter = 'd';
            case 4 -> letter = 'e';
            case 5 -> letter = 'f';
            case 6 -> letter = 'g';
            case 7 -> letter = 'h';
        }
        return letter;
    }

    public void printTurn(){
        if (isBlack){
            System.out.println("Black's turn");
        }
        else {
            System.out.println("White's turn");
        }
    }

    public boolean checkWinner(){
        return checkWinnerBlack() || checkWinnerWhite();
    }

    public boolean checkWinnerBlack(){
        return checkWinnerByMovesBlack() || checkWinnerByPiecesBlack();
    }

    public boolean checkWinnerWhite(){
        return checkWinnerByMovesWhite() || checkWinnerByPiecesWhite();
    }

    public boolean checkWinnerByMovesBlack(){
        if(isBlack&&getAllLegalMoves(board, isBlack).isEmpty()){
            System.out.println("WHITE WINS");
            return true;
        }

        return false;
    }

    public boolean checkWinnerByMovesWhite(){
         if (!isBlack&&getAllLegalMoves(board, !isBlack).isEmpty()){
            System.out.println("WHITE WINS");
            return true;
        }
         return false;
    }


    public boolean checkWinnerByPiecesWhite() {
        int blacks = 0;
        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 1 || board[i][j] == 2) {
                    blacks++;
                }
            }
        }
     if (blacks==0){
            System.out.println("WHITE WINS");
            return true;
        }
        return false;
    }

    public boolean checkWinnerByPiecesBlack() {
        int whites = 0;
        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 3 || board[i][j] == 4) {
                    whites++;
                }
            }
        }
        if (whites==0){
            System.out.println("BLACK WINS");
            return true;
        }
        return false;
    }



    public void makeMove(String positionsToMove){
        char[] positions = positionsToMove.toCharArray();
        String firstPosition = "" + positions[0] + positions[1];
        String secondPosition = "" + positions[3] + positions[4];
        char firstLetter = positions[0];
        char secondLetter = positions[3];
        int firstNumber = positions[1] - '0';
        int secondNumber = positions[4] - '0';

        int piece = board[(firstNumber - 1)][transformLetterToNumber(positions[0])];

        //Tjek om brikken, der lige har hoppet kan hoppe igen
        if (hasJumped){
            if (isJumpPossibleOnePiece()) {
                if (!firstPosition.equals(hasJumpedTo)) {
                    System.out.println("This piece has to jump again");
                    return;
                }
                if (firstPosition.equals(hasJumpedTo)) {
                    isBlack = !isBlack;
                }
            }
        }
        if (hasJumpedKing){
            if (isJumpPossibleOneKing()){
                if (!firstPosition.equals(hasJumpedTo)){
                    System.out.println("This piece has to jump again");
                    return;
                }
                if (firstPosition.equals(hasJumpedTo)){
                    isBlack=!isBlack;
                }
            }
        }


        if (board[(secondNumber - 1)][transformLetterToNumber(secondLetter)] != 0) {//Der vi går hen
            return;}

        if ((isBlack && (piece != 1 && piece != 2)) || (!isBlack && (piece != 3 && piece != 4))) {
            return;
        }

        //HOPPER
        if (secondNumber - firstNumber > 1 || secondNumber - firstNumber < -1) {
            switch (piece) {
                case 1:
                    jumpPiece(positionsToMove, piece, firstNumber, secondNumber, firstPosition, secondPosition, firstLetter, secondLetter);
                    break;
                case 2:
                    jumpKing(positionsToMove, piece, firstNumber, secondNumber, firstPosition, secondPosition, firstLetter, secondLetter);
                    break;
                case 3:
                    jumpPiece(positionsToMove, piece, firstNumber, secondNumber, firstPosition, secondPosition, firstLetter, secondLetter);
                    break;
                case 4:
                    jumpKing(positionsToMove, piece, firstNumber, secondNumber, firstPosition, secondPosition, firstLetter, secondLetter);
            }
            //GÅR
        } else {
            switch (piece) {
                case 1:
                    movePiece(piece, firstNumber, secondNumber, firstPosition, secondPosition, firstLetter, secondLetter);
                    break;
                case 2:
                    moveKing(piece, firstNumber, secondNumber, firstPosition, secondPosition, firstLetter, secondLetter);
                    break;
                case 3:
                    movePiece(piece, firstNumber, secondNumber, firstPosition, secondPosition, firstLetter, secondLetter);
                    break;
                case 4:
                    moveKing(piece, firstNumber, secondNumber, firstPosition, secondPosition, firstLetter, secondLetter);
                    break;
            }


        }

    }

    public void movePiece(int piece, int firstNumber, int secondNumber, String firstPosition, String secondPosition, char firstLetter, char secondLetter) {
        if (isJumpPossible()) {
            return;
        }


        //move black
        if (isBlack) {
            if (legalMovesBlack.get(firstPosition).contains(secondPosition)) {
                board[(firstNumber - 1)][transformLetterToNumber(firstLetter)] = 0; // Sætter gamle til 0
                if (secondNumber == 8) {
                    board[(secondNumber - 1)][transformLetterToNumber(secondLetter)] = 2;
                } else {
                    board[(secondNumber - 1)][transformLetterToNumber(secondLetter)] = piece;
                }
                isBlack = false;
            }
        }

        //move white
        else if (!isBlack) {
            if (legalMovesWhite.get(firstPosition).contains(secondPosition)) {
                board[(firstNumber - 1)][transformLetterToNumber(firstLetter)] = 0; // Sætter gamle til 0
                if (secondNumber == 1) {
                    board[(secondNumber - 1)][transformLetterToNumber(secondLetter)] = 4;
                } else {
                    board[(secondNumber - 1)][transformLetterToNumber(secondLetter)] = piece;
                }
                isBlack = true;
            }
        }
        resetHasJumped();
        checkWinner();

    }
    public void moveKing(int piece, int firstNumber, int secondNumber, String firstPosition, String secondPosition, char firstLetter, char secondLetter){
        //CHECK JUMPS
        if (legalMovesKing.get(firstPosition).contains(secondPosition)) {
            board[(firstNumber - 1)][transformLetterToNumber(firstLetter)] = 0; // Sætter gamle til 0
            board[(secondNumber - 1)][transformLetterToNumber(secondLetter)] = piece;
            isBlack = !isBlack;
        }
        resetHasJumped();
        checkWinner();

    }



    public void jumpPiece(String positionsToMove, int piece, int firstNumber, int secondNumber, String firstPosition, String secondPosition, char firstLetter, char secondLetter) {
        if (isBlack) {
            if (legalJumpsBlack.get(firstPosition).contains(secondPosition)) {
                if (getPieceInBetween(positionsToMove) == 3 || getPieceInBetween(positionsToMove) == 4) {
                    board[(firstNumber - 1)][transformLetterToNumber(firstLetter)] = 0;
                    removePiece(positionsToMove);

                    if (secondNumber == 8) {
                        board[(secondNumber - 1)][transformLetterToNumber(secondLetter)] = 2;
                    } else {
                        board[(secondNumber - 1)][transformLetterToNumber(secondLetter)] = piece;
                    }

                    isBlack = false;

                }
            }
        }

        else if (!isBlack) {
            if (legalJumpsWhite.get(firstPosition).contains(secondPosition)) {
                if (getPieceInBetween(positionsToMove) == 1 || getPieceInBetween(positionsToMove) == 2) {
                    board[(firstNumber - 1)][transformLetterToNumber(firstLetter)] = 0;
                    removePiece(positionsToMove);

                    if (secondNumber == 1) {
                        board[(secondNumber - 1)][transformLetterToNumber(secondLetter)] = 4;
                    } else {
                        board[(secondNumber - 1)][transformLetterToNumber(secondLetter)] = piece;
                    }

                    isBlack = true;

                }
            }
        }
        hasJumped = true;
        hasJumpedTo = secondLetter + String.valueOf(secondNumber);
        checkWinner();
    }
    public void jumpKing(String positionsToMove, int piece, int firstNumber, int secondNumber, String firstPosition, String secondPosition, char firstLetter, char secondLetter){
        if (isBlack) {
            if (legalJumpsKing.get(firstPosition).contains(secondPosition)) {
                if (getPieceInBetween(positionsToMove) == 3 || getPieceInBetween(positionsToMove) == 4) {
                    board[(firstNumber - 1)][transformLetterToNumber(firstLetter)] = 0;
                    removePiece(positionsToMove);
                    board[(secondNumber - 1)][transformLetterToNumber(secondLetter)] = piece;

                    isBlack = false;

                }
            }
        }

        else if (!isBlack) {
            if (legalJumpsKing.get(firstPosition).contains(secondPosition)) {
                if (getPieceInBetween(positionsToMove) == 1 || getPieceInBetween(positionsToMove) == 2) {
                    board[(firstNumber - 1)][transformLetterToNumber(firstLetter)] = 0;
                    removePiece(positionsToMove);
                    board[(secondNumber - 1)][transformLetterToNumber(secondLetter)] = piece;

                    isBlack = true;

                }
            }
        }
        hasJumpedKing = true;
        hasJumpedTo = secondLetter + String.valueOf(secondNumber);
        checkWinner();
    }

    public boolean isJumpPossible(){
        if (isJumpPossibleKing()){return true;}
        String positionsToMove = "";
        if (!isBlack) {
            for (int i = 7; i > -1; i--) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] == 3) {
                        for (String s : legalJumpsWhite.get("" + transformNumberToLetter(j) + (i + 1))) {
                            char[] positions = s.toCharArray();
                            int a = positions[1] - '0';
                            if (board[a-1][transformLetterToNumber(positions[0])]==0) { //Går hen til

                                String b = String.valueOf(i+1);

                                positionsToMove = transformNumberToLetter(j) + b + "-" + s;
                                if (getPieceInBetween(positionsToMove) == 1 || getPieceInBetween(positionsToMove) == 2) {
                                    System.out.println("White can jump");
                                    return true;
                                }
                            }
                        } ;

                    } ;

                }
            }
        }
        if (isBlack) {
            for (int i = 7; i > -1; i--) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] == 1) {
                        for (String s : legalJumpsBlack.get("" + transformNumberToLetter(j) + (i + 1))) {
                            char[] positions = s.toCharArray();
                            int a = positions[1] - '0';
                            if (board[a-1][transformLetterToNumber(positions[0])] == 0) { // Går hen til

                                String b = String.valueOf(i + 1);

                                positionsToMove = transformNumberToLetter(j) + b + "-" + s;
                                if (getPieceInBetween(positionsToMove) == 3 || getPieceInBetween(positionsToMove) == 4) {
                                    System.out.println("Black can take");
                                    return true;
                                }
                            }
                        }
                        ;

                    }
                    ;

                }
            }
        }

        return false;
    }

    public boolean isJumpPossibleKing(){
        String positionsToMove = "";
        if (!isBlack) {
            for (int i = 7; i > -1; i--) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] == 4) {
                        for (String s : legalJumpsKing.get("" + transformNumberToLetter(j) + (i + 1))) {
                            char[] positions = s.toCharArray();
                            int a = positions[1] - '0';
                            if (board[a-1][transformLetterToNumber(positions[0])]==0) { //Går hen til

                                String b = String.valueOf(i+1);

                                positionsToMove = transformNumberToLetter(j) + b + "-" + s;
                                if (getPieceInBetween(positionsToMove) == 1 || getPieceInBetween(positionsToMove) == 2) {
                                    System.out.println("White can jump");
                                    return true;
                                }
                            }
                        } ;

                    } ;

                }
            }
        }
        if (isBlack) {
            for (int i = 7; i > -1; i--) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] == 2) {
                        for (String s : legalJumpsKing.get("" + transformNumberToLetter(j) + (i + 1))) {
                            char[] positions = s.toCharArray();
                            int a = positions[1] - '0';
                            if (board[a-1][transformLetterToNumber(positions[0])] == 0) { // Går hen til

                                String b = String.valueOf(i + 1);

                                positionsToMove = transformNumberToLetter(j) + b + "-" + s;
                                if (getPieceInBetween(positionsToMove) == 3 || getPieceInBetween(positionsToMove) == 4) {
                                    System.out.println("Black can take");
                                    return true;
                                }
                            }
                        }
                        ;

                    }
                    ;

                }
            }
        }
        return false;
    }

    public boolean isJumpPossibleOnePiece(){
        String positionsToMove = "";
        if (!isBlack){
            for (String s : legalJumpsBlack.get(hasJumpedTo)){
                char[] positions = s.toCharArray();
                int a = positions[1] - '0';
                if (board[a-1][transformLetterToNumber(positions[0])] == 0){

                    positionsToMove = hasJumpedTo + "-" + s;
                    if (getPieceInBetween(positionsToMove) == 3 || getPieceInBetween(positionsToMove) == 4){
                        return true;
                    }
                }
            }
        }
        else if (isBlack){
            for (String s : legalJumpsWhite.get(hasJumpedTo)){
                char[] positions = s.toCharArray();
                int a = positions[1] - '0';
                if (board[a-1][transformLetterToNumber(positions[0])] == 0){

                    positionsToMove = hasJumpedTo + "-" + s;
                    if (getPieceInBetween(positionsToMove) == 1 || getPieceInBetween(positionsToMove) == 2){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean isJumpPossibleOneKing(){
        String positionsToMove = "";
        if (!isBlack){
            for (String s : legalJumpsKing.get(hasJumpedTo)){
                char[] positions = s.toCharArray();
                int a = positions[1] - '0';
                if (board[a-1][transformLetterToNumber(positions[0])] == 0){

                    positionsToMove = hasJumpedTo + "-" + s;
                    if (getPieceInBetween(positionsToMove) == 3 || getPieceInBetween(positionsToMove) == 4){
                        return true;
                    }
                }
            }
        }
        else if (isBlack){
            for (String s : legalJumpsKing.get(hasJumpedTo)){
                char[] positions = s.toCharArray();
                int a = positions[1] - '0';
                if (board[a-1][transformLetterToNumber(positions[0])] == 0){

                    positionsToMove = hasJumpedTo + "-" + s;
                    if (getPieceInBetween(positionsToMove) == 1 || getPieceInBetween(positionsToMove) == 2){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getPieceInBetween(String positionsToMove){
        char[] positions = positionsToMove.toCharArray();
        int a = positions[1] - '0'; // first number
        int b = positions[4] - '0'; //second number
        int rowToRemoveFrom = (a + b) / 2;


        char charToRemoveFrom = 'a';
        if (positions[0] == 'a' && positions[3] == 'c' || positions[0] == 'c' && positions[3] == 'a') {
            charToRemoveFrom = 'b';
        } else if (positions[0] == 'b' && positions[3] == 'd' || positions[0] == 'd' && positions[3] == 'b') {
            charToRemoveFrom = 'c';
        } else if (positions[0] == 'c' && positions[3] == 'e' || positions[0] == 'e' && positions[3] == 'c') {
            charToRemoveFrom = 'd';
        } else if (positions[0] == 'd' && positions[3] == 'f' || positions[0] == 'f' && positions[3] == 'd') {
            charToRemoveFrom = 'e';
        } else if (positions[0] == 'e' && positions[3] == 'g' || positions[0] == 'g' && positions[3] == 'e') {
            charToRemoveFrom = 'f';
        } else if (positions[0] == 'f' && positions[3] == 'h' || positions[0] == 'h' && positions[3] == 'f') {
            charToRemoveFrom = 'g';
        }

        return board[rowToRemoveFrom - 1][transformLetterToNumber(charToRemoveFrom)];

    }


    public void removePiece(String positionsToMove){
        char[] positions = positionsToMove.toCharArray();
        int a = positions[1] - '0'; // first number
        int b = positions[4] - '0'; //second number
        int rowToRemoveFrom = (a+b)/2;


        char charToRemoveFrom = 'a';
        if (positions[0]=='a'&&positions[3]=='c'||positions[0]=='c'&&positions[3]=='a') {
            charToRemoveFrom = 'b';
        }
        else if (positions[0]=='b'&&positions[3]=='d'||positions[0]=='d'&&positions[3]=='b') {
            charToRemoveFrom = 'c';
        }
        else if (positions[0]=='c'&&positions[3]=='e'||positions[0]=='e'&&positions[3]=='c') {
            charToRemoveFrom = 'd';
        }
        else if (positions[0]=='d'&&positions[3]=='f'||positions[0]=='f'&&positions[3]=='d') {
            charToRemoveFrom = 'e';
        }
        else if (positions[0]=='e'&&positions[3]=='g'||positions[0]=='g'&&positions[3]=='e') {
            charToRemoveFrom = 'f';
        }
        else if (positions[0]=='f'&&positions[3]=='h'||positions[0]=='h'&&positions[3]=='f') {
            charToRemoveFrom = 'g';
        }



        board[rowToRemoveFrom-1][transformLetterToNumber(charToRemoveFrom)]=0;


    }


    //a3-1  = Sort brik på a3
    public void setUpBoardManually(String string){
        char[] positions = string.toCharArray();
        int newNumber = positions[3]-'0';
        int a = positions[1]-'0';
        for (char c : positions){
            System.out.println(c);
        }
        board[(a-1)][transformLetterToNumber(positions[0])]=newNumber;

    }
    public List<String> getAllLegalMoves(int[][] board, boolean isBlack) {
        List<String> jumpMoves = new ArrayList<>();
        List<String> normalMoves = new ArrayList<>();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int piece = board[row][col];
                char fromCol = transformNumberToLetter(col);
                int fromRow = row + 1;
                String fromPos = "" + fromCol + fromRow;

                if (isBlack && (piece == 1 || piece == 2)) {
                    List<String> jumpTargets = (piece == 1) ?
                            legalJumpsBlack.getOrDefault(fromPos, List.of()) :
                            legalJumpsKing.getOrDefault(fromPos, List.of());

                    for (String to : jumpTargets) {
                        if (isEmpty(to, board)) {
                            String move = fromPos + "-" + to;
                            int captured = getPieceInBetween(move);
                            if (captured == 3 || captured == 4) {
                                jumpMoves.add(move);
                            }
                        }
                    }


                    if (jumpMoves.isEmpty()) {
                        List<String> targets = (piece == 1) ?
                                legalMovesBlack.getOrDefault(fromPos, List.of()) :
                                legalMovesKing.getOrDefault(fromPos, List.of());

                        for (String to : targets) {
                            if (isEmpty(to, board)) {
                                normalMoves.add(fromPos + "-" + to);
                            }
                        }
                    }
                }

                if (!isBlack && (piece == 3 || piece == 4)) {
                    List<String> jumpTargets = (piece == 3) ?
                            legalJumpsWhite.getOrDefault(fromPos, List.of()) :
                            legalJumpsKing.getOrDefault(fromPos, List.of());

                    for (String to : jumpTargets) {
                        if (isEmpty(to, board)) {
                            String move = fromPos + "-" + to;
                            int captured = getPieceInBetween(move);
                            if (captured == 1 || captured == 2) {
                                jumpMoves.add(move);
                            }
                        }
                    }

                    if (jumpMoves.isEmpty()) {
                        List<String> targets = (piece == 3) ?
                                legalMovesWhite.getOrDefault(fromPos, List.of()) :
                                legalMovesKing.getOrDefault(fromPos, List.of());

                        for (String to : targets) {
                            if (isEmpty(to, board)) {
                                normalMoves.add(fromPos + "-" + to);
                            }
                        }
                    }
                }
            }
        }
        return !jumpMoves.isEmpty() ? jumpMoves : normalMoves;

    }

    private boolean isEmpty(String pos, int[][] board) {
        char colChar = pos.charAt(0);
        int row = Character.getNumericValue(pos.charAt(1)) - 1;
        int col = transformLetterToNumber(colChar);
        return board[row][col] == 0;
    }
}
