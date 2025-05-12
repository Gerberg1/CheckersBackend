package com.example.checkersbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class Controller {
    @Autowired
    BoardRepository br;

    @Autowired
    AlgoRepository algo;

@GetMapping("/boardstate")
public int[][]getBoard(){
    String algoMove = algo.startAlgo(br.board, 10, true);
    br.makeMove(algoMove);
    return br.board;
}

@PostMapping("/move")
    public void makeMove(@RequestBody String move){
    br.makeMove(move);
}
}
