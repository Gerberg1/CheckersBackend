package com.example.checkersbackend;

import org.springframework.stereotype.Repository;

@Repository
public class AlgoRepository {

    int cutOffCount = 0;
    int nodeCount = 0;

    private final BoardRepository boardRepository;


    public AlgoRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private long endTime;


    public String startAlgo(int[][] board, int depth, boolean isBlack) {
        cutOffCount = 0;
        int bestScore = isBlack ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        String bestMove = "";

        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        endTime = System.currentTimeMillis() + 30_000;

        for (String move : boardRepository.getAllLegalMoves(board, isBlack)) {

            if (System.currentTimeMillis() > endTime) break;
            System.out.println(move);
            int[][] copyBoard = simulateMove(board, move);
            int score = alphaBeta(copyBoard, isBlack, depth, alpha, beta);


            if ((isBlack && score > bestScore)) {
                bestScore = score;
                bestMove = move;
                alpha = Math.max(alpha, score);
            }
            if ((!isBlack && score < bestScore)) {
                bestScore = score;
                bestMove = move;
                beta = Math.min(beta, score);
            }
           if (alpha >= beta) {
                break;
            }
            if (System.currentTimeMillis() > endTime) {
                break;
            }
        }

        //System.out.println("Branching factor: "+ (computeEBF(nodeCount, depth)));
        //System.out.println("Node count: " + nodeCount);
        //System.out.println("Cut off count: " + cutOffCount);
        return bestMove;
    }

    public double computeEBF(int totalNodes, int depth) {
        double low = 1.0;
        double high = totalNodes;
        double epsilon = 1e-5;

        while (high - low > epsilon) {
            double mid = (low + high) / 2;
            double estimatedNodes = 0;
            for (int i = 0; i <= depth; i++) {
                estimatedNodes += Math.pow(mid, i);
            }

            if (estimatedNodes > totalNodes) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return (low + high) / 2;
    }




    public int minMax(int[][] board, boolean isMaxing, int depth) {
        int maxScore = Integer.MIN_VALUE;
        int minScore = Integer.MAX_VALUE;

        if (depth == 0) {
            return boardValues(board);
        }
        if (isMaxing) {
            maxScore = Integer.MIN_VALUE;
            for (String move : boardRepository.getAllLegalMoves(board, true)) {
                int score = minMax(simulateMove(board, move), false, depth - 1);
                maxScore = Math.max(score, maxScore);
            }
            return maxScore;

        } else {
            minScore = Integer.MAX_VALUE;
            for (String move : boardRepository.getAllLegalMoves(board, false)) {
                int score = minMax(simulateMove(board, move), true, depth - 1);
                minScore = Math.min(score, minScore);
            }
        }
        return minScore;
    }

    public int alphaBeta(int[][] board, boolean isMaxing, int depth, int alpha, int beta) {
        nodeCount++;
        int maxScore = Integer.MIN_VALUE;
        int minScore = Integer.MAX_VALUE;

        if (System.currentTimeMillis() > endTime || depth == 0) {
            return boardValues(board);
        }
        if (isMaxing) {
            maxScore = Integer.MIN_VALUE;
            for (String move : boardRepository.getAllLegalMoves(board, true)) {
                if (System.currentTimeMillis() > endTime) break;
                int score = alphaBeta(simulateMove(board, move), false, depth - 1, alpha, beta);
                maxScore = Math.max(score, maxScore);
                alpha = Math.max(alpha, maxScore);
               if (alpha >= beta){
                   cutOffCount++;
                  break;
                }
            }
            return maxScore;

        } else {
            minScore = Integer.MAX_VALUE;
            for (String move : boardRepository.getAllLegalMoves(board, false)) {
                if (System.currentTimeMillis() > endTime) break;
                int score = alphaBeta(simulateMove(board, move), true, depth - 1, alpha, beta);
                minScore = Math.min(score, minScore);
                beta = Math.min(beta, minScore);
                if (alpha >= beta){
                    cutOffCount++;
                    break;
                }
            }

        }

        return minScore;
    }


    public int boardValues(int[][] board) {
        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // switch (board[i][j]) {
                switch (board[i][j]) {
                    case 1 -> {score += 5;
                        score+=i;
                        if (j>=2 && j <= 5) score +=1;} //sort brik


                    case 2 -> score += 10; //sort konge

                    case 3 ->{ score -= 5;
                        score -= (7-i);
                        if (j>=2 && j<=5) score -= 1;} //hvid brik

                    case 4 -> score -= 10; //hvid konge
                }
            }
        }
        if (boardRepository.checkWinnerBlack()){
            score=score+1000;
        }
        if (boardRepository.checkWinnerWhite()){
            score=score-1000;
        }

        return score;
    }
    public int[][] simulateMove(int[][] board, String move) {
        int[][] copy = new int[8][8];
        for (int i = 0; i < 8; i++) copy[i] = board[i].clone();

        char[] moves = move.toCharArray();
        char firstLetter = moves[0];
        int firstNumber = moves[1] - '0';
        char secondLetter = moves[3];
        int secondNumber = moves[4] - '0';

        int firstLetterAsNumber = boardRepository.transformLetterToNumber(firstLetter);
        int secondLetterAsNumber = boardRepository.transformLetterToNumber(secondLetter);

        int piece = copy[firstNumber-1][boardRepository.transformLetterToNumber(firstLetter)];
        copy[firstNumber-1][boardRepository.transformLetterToNumber(firstLetter)] = 0;

        if (secondNumber - firstNumber > 1 || secondNumber - firstNumber < -1) {
            int capNumber = (firstNumber + secondNumber) / 2;
            int capLetter = (firstLetterAsNumber + secondLetterAsNumber) / 2;
            copy[capNumber-1][capLetter] = 0;
        }


        if (piece == 1 && secondNumber == 8) piece = 2;
        if (piece == 3 && secondNumber == 1) piece = 4;

        copy[secondNumber-1][boardRepository.transformLetterToNumber(secondLetter)] = piece;


        return copy;
    }


}